/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mvc.mvc.repos;

import com.mvc.mvc.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author HİDAYET
 */
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    boolean existsByCurrencyName(String currencyName);
    boolean existsByCurrencyCode(String currencyCode);
        // Belirli bir ID hariç diğer verilerde aynı currencyName'e sahip olan kayıtları kontrol et
    boolean existsByCurrencyNameAndIdNot(String currencyName, Long id);

    // Belirli bir ID hariç diğer verilerde aynı currencyCode'a sahip olan kayıtları kontrol et
    boolean existsByCurrencyCodeAndIdNot(String currencyCode, Long id);
}
