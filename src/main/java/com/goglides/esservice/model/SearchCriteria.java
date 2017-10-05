package com.goglides.esservice.model;

import org.elasticsearch.search.sort.SortOrder;

public class SearchCriteria {
	private String query;

	private String[] indices;

	private String[] documentTypes;

	private int from = 0;

	private int size = 10;

	private SortOrder sortOrder;

	public SearchCriteria() {
	}

	public String[] getIndexes() {
		return indices;
	}

	public SearchCriteria indices(String... indices) {
		this.indices = indices;
		return this;
	}

	public String[] getDocumentTypes() {
		return documentTypes;
	}

	public SearchCriteria documentTypes(String... documentTypes) {
		this.documentTypes = documentTypes;
		return this;
	}

	public SearchCriteria query(String query) {
		this.query = query;
		return this;
	}

	public String getQuery() {
		return query;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public SearchCriteria sortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}
}
