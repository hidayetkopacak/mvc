/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mvc.mvc.controllers;


import com.mvc.mvc.entities.ExchangeRate;
import com.mvc.mvc.entities.Logs;
import com.mvc.mvc.exceptions.ExchangeRateNotFoundException;
import com.mvc.mvc.services.ILogsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author HİDAYET
 */
@Controller
@RequestMapping("/exchangerates")
public class LogsController {

    
    @Autowired   
    private ILogsService logService;
    
  @GetMapping("/chart")
    public String getChartPage(
            Model model,
           
            @RequestParam Long id
            ) {
       

       List<Logs> logList = logService.getLogsByExchangeRateId(id);
       model.addAttribute("logList", logList);// exchangeRate -->> exchangerates
       System.out.println(logList);

       
       return "chart"; 
    }

}
