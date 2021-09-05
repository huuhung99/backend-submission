package com.submissions.back_end.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@RestController
@RequestMapping("/calculator")
public class Calculator {
    @GetMapping("/tong/{so1}/{so2}")
    public String sum(@PathVariable String so1,@PathVariable String so2){
        BigDecimal num1=new BigDecimal(so1);
        return num1.add(new BigDecimal(so2)).toString();
    }
    @GetMapping("/tru/{so1}/{so2}")
    public String subtract(@PathVariable String so1,@PathVariable String so2){
        BigDecimal num1=new BigDecimal(so1);
        return num1.subtract(new BigDecimal(so2)).toString();
    }
    @GetMapping("/nhan/{so1}/{so2}")
    public String multiply(@PathVariable String so1,@PathVariable String so2){
        BigDecimal num1=new BigDecimal(so1);
        return num1.multiply(new BigDecimal(so2)).toString();
    }
    @GetMapping("/chia/{so1}/{so2}")
    public String divide(@PathVariable String so1,@PathVariable String so2){
        BigDecimal num1=new BigDecimal(so1);
        BigDecimal num2 = new BigDecimal(so2);
        BigDecimal divide = num1.divide(num2,10, RoundingMode.HALF_EVEN);
        return divide.toString();
    }
}
