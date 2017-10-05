package com.goglides.esservice.util;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkIndexByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.json.JSONArray;

import com.goglides.esservice.clientservice.ClientService;
import com.goglides.esservice.dto.DataOperationParam;

public class Indexing {

	public static Boolean insert(JSONArray insertJSON) {

		Client client = ClientService.getClient();

		BulkRequestBuilder bulkRequest = client.prepareBulk();

		for (int i = 0; i < insertJSON.length(); i++) {
			bulkRequest.add(client.prepareIndex(Configuration.INDEX, Configuration.TYPE)
					.setSource(insertJSON.getJSONObject(i).toString()));

		}

		// 10second Timeout
		BulkResponse bulkResponse = bulkRequest.execute().actionGet(10000);
		if (bulkResponse.hasFailures()) {
			return false;
		} else
			return true;

	}

	public static Boolean deleteNInsert(DataOperationParam dparam) {

		// 10second Timeout
		BulkIndexByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(ClientService.getClient())
				.filter(QueryBuilders.matchQuery("listing_id", dparam.getId())).source(Configuration.INDEX).execute()
				.actionGet(10000);

		if (response.getBulkFailures().isEmpty()) {
			long deleted = response.getDeleted();
			if (deleted == 0) {
				System.out.println("No old records");
			}

			System.out.println(deleted + " records are deleted.");
			System.out.println("DB Operation:" + dparam.getDbOperation());
			if (!dparam.getDbOperation().equalsIgnoreCase("DELETE")) {
				return insert(dparam.getDatas());
			} else {
				return true;
			}
		} else
			return false;

	}

	/*
	 * public static Boolean deleteNInsert(int listing_id, JSONObject
	 * insertJSON) { Client client = ClientService.getClient();
	 * 
	 * ActionListener<BulkIndexByScrollResponse> actListener = new
	 * ActionListener<BulkIndexByScrollResponse>() { //
	 * 
	 * @Override public void onResponse(BulkIndexByScrollResponse response) {
	 * long deleted = response.getDeleted(); if (deleted == 0) {
	 * System.out.println("No old records"); }
	 * 
	 * System.out.println(deleted + " records are deleted."); isDeleted = true;
	 * System.out.println("DB Operation:" + insertJSON.getString("Action")); if
	 * (!insertJSON.getString("Action").equalsIgnoreCase("DELETE")) {
	 * insert(insertJSON.getJSONArray("Results")); } else { refreshIndex();
	 * isInserted = true; }
	 * 
	 * }
	 * 
	 * @Override public void onFailure(Exception e) { isFailure = true;
	 * System.out.println(" Failure LOL"); e.printStackTrace(); } };
	 * 
	 * DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
	 * .filter(QueryBuilders.matchQuery("listing_id",
	 * listing_id)).source(Configuration.INDEX) .execute(actListener);
	 * 
	 * while (!isDeleted && !isFailure) { System.out.println("Deleting: " +
	 * listing_id); }
	 * 
	 * while (!isInserted && !isFailure) { // System.out.println("Inserting: " +
	 * listing_id); }
	 * 
	 * if (isFailure) { return false; } else return isInserted;
	 * 
	 * }
	 */
}
