/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mvc.mvc.services.impl;

import com.mvc.mvc.entities.ExchangeRate;
import com.mvc.mvc.entities.Logs;

import com.mvc.mvc.repos.LogsRepository;
import com.mvc.mvc.services.ILogsService;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HİDAYET
 */
@Service
public class LogsServiceImpl implements ILogsService {
   
    @Autowired
    private LogsRepository repo;
    
    @Override
    public void saveOneLog(ExchangeRate exchangeRate){
        if (exchangeRate.getId() != null) {
            System.out.println("NULL DEGIL");
        // If not persisted, save it first
        Logs log = new Logs();
        log.setExchangeRate(exchangeRate);
        log.setCreateDate(new Date());
        log.setExchangeRangeTL(exchangeRate.getExchangeRangeTL());
        repo.save(log);
    }else {
        
        System.out.println("NULLDAYIZ");
        }

    }
    
    @Override
    public List<Logs> getLogsByExchangeRateId(Long exhangeRateId){
        return repo.findByExchangeRateId(exhangeRateId);
    
    }
       
}
