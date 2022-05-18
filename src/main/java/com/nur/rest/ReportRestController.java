package com.nur.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nur.bindingrequest.SearchRequest;
import com.nur.bindingresponse.SearchResponse;
import com.nur.reports.ExcelGenerator;
import com.nur.reports.PdfGenerator;
import com.nur.services.ReportService;

@RestController
public class ReportRestController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/plan-names")
	public List<String> getPlanNames() {
		return service.getPlanNames();
	}
	
	@GetMapping("/plan-status")
	public List<String> getStatus(){
		return service.getPlanStatus();
	}
	
	@PostMapping("/search")
	public List<SearchResponse> search(@RequestBody SearchRequest request){
		return service.searchPlans(request);	
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse httpResponse) throws Exception {
		
		httpResponse.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans.xls";	
		httpResponse.setHeader(headerKey, headerValue);
		
		List<SearchResponse> records = service.searchPlans(null);
		ExcelGenerator excelGen = new ExcelGenerator();
		excelGen.generateExcel(records, httpResponse);	
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse httpResponse) throws Exception{
		
		httpResponse.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans.pdf";	
		httpResponse.setHeader(headerKey, headerValue);
					
		List<SearchResponse> records = service.searchPlans(null);
		PdfGenerator pdfGen = new PdfGenerator();
		pdfGen.generatePdf(records, httpResponse);
	}
	
	//Generate PDF based on search request (Not all the records)	
	@PostMapping("/pdf")
	public void generatePdfSearched(@RequestBody SearchRequest request, HttpServletResponse httpResponse) throws Exception{
		
		httpResponse.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans.pdf";	
		httpResponse.setHeader(headerKey, headerValue);
					
		List<SearchResponse> records = service.searchPlans(request);
		PdfGenerator pdfGen = new PdfGenerator();
		pdfGen.generatePdf(records, httpResponse);
	}
}
