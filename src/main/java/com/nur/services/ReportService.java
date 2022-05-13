package com.nur.services;

import java.util.List;

import com.nur.bindingrequest.SearchRequest;
import com.nur.bindingresponse.SearchResponse;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<SearchResponse> searchPlans(SearchRequest request);

}
