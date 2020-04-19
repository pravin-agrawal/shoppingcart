package com.interview.shoppingcart.bo;

import com.interview.shoppingcart.enums.CustomerType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class PremiumSlab implements slabInterface {
    Map<Double, Integer> discount = new TreeMap<>();

    PremiumSlab(){
        discount.put( 4000.00, 10);
        discount.put( 8000.00, 15);
        discount.put( 12000.00, 20);
        discount.put( 12001.00, 30);
    }

    @Override
    public Double calculateFinalCost(Double amount) {
        Double totalDiscount = calculateTotalDiscount(discount, amount);
        return amount-totalDiscount;
    }


}
