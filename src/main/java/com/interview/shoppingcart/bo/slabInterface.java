package com.interview.shoppingcart.bo;

import com.interview.shoppingcart.enums.CustomerType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface slabInterface {

    default Map<Double, List<Integer>> calculateDiscountRate(Map<Double, Integer> discountSlab, Double amount) {
        Map<Double, List<Integer>> amountDiscountMap = new HashMap<>();
        Double lastSlabAmount = 0.0;
        for(Map.Entry<Double, Integer> entry : discountSlab.entrySet()){
            if(amount > 0){
                double applicableDiscountAmount = entry.getKey() - lastSlabAmount;
                if(amount >= applicableDiscountAmount || applicableDiscountAmount == 1.00 ){ //applicableDiscountAmount == 1 means its the last slab
                    lastSlabAmount = entry.getKey();
                    if(amountDiscountMap.containsKey(applicableDiscountAmount)){
                        amountDiscountMap.get(applicableDiscountAmount).add(entry.getValue());
                    }else{
                        double discountamount = applicableDiscountAmount == 1 ? amount : applicableDiscountAmount;
                        addDiscountMapEntry(amountDiscountMap, entry, discountamount);
                    }
                }else{
                    if(amountDiscountMap.containsKey(amount)){
                        amountDiscountMap.get(amount).add(entry.getValue());
                    }else{
                        addDiscountMapEntry(amountDiscountMap, entry, amount);
                    }
                }
                amount = applicableDiscountAmount == 1 ? -1 : amount-applicableDiscountAmount;
            }
        }
        return amountDiscountMap;
    }


    default void addDiscountMapEntry(Map<Double, List<Integer>> amountDiscountMap, Map.Entry<Double, Integer> entry, double applicableDiscountAmount) {
        List<Integer> discountList  = new ArrayList<>();
        discountList.add(entry.getValue());
        amountDiscountMap.put(applicableDiscountAmount,discountList );
    }

    default Double calculateTotalDiscount(Map<Double, Integer> discountSlab, Double amount){
        Double totalDiscountAmount = 0.0;
        Map<Double, List<Integer>> amountDiscountMap = calculateDiscountRate(discountSlab, amount);
        for(Map.Entry<Double, List<Integer>> entry : amountDiscountMap.entrySet()){
            //totalDiscountAmount = entry.getValue().stream().map(discountRate -> (discountRate * entry.getKey())/100).mapToDouble(i -> i).sum();
            for(Integer discountRate : entry.getValue()){
                totalDiscountAmount += (discountRate * entry.getKey())/100;
            }
        }
        return totalDiscountAmount;
    }

    public Double calculateFinalCost(Double amount);
}
