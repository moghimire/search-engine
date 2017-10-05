package com.goglides.esservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

import com.goglides.esservice.dto.DataOperationParam;
import com.goglides.esservice.dto.SearchParam;
import com.goglides.esservice.exception.SearchParameterNotSufficient;
import com.goglides.esservice.util.ConfigFileParser;
import com.goglides.esservice.util.GetRequestHandler;
import com.goglides.esservice.util.Indexing;
import com.goglides.esservice.util.JSONHandler;
import com.goglides.esservice.util.SearchQueryBuilder;
import com.goglides.esservice.util.SearchQueryExecutor;

@Path("/")
public class ESService {

	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchGlides(InputStream incomingData) {
		System.out.println("I'm post Hit");
		StringBuilder inputParam = new StringBuilder();
		String result = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				inputParam.append(line);
			}

			SearchParam searchParam = JSONHandler.parseInputJSON(inputParam.toString());

			result = SearchQueryExecutor.execute(SearchQueryBuilder.mainQuery(searchParam), searchParam.getPageNo(),
					searchParam.getSortBy(), searchParam.getSortOrder());

		} catch (IOException e) {
			System.out.println("Error while Querying");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
			e.printStackTrace();
		}
		System.out.println("RESULTS:" + result);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/getsearch")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchGlidesGet(@Context UriInfo ui) {
		// MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		// MultivaluedMap<String, String> pathParams = ui.getPathParameters();
		System.out.println("I'm get Hit");
		String result = "";

		try {

			SearchParam searchParam = GetRequestHandler.parseSearchParameters(ui.getQueryParameters());

			result = SearchQueryExecutor.execute(SearchQueryBuilder.mainQuery(searchParam), searchParam.getPageNo(),
					searchParam.getSortBy(), searchParam.getSortOrder());

		} catch (SearchParameterNotSufficient e) {
			System.out.println("Error while Parsing Get Request");
			result = "{\"error\":true,\"reason\":\"keyword is empty\"}";
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while Querying");
			result = "{\"error\":true}";
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
			result = "{\"error\":true}";
			e.printStackTrace();
		}

		System.out.println("RESULTS:" + result);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/test")
	public Response testService(InputStream incomingData) {
		return Response.status(200).entity("Service is On.").build();
	}

	@POST
	@Path("/dataoperation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dataOperationService(InputStream incomingData) {
		StringBuilder inputParam = new StringBuilder();
		boolean isDataOperated = false;
		try {
			ConfigFileParser.parseConfigFile("config.properties");
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				inputParam.append(line);
			}

			System.out.println("Data Operations Contents:\n" + inputParam.toString());

			DataOperationParam dparam = JSONHandler.parseDataOperationJSON(inputParam.toString());
			isDataOperated = Indexing.deleteNInsert(dparam);
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
			e.printStackTrace();
		}
		System.out.println("Data Operations:" + isDataOperated);

		JSONObject result = new JSONObject();
		if (isDataOperated) {
			result.put("successful", true);
			result.put("failure", false);
		} else {
			result.put("successful", false);
			result.put("failure", true);
		}

		return Response.status(200).entity(result.toString()).build();
	}

}