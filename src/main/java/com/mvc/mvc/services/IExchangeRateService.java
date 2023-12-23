/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mvc.mvc.services;

import com.mvc.mvc.entities.Admin;
import com.mvc.mvc.entities.ControlExchangeRate;
import com.mvc.mvc.entities.ExchangeRate;
import java.util.List;
import org.springframework.ui.Model;



/**
 *
 * @author HİDAYET
 */
public interface IExchangeRateService {
    
    public String handleAdminLogin(String userName, String password);
    public void handleAdminLogout();
    public String saveExchangeRate(ControlExchangeRate controlExchangeRate);
    public List<ExchangeRate> getAllExchangeRate();
    public ExchangeRate getExchangeRateById(Long id);
    public Double getResult(Double amount, String userInput);
    public void deleteExchangeRateById(Long id);
    public String updateExchangeRate(ExchangeRate exchangeRate);
    public String updateExchangeRate2(ControlExchangeRate controlExchangeRate);

}
