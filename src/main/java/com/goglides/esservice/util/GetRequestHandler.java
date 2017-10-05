package com.goglides.esservice.util;

import javax.ws.rs.core.MultivaluedMap;

import com.goglides.esservice.dto.SearchParam;
import com.goglides.esservice.enums.SearchParamFields;
import com.goglides.esservice.exception.SearchParameterNotSufficient;

public class GetRequestHandler {

	public static SearchParam parseSearchParameters(MultivaluedMap<String, String> queryParams)
			throws SearchParameterNotSufficient {
		SearchParam searchParam = new SearchParam();

		searchParam.setKeyword(queryParams.get(SearchParamFields.KEYWORD.toString()) == null ? null
				: queryParams.get(SearchParamFields.KEYWORD.toString()).get(0));

		if (searchParam.getKeyword() == null) {
			throw new SearchParameterNotSufficient("Keyword is Empty.");
		}

		searchParam.setPageNo(queryParams.get(SearchParamFields.PAGE_NO.toString()) == null ? 1
				: Integer.parseInt(queryParams.get(SearchParamFields.PAGE_NO.toString()).get(0).toString()));

		// searchParam.setPageNo(queryParams.get(SearchParamFields.PAGE_SIZE.toString())
		// == null ? 1
		// :
		// Integer.parseInt(queryParams.get(SearchParamFields.PAGE_SIZE.toString()).get(0).toString()));

		searchParam.setFrom_date(queryParams.get(SearchParamFields.FROM_DATE.toString()) == null ? ""
				: queryParams.get(SearchParamFields.FROM_DATE.toString()).get(0));

		searchParam.setTo_date(queryParams.get(SearchParamFields.TO_DATE.toString()) == null ? ""
				: queryParams.get(SearchParamFields.TO_DATE.toString()).get(0));

		searchParam.setNo_of_guests(queryParams.get(SearchParamFields.NO_OF_GUESTS.toString()) == null ? 0
				: Integer.parseInt(queryParams.get(SearchParamFields.NO_OF_GUESTS.toString()).get(0).toString()));

		searchParam.setPrice_range_min(queryParams.get(SearchParamFields.PRICE_RANGE_MIN.toString()) == null ? null
				: Double.parseDouble(queryParams.get(SearchParamFields.PRICE_RANGE_MIN.toString()).get(0).toString()));
		searchParam.setPrice_range_max(queryParams.get(SearchParamFields.PRICE_RANGE_MAX.toString()) == null ? null
				: Double.parseDouble(queryParams.get(SearchParamFields.PRICE_RANGE_MAX.toString()).get(0).toString()));
		searchParam.setPrice(queryParams.get(SearchParamFields.PRICE.toString()) == null ? null
				: Double.parseDouble(queryParams.get(SearchParamFields.PRICE.toString()).get(0).toString()));

		searchParam.setDuration(queryParams.get(SearchParamFields.DURATION.toString()) == null ? null
				: queryParams.get(SearchParamFields.DURATION.toString()));

		searchParam.setRating(queryParams.get(SearchParamFields.RATING.toString()) == null ? null
				: queryParams.get(SearchParamFields.RATING.toString()));

		searchParam.setSchedule(queryParams.get(SearchParamFields.SCHEDULE.toString()) == null ? null
				: queryParams.get(SearchParamFields.SCHEDULE.toString()));

		searchParam.setNationality(queryParams.get(SearchParamFields.NATIONALITY.toString()) == null ? ""
				: queryParams.get(SearchParamFields.NATIONALITY.toString()).get(0));
		searchParam.setLocation(queryParams.get(SearchParamFields.LOCATION.toString()) == null ? ""
				: queryParams.get(SearchParamFields.LOCATION.toString()).get(0));

		searchParam.setLatitude(queryParams.get(SearchParamFields.LATITUDE.toString()) == null ? null
				: Double.parseDouble(queryParams.get(SearchParamFields.LATITUDE.toString()).get(0).toString()));
		searchParam.setLongitude(queryParams.get(SearchParamFields.LONGITUDE.toString()) == null ? null
				: Double.parseDouble(queryParams.get(SearchParamFields.LONGITUDE.toString()).get(0).toString()));
		searchParam.setNeighbourhood(queryParams.get(SearchParamFields.NEIGHBOURHOOD.toString()) == null ? null
				: Double.parseDouble(queryParams.get(SearchParamFields.NEIGHBOURHOOD.toString()).get(0).toString()));

		searchParam.setSortBy(queryParams.get(SearchParamFields.SORT_BY.toString()) == null ? ""
				: queryParams.get(SearchParamFields.SORT_BY.toString()).get(0));
		searchParam.setSortOrder(queryParams.get(SearchParamFields.SORT_ORDER.toString()) == null ? ""
				: queryParams.get(SearchParamFields.SORT_ORDER.toString()).get(0));

		// searchMapEnable
		searchParam.setSearchmap(queryParams.get(SearchParamFields.SEARCH_MAP.toString()) == null ? false
				: Boolean.parseBoolean(queryParams.get(SearchParamFields.SEARCH_MAP.toString()).get(0).toString()));

		if (searchParam.getSearchmap()) {

			searchParam.setTopleftlat(queryParams.get(SearchParamFields.TOPLEFTLAT.toString()) == null ? null
					: Double.parseDouble(queryParams.get(SearchParamFields.TOPLEFTLAT.toString()).get(0).toString()));
			searchParam.setTopleftlon(queryParams.get(SearchParamFields.TOPLEFTLON.toString()) == null ? null
					: Double.parseDouble(queryParams.get(SearchParamFields.TOPLEFTLON.toString()).get(0).toString()));

			searchParam.setBottomrightlat(queryParams.get(SearchParamFields.BOTRIGHTLAT.toString()) == null ? null
					: Double.parseDouble(queryParams.get(SearchParamFields.BOTRIGHTLAT.toString()).get(0).toString()));
			searchParam.setBottomrightlon(queryParams.get(SearchParamFields.BOTRIGHTLON.toString()) == null ? null
					: Double.parseDouble(queryParams.get(SearchParamFields.BOTRIGHTLON.toString()).get(0).toString()));
		}

		searchParam.setAge_group(queryParams.get(SearchParamFields.AGE_GROUP.toString()) == null ? ""
				: queryParams.get(SearchParamFields.AGE_GROUP.toString()).get(0));
		searchParam.setListing_type(queryParams.get(SearchParamFields.LISTING_TYPE.toString()) == null ? null
				: queryParams.get(SearchParamFields.LISTING_TYPE.toString()));

		return searchParam;

	}
}
