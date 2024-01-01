/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mvc.mvc.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 *
 * @author HİDAYET
 */
@Component
@Getter
@Setter
public class Admin {
    
    private String userName= "admin";
    private String password="admin";
    private boolean activated= false;
    
           
}
