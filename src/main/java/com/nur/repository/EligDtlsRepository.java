package com.nur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nur.entities.EligibilityDtlsEntity;

public interface EligDtlsRepository extends JpaRepository<EligibilityDtlsEntity, Integer> {
	
	@Query("select distinct(planName) from EligibilityDtlsEntity")
	List<String> getUniquePlanNames();
	
	@Query("select distinct(planStatus) from EligibilityDtlsEntity")
	List<String> getUniquePlanStatus();

}
