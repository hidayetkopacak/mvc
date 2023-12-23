/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mvc.mvc.services;

import com.mvc.mvc.entities.ExchangeRate;
import com.mvc.mvc.entities.Logs;
import java.util.List;

/**
 *
 * @author HİDAYET
 */
public interface ILogsService {
    public void saveOneLog(ExchangeRate exchangeRate);
    public List<Logs> getLogsByExchangeRateId(Long exhangeRateId);
}
