package com.nur.services;

import com.nur.repository.EligDtlsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private EligDtlsRepository repository;

    @InjectMocks
    private ReportServiceImpl service;

    @Test
    void getPlanNamesTest(){
        List<String> list = Arrays.asList("CCAP","Medicare");
        when(repository.getUniquePlanNames()).thenReturn(list);
        List<String> planNames = service.getPlanNames();
        assertEquals(list,planNames);
    }

}