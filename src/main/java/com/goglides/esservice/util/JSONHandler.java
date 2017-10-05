package com.goglides.esservice.util;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goglides.esservice.dto.DataOperationParam;
import com.goglides.esservice.dto.SearchParam;

public class JSONHandler {

	public static SearchParam parseInputJSON(String inputParam) {
		JSONObject jsonSearchParam = (JSONObject) new JSONObject(inputParam.toString()).get("search_params");
		ObjectMapper mapper = new ObjectMapper();
		SearchParam searchParam;
		try {
			searchParam = mapper.readValue(jsonSearchParam.toString(), SearchParam.class);

			System.out.println("Search Param after parsing " + searchParam.toString());
			return searchParam;
		} catch (IOException e) {
			System.out.println("Error while mapping to SearchParam");
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	public static String parseResults(SearchResponse searchResponse, int pageNo, int offset, int pageSize) {

		int result_count = (int) searchResponse.getHits().getTotalHits();

		String execution_time = String.valueOf(searchResponse.getTookInMillis()) + "ms";

		JSONObject parentJSONObject = new JSONObject();

		JSONObject nodeJSONObject;

		JSONArray nodeJSONArray = new JSONArray();

		int i = offset;

		for (SearchHit searchHit : searchResponse.getHits()) {

			i++;

			nodeJSONObject = new JSONObject(searchHit.getSourceAsString());

			nodeJSONObject.append("ranking", i);

			// System.out.println(nodeJSONObject.toString());

			nodeJSONArray.put(new JSONObject().put("result", nodeJSONObject));

		}
		int totalNoOfPage = result_count / pageSize;
		if (!((result_count % pageSize) == 0)) {
			totalNoOfPage = totalNoOfPage + 1;
		}
		parentJSONObject.put("result_count", result_count);
		parentJSONObject.put("result_time", execution_time);
		parentJSONObject.put("result_current_pageid", pageNo);
		parentJSONObject.put("result_offset", offset);
		parentJSONObject.put("result_pagesize", pageSize);
		parentJSONObject.put("result_total_no_of_page", totalNoOfPage);
		parentJSONObject.put("results", nodeJSONArray);

		System.out.println("Page No: " + pageNo);

		return parentJSONObject.toString();
	}

	public static DataOperationParam parseDataOperationJSON(String inputParam) {
		JSONObject jsonDataOperationParam = (JSONObject) new JSONObject(inputParam.toString()).get("data_operation");
		DataOperationParam dataOperationParam = new DataOperationParam();

		dataOperationParam.setId(jsonDataOperationParam.getInt("id"));
		dataOperationParam.setDbOperation(jsonDataOperationParam.getString("dbOperation"));
		if (!dataOperationParam.getDbOperation().equalsIgnoreCase("DELETE")) {
			dataOperationParam.setDatas(jsonDataOperationParam.getJSONArray("datas"));
		}

		return dataOperationParam;

	}

}
