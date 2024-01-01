/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mvc.mvc.services.impl;

import com.mvc.mvc.custom.Admin;
import com.mvc.mvc.custom.ControlExchangeRate;
import com.mvc.mvc.entities.ExchangeRate;
import com.mvc.mvc.exceptions.ExchangeRateNotFoundException;
import com.mvc.mvc.repos.ExchangeRateRepository;
import com.mvc.mvc.services.IExchangeRateService;
import com.mvc.mvc.services.ILogsService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author HİDAYET
 */


@Service
public class ExchangeRateServiceImpl implements IExchangeRateService {
    
    @Autowired
    private Admin existingAdmin;
    
    @Autowired
    private ExchangeRateRepository repo;
    
    @Autowired   
    private ILogsService logService;
    
    // Buraya adın mı daha önceden exist yoksa code'un mudaha önceden exist olduğunun kontrolü yapılmalı. Update'den bakarak yap.
    
    @Override
    public void handleAdminLogout(){
        existingAdmin.setActivated(false);
        
    }
    
    @Override
    public String handleAdminLogin(
            String userName,
            String password
            
    
    ) {
        System.out.println("Input Admin Username: " + userName);
        System.out.println("Input Admin Activated: " + password);
        System.out.println("Existing Admin Username: " + existingAdmin.getUserName());
        System.out.println("Existing Admin Activated: " + existingAdmin.isActivated());
        if (userName.equals(existingAdmin.getUserName()) && password.equals(existingAdmin.getPassword())){
            existingAdmin.setActivated(true);
            String message = "Login successfull.";
            return message;
        } else {
            
            String message = "Incorrect username and password.";
            return message;
            
        }
        
        
        
        
  

    }
    
    
    
    @Override
    public String saveExchangeRate(
            ControlExchangeRate controlExchangeRate
    ) {
        if (existingAdmin.isActivated()){
        
        double exchangeRangeTL;
    try {
        exchangeRangeTL = Double.parseDouble(controlExchangeRate.getExchangeRangeTL());
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrencyName(controlExchangeRate.getCurrencyName());
        exchangeRate.setCurrencyCode(controlExchangeRate.getCurrencyCode());
        exchangeRate.setExchangeRangeTL(exchangeRangeTL);
            if (exchangeRangeTL>0 ) {
       if (repo.existsByCurrencyName(exchangeRate.getCurrencyName()) || repo.existsByCurrencyCode(exchangeRate.getCurrencyCode())) {

           String message = "Currency '" + exchangeRate.getCurrencyName() + " " + exchangeRate.getCurrencyCode() + "' is already in the database.";
        return message;
    }
       else{
       
        repo.save(exchangeRate);
        String message = "Record with id : '"+exchangeRate.getId()+"' is saved successfully !";
        logService.saveOneLog(exchangeRate);
        return message;

       }


    }
   else{
       String message = "The number that you have entered can not be negative or zero.";
       return message;
   }
        
        
        
        
    } catch (NumberFormatException e) {
        return "Invalid value. Please enter a valid number.";
    }
        

        
        
        
        }else {
        
            return "You don't have permisson to do that.";
        }
        
        


        
        
        
        
       
   
    }
    
    

    @Override
    public List<ExchangeRate> getAllExchangeRate() {
       return repo.findAll();
    }

    @Override
    public ExchangeRate getExchangeRateById(Long id) {
       Optional<ExchangeRate> opt = repo.findById(id);
       if(opt.isPresent()) {
           return opt.get();
       } else {
           throw new ExchangeRateNotFoundException("Currency with Id : "+id+" Not Found");
       }
    }
    
    @Override
public Double getResult(Double amount, String userInput) {
    try {
        // Double.parseDouble() kullanımı
        double userInputAsDouble = Double.parseDouble(userInput);
        
        // Dönüştürülen değeri kullanarak işlem yapabilirsiniz
        return amount * userInputAsDouble;
    } catch (NumberFormatException e) {
        // Eğer dönüştürme hatası alınırsa burada uygun bir işlem yapabilirsiniz
        System.err.println("Hata: Geçersiz sayı formatı");
        
        return null; // veya başka bir değer, işleme bağlı olarak
    }
}
    
    @Override
    public void deleteExchangeRateById(Long id) {
        if (existingAdmin.isActivated()){
            repo.delete(getExchangeRateById(id)); 
        }
       
    }

    
        @Override
    public String updateExchangeRate2(ControlExchangeRate controlExchangeRate) {
        
        if (existingAdmin.isActivated()){
            
            double exchangeRangeTL;
        try {
            // Validasyon
            exchangeRangeTL = Double.parseDouble(controlExchangeRate.getExchangeRangeTL());
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setId(controlExchangeRate.getId());
            exchangeRate.setCurrencyName(controlExchangeRate.getCurrencyName());
            exchangeRate.setCurrencyCode(controlExchangeRate.getCurrencyCode());
            exchangeRate.setExchangeRangeTL(exchangeRangeTL);
            
            ExchangeRate existingExchangeRate = getExchangeRateById(exchangeRate.getId());
       
       if (exchangeRate.getExchangeRangeTL()>0 ){
           if (repo.existsByCurrencyName(exchangeRate.getCurrencyName()) || repo.existsByCurrencyCode(exchangeRate.getCurrencyCode())) {
               
               
               // == kullanmama sebebi, java == ile referans bazında karşılaştırma yapar
               if (existingExchangeRate.getCurrencyName().equals(exchangeRate.getCurrencyName()) && existingExchangeRate.getCurrencyCode().equals(exchangeRate.getCurrencyCode()) ) {
                   repo.save(exchangeRate);
                String message = "Record with id : '"+exchangeRate.getId()+"' is saved successfully !";
                logService.saveOneLog(exchangeRate);
                return message;
               }else {
                   if (repo.existsByCurrencyNameAndIdNot(exchangeRate.getCurrencyName(),existingExchangeRate.getId())){
                   
                        String message = "Currency Name '" + exchangeRate.getCurrencyName()+"' has alrady exist.";
                    return message;
                   }else if (repo.existsByCurrencyCodeAndIdNot(exchangeRate.getCurrencyCode(),existingExchangeRate.getId())){
                   
                        String message = "Currency Code '" + exchangeRate.getCurrencyCode()+"' has alrady exist.";
                    return message;
                   }
                   else{
                        repo.save(exchangeRate);
                        
                        String message = "Record with id : '"+exchangeRate.getId()+"' is saved successfully !";
                        logService.saveOneLog(exchangeRate);
                        return message;
                   }
            
              
               }
               
        }
           else{
                repo.save(exchangeRate);
                String message = "Record with id : '"+exchangeRate.getId()+"' is saved successfully !";
                logService.saveOneLog(exchangeRate);
                return message;
       }
       }
       else{
           String message = "The number that you have entered can not be negative or zero.";
           return message;
       }
        }
        catch (NumberFormatException e) {
        return "Invalid value. Please enter a valid number.";
    }
        
       
    }else {
        return "You dont have permission to do that.";
        
        }
            
            
        }
        
    
}
