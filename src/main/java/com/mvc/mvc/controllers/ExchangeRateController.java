/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mvc.mvc.controllers;


import com.mvc.mvc.custom.Admin;
import com.mvc.mvc.custom.ControlExchangeRate;
import org.springframework.ui.Model;
import com.mvc.mvc.entities.ExchangeRate;
import com.mvc.mvc.exceptions.ExchangeRateNotFoundException;

import com.mvc.mvc.services.IExchangeRateService;
import com.mvc.mvc.services.ILogsService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author HİDAYET
 */
@Controller
@RequestMapping("/")
public class ExchangeRateController {

    
    @Autowired
    private Admin admin;
    
    
    @Autowired   
    private IExchangeRateService service;
    
    @Autowired   
    private ILogsService logService;
    
    

    
 
    
    @GetMapping("/adminLogin")
    public String showAdminLogin(Model model) {
        model.addAttribute("admin", admin.isActivated());
        System.out.println("Controller Admin Activated: " + admin.isActivated());
       return "adminLogin";
    }
    
    
    @GetMapping("/adminLogut")
    public String adminLogout() {
        service.handleAdminLogout();
       return "redirect:/";
    }
    
    @PostMapping("/handleAdminLogin")
    public String handleAdminLogin(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            Model model) {

        
        String message = service.handleAdminLogin(userName, password);
        // If login is successful, you can add a message to be displayed in the HTML.
        model.addAttribute("message", message);
        model.addAttribute("admin", admin.isActivated());
        
        // Return the view name or redirect to another page as needed.
        return "adminLogin";
        
    }

    @GetMapping("/")
    public String getAllExchangeRate(
            @RequestParam(value = "message", required = false) String message,
            Model model
            ) {
       List<ExchangeRate> exchangeRates= service.getAllExchangeRate();
       model.addAttribute("list", exchangeRates);
       model.addAttribute("message", message);
      
       model.addAttribute("admin", admin.isActivated());
       //model.addAttribute("adminActivated", admin.get);
       
       return "allExchangeRatesPage";
    }
    @GetMapping("/convert")
    public String convertCurrency(
            
                        Model model,
            RedirectAttributes attributes,
            @RequestParam Long id
           
    
    ) {
        
        String page = null; 
        try {
       ExchangeRate exchangeRate = service.getExchangeRateById(id); // exchangeRate -->> exchangerates
       
       model.addAttribute("exchangeRate", exchangeRate);// exchangeRate -->> exchangerates
       page="converterPage";
       } catch (ExchangeRateNotFoundException e) {
           e.printStackTrace();
           attributes.addAttribute("message", e.getMessage());
           page="redirect:/";
       }
       return page; 

    }
    
    @GetMapping("/result")
    public String resultCurrency(
            
                        Model model,
            RedirectAttributes attributes,
            @RequestParam Long id,
            @RequestParam("userInput") String userInput
    
    ) {
        
        String page = null; 

       ExchangeRate exchangeRate = service.getExchangeRateById(id); 
       Double result = service.getResult(exchangeRate.getExchangeRangeTL(), userInput);
       model.addAttribute("exchangeRate", exchangeRate);
       if (result != null) {
        // result değeri null değilse aşağıdaki kodlar çalışır
        model.addAttribute("resultValue", result);
        
        page = "resultPage";
    } else {
        
        model.addAttribute("message", "Invalid number format.");
        page = "converterPage";
    }
       

       return page; 

    }

@GetMapping("/register")
public String showRegistration() {
   return "registerExchangeRatePage";
}
        




    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Long id
            ) {
       String page = null; 
       try {
       ExchangeRate exchangeRate = service.getExchangeRateById(id); // exchangeRate -->> exchangerates
       ControlExchangeRate controlExchangeRate = new ControlExchangeRate();
       controlExchangeRate.setId(exchangeRate.getId());
       controlExchangeRate.setCurrencyName(exchangeRate.getCurrencyName());
       controlExchangeRate.setCurrencyCode(exchangeRate.getCurrencyCode());
       controlExchangeRate.setExchangeRangeTL(String.valueOf(exchangeRate.getExchangeRangeTL()));

       model.addAttribute("controlExchangeRate", controlExchangeRate);// exchangeRate -->> exchangerates

       page="editExchangeRatePage";
       } catch (ExchangeRateNotFoundException e) {
           e.printStackTrace();
           attributes.addAttribute("message", e.getMessage());
           page="redirect:/";
       }
       return page; 
    }

    @PostMapping("/update")
    public String updateExchangeRate(
            @ModelAttribute ControlExchangeRate controlExchangeRate,
            RedirectAttributes attributes,
            Model model
            ) {

      
       String message = service.updateExchangeRate2(controlExchangeRate);
       model.addAttribute("message",message);
       //logService.saveOneLog(exchangeRate);
       return "editExchangeRatePage";

   
       
    }
    
        @GetMapping("/delete")
    public String deleteExchangeRate(
            @RequestParam Long id,
            RedirectAttributes attributes
            ) {
        try {
        
        ExchangeRate exchangeRate = service.getExchangeRateById(id);
        service.deleteExchangeRateById(id);
        
        attributes.addAttribute("message", "ExchangeRate with Currency Name : '"+exchangeRate.getCurrencyName()+"' is removed successfully!");
        } catch (ExchangeRateNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/";
    }
    
    
    
    @PostMapping("/save")
public String saveExchangeRate(
        @ModelAttribute ControlExchangeRate controlExchangeRate,
        Model model
        //RedirectAttributes attributes
) {
    
        
        String message = service.saveExchangeRate(controlExchangeRate);
        //logService.saveOneLog(exchangeRate);

        model.addAttribute("message", message);

        return "registerExchangeRatePage";

         
    
}



}
