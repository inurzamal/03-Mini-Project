package com.nur.reports;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.nur.bindingresponse.SearchResponse;

public class ExcelGenerator {
	
	public void generateExcel(List<SearchResponse> response, HttpServletResponse httpResponse) throws Exception{

	    HSSFWorkbook workbook = new HSSFWorkbook();
	    HSSFSheet sheet = workbook.createSheet("Plans");
	    HSSFRow headerRow = sheet.createRow(0);

	    headerRow.createCell(0).setCellValue("S.No");
	    headerRow.createCell(1).setCellValue("Holder Name");
	    headerRow.createCell(2).setCellValue("Holder SSN");
	    headerRow.createCell(3).setCellValue("Plan Name");
	    headerRow.createCell(4).setCellValue("Plan Status");
	    headerRow.createCell(5).setCellValue("Start Date");
	    headerRow.createCell(6).setCellValue("End Date");
	    headerRow.createCell(7).setCellValue("Benefit Amount");
	    headerRow.createCell(8).setCellValue("Denial Reason");

	    for (int i = 0; i < response.size(); i++) {
	      HSSFRow dataRow = sheet.createRow(i + 1);
	      SearchResponse record = response.get(i);
	      dataRow.createCell(0).setCellValue(i + 1);
	      dataRow.createCell(1).setCellValue(record.getHolderName());
	      dataRow.createCell(2).setCellValue(record.getHolderSsn());
	      dataRow.createCell(3).setCellValue(record.getPlanName());
	      dataRow.createCell(4).setCellValue(record.getPlanStatus());
	      dataRow.createCell(5).setCellValue(String.valueOf(record.getStartDate()));
	      dataRow.createCell(6).setCellValue(String.valueOf(record.getEndDate()));
	      dataRow.createCell(7).setCellValue(String.valueOf(record.getBenefitAmt()));
	      dataRow.createCell(8).setCellValue(record.getDenialReason());
	    }

	    workbook.write(httpResponse.getOutputStream());
	    workbook.close();
	  }

}
