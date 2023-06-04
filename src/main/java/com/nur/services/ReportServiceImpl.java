package com.nur.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nur.bindingrequest.SearchRequest;
import com.nur.bindingresponse.SearchResponse;
import com.nur.entities.EligibilityDtlsEntity;
import com.nur.repository.EligDtlsRepository;

@Service
public class ReportServiceImpl implements ReportService {

	public static final String START_DATE = "startDate";
	@Autowired
	private EligDtlsRepository repository;

	@Autowired
	private ModelMapper modelMapper;

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

//			if(startDate != null && endDate != null) {
//				entity.setStartDate(startDate);
//				entity.setEndDate(endDate);
//			}

			if(startDate != null) {
				entity.setStartDate(startDate);
			}
			if(endDate != null) {
				entity.setEndDate(endDate);
			}

//			Example<EligibilityDtlsEntity> of = Example.of(entity);
//			Example<EligibilityDtlsEntity> of = Example.of(entity, ExampleMatcher.matchingAny().withIgnoreCase());
//			Example<EligibilityDtlsEntity> of = Example.of(entity, ExampleMatcher.matchingAll().withIgnoreCase());
//			Example<EligibilityDtlsEntity> of = Example.of(entity, ExampleMatcher.matchingAny().withIgnoreCase().withIgnorePaths("WORKFLOWS"));
//			eligRecords = repository.findAll(of);

			Example<EligibilityDtlsEntity> of = Example.of(entity, ExampleMatcher.matchingAll().withIgnoreCase());
			eligRecords = repository.findAll(of, Sort.by(Sort.Direction.DESC, START_DATE));


		}

//		List<SearchResponse> response = new ArrayList<>();
//
//		for(EligibilityDtlsEntity eligRecord: eligRecords) {
//			SearchResponse sr = new SearchResponse();
//			BeanUtils.copyProperties(eligRecord, sr);
//			response.add(sr);
//		}

		List<SearchResponse> response = eligRecords.stream()
				  .map(eligRecord -> modelMapper.map(eligRecord, SearchResponse.class))
				  .collect(Collectors.toList());

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
		if(request.getEndDate() != null && !request.getEndDate().equals("")) {
			return false;
		}

		return true;
	}
}
