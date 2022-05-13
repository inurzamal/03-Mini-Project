package com.nur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nur.bindingrequest.SearchRequest;
import com.nur.bindingresponse.SearchResponse;
import com.nur.entities.EligibilityDtlsEntity;
import com.nur.repository.EligDtlsRepository;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private EligDtlsRepository repository;

	@Override
	public List<String> getPlanNames() {
		return repository.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return repository.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		
		if(request == null) {
			List<EligibilityDtlsEntity> allRecords = repository.findAll();
		}else {
			// including null filter total 8 possibilities of filters
		}
		return null;
	}

}
