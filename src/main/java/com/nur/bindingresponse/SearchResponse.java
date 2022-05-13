package com.nur.bindingresponse;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchResponse {
	
	private Long caseNum;
	private String planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double benefitAmt;
	private String holderName;
	private Long holderSsn;
	private String denialReason;
	
}
