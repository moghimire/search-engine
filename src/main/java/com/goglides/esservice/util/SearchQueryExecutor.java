package com.goglides.esservice.util;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import com.goglides.esservice.clientservice.ClientService;
import com.goglides.esservice.enums.QueryFields;

public class SearchQueryExecutor {

	// private static final Logger logger =
	// LoggerFactory.getLogger(SearchQueryExecutor.class);

	public static String execute(QueryBuilder query, int pageNo, String sortBy, String sortOrder) throws IOException {
		Client client = ClientService.getClient();
		int offset = (pageNo - 1) * Configuration.PAGE_SIZE;

		SearchRequestBuilder requestBuilder = client.prepareSearch(Configuration.INDEX).setTypes(Configuration.TYPE)
				.setFrom(offset).setSize(Configuration.PAGE_SIZE);

		if (sortBy != "") {
			int index = sortBy.indexOf('.');
			FieldSortBuilder fsort = new FieldSortBuilder(sortBy);
			if (index > 0) {
				fsort.setNestedPath(sortBy.substring(0, index));
			}
			fsort.order(sortOrder.equalsIgnoreCase("DESC") ? SortOrder.DESC : SortOrder.ASC);
			requestBuilder.addSort(fsort);
		}

		String[] includes = { QueryFields.LISTING_ID.toString(), QueryFields.TITLE.toString(),
				QueryFields.LOGO.toString(), QueryFields.COMPANYNAME.toString(), QueryFields.RATING_COUNT.toString(),
				QueryFields.RATING.toString(), QueryFields.LISTING_TYPE.toString(), QueryFields.PRICE_ID.toString(),
				QueryFields.PRICE_ACTIVITY.toString(), QueryFields.PRICE_TRANSPORTATION.toString(),
				QueryFields.PRICE_MULTIMEDIA.toString(), QueryFields.DURATION.toString(),
				QueryFields.SCHEDULE.toString(), QueryFields.LOCATION_ADDRESS.toString(),
				QueryFields.LOCATION_GEOLOCATION_LAT.toString(), QueryFields.LOCATION_GEOLOCATION_LON.toString(),
				QueryFields.SLUG.toString() };

		requestBuilder.setFetchSource(includes, new String[] {});

		requestBuilder.setQuery(query);

		System.out.println("Query:\n" + requestBuilder.toString());

		SearchResponse searchResponse = requestBuilder.execute().actionGet();

		return JSONHandler.parseResults(searchResponse, pageNo, offset, Configuration.PAGE_SIZE);
	}

	protected static String getFieldValueOrNull(Map<String, SearchHitField> map, String fieldName) {
		if (map.get(fieldName) != null) {
			return map.get(fieldName).getValue().toString();
		}
		return null;
	}

	protected static int getIntFieldValueOrNull(Map<String, SearchHitField> map, String fieldName) {

		if (map.get(fieldName) != null) {
			return Integer.valueOf(map.get(fieldName).getValue().toString());
		}
		return 0;
	}

	protected static float getFloatFieldValueOrNull(Map<String, SearchHitField> map, String fieldName) {
		if (map.get(fieldName) != null) {
			return Float.valueOf(map.get(fieldName).getValue().toString());
		}
		return (float) 0.0;
	}

	protected static double getDoubleFieldValueOrNull(Map<String, SearchHitField> map, String fieldName) {
		if (map.get(fieldName) != null) {
			return Double.valueOf(map.get(fieldName).getValue().toString());
		}
		return 0.00;
	}

}
