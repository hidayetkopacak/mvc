/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mvc.mvc.repos;

import com.mvc.mvc.entities.ExchangeRate;
import com.mvc.mvc.entities.Logs;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author HİDAYET
 */
public interface LogsRepository extends JpaRepository<Logs, Long> {
    List<Logs> findByExchangeRateId(Long exchangeRateId);
}
