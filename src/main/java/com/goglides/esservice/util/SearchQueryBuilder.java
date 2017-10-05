package com.goglides.esservice.util;

import java.util.List;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.goglides.esservice.dto.SearchParam;
import com.goglides.esservice.enums.QueryFields;

public class SearchQueryBuilder {

	/*
	 * { "search_params": { "keyword": "paragliding", "from_date": "",
	 * "to_date": "", "no_of_guests": "2", "price_range_min": "40.00",
	 * "price_range_max": "100.00", "price": "", "schedule": "10:00am",
	 * "duration": "30min", "rating": "", "location": "pokhara", "latitude": "",
	 * "longitude": "", "listing_type" : "tandem" } }
	 */

	/*
	 * Select listing_id, title from listing where title, description, slug and
	 * category
	 * 
	 * in (keyword) and price between() and schedule = and duration =
	 * 
	 * location in () and type =;
	 */

	public static QueryBuilder mainQuery(SearchParam searchParam) {

		BoolQueryBuilder bqbuilder = QueryBuilders.boolQuery();

		if (searchParam.getKeyword() != null) {

			String keyword = escapeQueryChars(searchParam.getKeyword());

			bqbuilder.should(getKeywordQuery(keyword)).should(getKeywordLocationQuery(keyword)).minimumShouldMatch("1");
		}

		if (searchParam.getPrice() != null) {
			bqbuilder.must(getPriceQuery(searchParam.getPrice()));
		} else if (searchParam.getPrice_range_max() != null) {
			bqbuilder.must(getPriceRangeQuery(searchParam.getPrice_range_max(), searchParam.getPrice_range_min()));
		}
		//
		if (searchParam.getListing_type() != null) {

			bqbuilder.must(getListingTypeQuery(searchParam.getListing_type()));
		}

		if (searchParam.getDuration() != null) {

			bqbuilder.must(getDurationQuery(searchParam.getDuration()));
		}

		if (searchParam.getSchedule() != null) {

			bqbuilder.must(getScheduleQuery(searchParam.getSchedule()));
		}

		if (searchParam.getRating() != null) {

			bqbuilder.must(getRatingQuery(searchParam.getRating()));
		}

		if (searchParam.getNeighbourhood() != null) {
			if (searchParam.getLatitude() != null && searchParam.getLongitude() != null) {
				bqbuilder.must(getNeighbourHoodQuery(searchParam.getNeighbourhood(), searchParam.getLatitude(),
						searchParam.getLongitude()));
			}
		}

		if (searchParam.getSearchmap()) {
			if (searchParam.getTopleftlat() != null && searchParam.getTopleftlon() != null
					&& searchParam.getBottomrightlat() != null && searchParam.getBottomrightlon() != null) {
				bqbuilder.must(getBoudingBoxQuery(searchParam.getTopleftlat(), searchParam.getTopleftlon(),
						searchParam.getBottomrightlat(), searchParam.getBottomrightlon()));
			}
		}

		// DATE filter needs to be implemented

		return bqbuilder;
	}

	private static QueryBuilder getKeywordQuery(String keyword) {
		return QueryBuilders.multiMatchQuery(keyword, QueryFields.TITLE.toString(), QueryFields.DESCRIPTION.toString(),
				QueryFields.CATEGORY.toString());
	}

	private static QueryBuilder getKeywordLocationQuery(String keyword) {
		return QueryBuilders
				.nestedQuery(QueryFields.LOCATION.toString(),
						QueryBuilders.multiMatchQuery(keyword, QueryFields.LOCATION_ADDRESS.toString(),
								QueryFields.LOCATION_CITY.toString(), QueryFields.LOCATION_COUNTRY.toString()),
						ScoreMode.Max);
	}

	private static QueryBuilder getPriceQuery(Double price) {

		return QueryBuilders.nestedQuery(QueryFields.PRICE_PATH.toString(),
				QueryBuilders.matchQuery(QueryFields.PRICE_ACTIVITY.toString(), price), ScoreMode.Max);

	}

	private static QueryBuilder getPriceRangeQuery(Double upperPrice, Double lowerPrice) {

		return QueryBuilders.nestedQuery(QueryFields.PRICE_PATH.toString(),
				QueryBuilders.rangeQuery(QueryFields.PRICE_ACTIVITY.toString()).gte(lowerPrice).lte(upperPrice),
				ScoreMode.Max);

	}

	private static QueryBuilder getListingTypeQuery(List<String> listing_type) {
		return QueryBuilders.termsQuery(QueryFields.LISTING_TYPE.toString(), listing_type);
	}

	private static QueryBuilder getDurationQuery(List<Integer> duration) {
		return QueryBuilders.termsQuery(QueryFields.DURATION.toString(), duration);
	}

	private static QueryBuilder getScheduleQuery(List<String> schedule) {
		return QueryBuilders.termsQuery(QueryFields.SCHEDULE.toString(), schedule);
	}

	private static QueryBuilder getNeighbourHoodQuery(double neighbourhood, double lat, double lon) {

		return QueryBuilders.nestedQuery(QueryFields.LOCATION.toString(),
				QueryBuilders.geoDistanceQuery(QueryFields.LOCATION_GEOLOCATION.toString()).point(lat, lon)
						.distance(neighbourhood, DistanceUnit.KILOMETERS),
				ScoreMode.Max);

	}

	private static QueryBuilder getAvailableDatesQuery(int duration) {
		return QueryBuilders.termQuery(QueryFields.DURATION.toString(), duration);
	}

	private static QueryBuilder getRatingQuery(List<Float> rating) {
		BoolQueryBuilder ratbuilder = QueryBuilders.boolQuery();

		for (Float rat : rating) {
			ratbuilder.should(QueryBuilders.rangeQuery(QueryFields.RATING.toString()).gt(rat - 1).lte(rat));

		}

		return ratbuilder;
	}

	private static QueryBuilder getBoudingBoxQuery(double topLeftLat, double topLeftLon, double bottomRightLat,
			double bottomRightLon) {

		System.out.println("Top Left:");
		System.out.println("Lat: " + topLeftLat);
		System.out.println("lon: " + topLeftLon);
		System.out.println("Bottom Right:");
		System.out.println("lat: " + bottomRightLat);
		System.out.println("lon: " + bottomRightLon);

		return QueryBuilders.nestedQuery(QueryFields.LOCATION.toString(),
				QueryBuilders.geoBoundingBoxQuery(QueryFields.LOCATION_GEOLOCATION.toString())
						.setCorners(new GeoPoint(topLeftLat, topLeftLon), new GeoPoint(bottomRightLat, bottomRightLon)),
				ScoreMode.Max);

	}

	private static String escapeQueryChars(String queryString) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < queryString.length(); i++) {
			final char c = queryString.charAt(i);
			// These characters are part of the query syntax and must be escaped
			// The list if retrieved from Solr escape characters.
			if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^'
					|| c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~' || c == '*' || c == '?'
					|| c == '|' || c == '&' || c == ';' || c == '/' || Character.isWhitespace(c)) {
				sb.append('\\');
			}
			sb.append(c);
		}
		return sb.toString();
	}
}
