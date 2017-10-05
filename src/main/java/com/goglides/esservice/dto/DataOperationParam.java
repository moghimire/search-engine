package com.goglides.esservice.dto;

import org.json.JSONArray;

public class DataOperationParam {

	int id;
	String dbOperation;
	JSONArray datas;

	public JSONArray getDatas() {
		return datas;
	}

	public void setDatas(JSONArray datas) {
		this.datas = datas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDbOperation() {
		return dbOperation;
	}

	public void setDbOperation(String dbOperation) {
		this.dbOperation = dbOperation;
	}

}
