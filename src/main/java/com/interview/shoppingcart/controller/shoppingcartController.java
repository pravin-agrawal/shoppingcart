package com.interview.shoppingcart.controller;

import com.interview.shoppingcart.bo.PremiumSlab;
import com.interview.shoppingcart.bo.RegularSlab;
import com.interview.shoppingcart.enums.CustomerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class shoppingcartController {

    @Autowired
    PremiumSlab premiumSlab;

    @Autowired
    RegularSlab regularSlab;

    @RequestMapping("/calculateAmount")
    public double amountCalculator(@RequestParam Double amount, @RequestParam CustomerType type){
        Integer applicableDiscountRate;
        if(CustomerType.Premium.name().equals(type)){
            return  premiumSlab.calculateFinalCost(amount);
        }else if(CustomerType.Regular.name().equals(type)){
            return regularSlab.calculateFinalCost(amount);
        }
        return amount;
    }
}
