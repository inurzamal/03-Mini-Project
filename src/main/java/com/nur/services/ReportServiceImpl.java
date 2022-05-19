package com.nur.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
		
		List<EligibilityDtlsEntity> eligRecords = null;
		
		if(isSearchReqEmpty(request)) {
			eligRecords = repository.findAll();
		}		
		else {
			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();	
			
			EligibilityDtlsEntity entity = new EligibilityDtlsEntity();
						
			if(planName != null && !planName.equals("")) {
				entity.setPlanName(planName);
			}
			
			if(planStatus != null && !planStatus.equals("")) {
				entity.setPlanStatus(planStatus);
			}
			
			if(startDate != null && endDate != null) {
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}
			
			Example<EligibilityDtlsEntity> of = Example.of(entity);
			eligRecords = repository.findAll(of);
		}
		
		List<SearchResponse> response = new ArrayList<>();
		
		for(EligibilityDtlsEntity eligRecord: eligRecords) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(eligRecord, sr);
			response.add(sr);
		}
		return response;		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private boolean isSearchReqEmpty(SearchRequest request) {
		if(request.getPlanName() != null && !request.getPlanName().equals("")) {
			return false;
		}
		if(request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			return false;
		}
		if(request.getStartDate() != null && !request.getStartDate().equals("")) {
			return false;
		}

		return true;
	}
}
