package com.interview.shoppingcart.bo;

import com.interview.shoppingcart.enums.CustomerType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RegularSlab implements slabInterface {
    Map<Double, Integer> discount = new TreeMap<>();

    public RegularSlab(){
        discount.put(5000.00, 0);
        discount.put(10000.00, 10);
        discount.put(100001.00, 20);
    }


    @Override
    public Double calculateFinalCost(Double amount) {
        Double totalDiscount = calculateTotalDiscount(discount, amount);
        return amount-totalDiscount;
    }
}
