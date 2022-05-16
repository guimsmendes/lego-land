package com.guimsmendes.legoland.usecase;

import com.guimsmendes.legoland.domain.Profit;
import com.guimsmendes.legoland.domain.Sawmill;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SawmillUseCase {

    private static final Integer MAX_CUT = 3;

    /**
     * <p>Returns a List of Profit object.</p>
     * <p>Each List of Integer object representing
     * one river described in the input returns one
     * Profit object.</p>
     * <p>Each Profit object holds the value of the max
     * profit calculated and the multiple order sets for each
     * series of tree trunk lengths described in the List
     * of Integer object.</p>
     *
     *
     * @return     a List of Profit object containing, for each Profit object,
     *             the max profit calculated and the order sets to achieve the max profit.
     */
    public static List<Sawmill> getMaxProfitList(List<Sawmill> sawmills) {
        sawmills.stream().forEach(Sawmill::calculateMaxProfit);

        return sawmills;
    }
}
