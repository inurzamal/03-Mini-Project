package com.nur.bindingrequest;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchRequest {
	
	private String planName;
	private String planStatus;
//	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;
//	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate endDate;

}
