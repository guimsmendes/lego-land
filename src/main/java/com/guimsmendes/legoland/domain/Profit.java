package com.guimsmendes.legoland.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Profit {
    private Integer value;
    private Set<List<Integer>> orderList;

    /**
     * <p>Constructor for the Profit object.</p>
     *
     * @param      value   Integer object representing the value of the profit.
     * @param      orderList   A set of different order sets needed to achieve the profit value.
     */
    public Profit(Integer value, Set<List<Integer>> orderList) {
        this.value = value;
        this.orderList = orderList;
    }

    /**
     * <p>Checks if the given profitValue is greater than the current value of the object.
     * If it is, sets the new value and the given order set to achieve it.
     * If it equals, adds the new given order set.</p>
     *
     * @param      trees   List of Integer object representing the order set of tree trunks thrown in the river.
     * @param      profitValue   Integer object representing the value of the calculated profit for the order set.
     */
    public void updateProfit(List<Integer> trees, Integer profitValue){
        if (profitValue > this.value) {
            this.value = profitValue;
            this.orderList = Set.of(new ArrayList<>(trees));
        } else if (profitValue.equals(this.value)) {
            Set<List<Integer>> newOrderList = new HashSet<>(this.orderList);
            newOrderList.add(new ArrayList<>(trees));
            this.orderList = newOrderList;
        }
    }

    /**
     * @return      Integer object that represents the max profit value.
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @return      Set of different order sets needed to achieve the profit value.
     */
    public Set<List<Integer>> getOrderList() {
        return this.orderList;
    }

    /**
     * <p>Returns the profit for a given tree trunk length.</p>
     *
     * @param      length   Integer object representing the tree trunk length.
     * @return     Integer object representing the profit for a given tree trunk length.
     */
    public static Integer getProfit(Integer length) {
        switch (length) {
            case 1:
                return -1;
            case 2:
                return 3;
            case 3:
                return 1;
            default:
                return 0;
        }
    }
}
