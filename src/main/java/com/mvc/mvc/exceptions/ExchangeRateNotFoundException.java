/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mvc.mvc.exceptions;

/**
 *
 * @author HİDAYET
 */
public class ExchangeRateNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ExchangeRateNotFoundException() {
        super();
    }

    public ExchangeRateNotFoundException(String customMessage) {
        super(customMessage);
    }
}
