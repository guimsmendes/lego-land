package com.guimsmendes.legoland.usecase;

import com.guimsmendes.legoland.domain.Profit;

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
     * @param      totalTrees   a List representing multiple rivers containing in each one
     *                          a List of Integer representing each tree trunk lengths.
     * @return     a List of Profit object containing, for each Profit object,
     *             the max profit calculated and the order sets to achieve the max profit.
     */
    public static List<Profit> getMaxProfitList(List<List<Integer>> totalTrees) {
        List<Profit> profitList = new ArrayList<>();

        for (List<Integer> trees : totalTrees) {
            Profit maxProfit = calculateMaxProfit(trees);
            profitList.add(maxProfit);
        }
        return profitList;
    }

    /**
     * <p>Calculates the max profit for a sawmill and the possible order sets to achieve it
     *  given a series of tree trunks.</p>
     *
     * @param      trees   a List of Integer representing each tree trunk length.
     * @return     a Profit object containing the max profit calculated and the order sets to achieve the max profit.
     */
    private static Profit calculateMaxProfit(List<Integer> trees) {

        Profit maxProfit = new Profit(0, new HashSet<>());
        calculateProfitRecursively(maxProfit, trees.size(), trees);

        return maxProfit;
    }

    /**
     * <p>Calculates recursively the profit of every order set of the given series of tree trunks
     * and updates the maxProfit param with the max profit calculated and the possible order sets to achieve it.</p>
     *
     * @param      maxProfit   object used to represent the max profit value and the possible order sets to achieve it.
     * @param      n   number of steps for recursive calculation. Initial input: number of given tree trunks.
     * @param      trees   a List of Integer representing each tree trunk length.
     */
    private static void calculateProfitRecursively(Profit maxProfit, int n, List<Integer> trees) {

        if (n == 1) {
            Integer profitValue = calculateProfit(trees);
            maxProfit.updateProfit(trees, profitValue);
        } else {
            for (int i = 0; i < n - 1; i++) {
                calculateProfitRecursively(maxProfit, n - 1, trees);
                if ((n % 2 == 0)) {
                    swap(trees, i, n - 1);
                } else {
                    swap(trees, 0, n - 1);
                }
            }
            calculateProfitRecursively(maxProfit, n - 1, trees);
        }
    }

    /**
     * <p>Calculates the profit of the sawmill for a given order set of tree trunk lengths.</p>
     *
     * @param      trees   a List of Integer representing each tree trunk length.
     * @return     an Integer object representing the calculated profit.
     */
    private static Integer calculateProfit(List<Integer> trees) {
        int countCut = 0;
        int profit = 0;

        for (Integer treeLength : trees) {
            countCut += treeLength;
            if (countCut > MAX_CUT) {
                int firstCut = treeLength - (countCut - MAX_CUT);
                profit += Profit.getProfit(firstCut);

                int tempLength = treeLength - firstCut;
                while (tempLength >= MAX_CUT) {
                    tempLength -= MAX_CUT;
                    profit += Profit.getProfit(MAX_CUT);
                }
                profit += Profit.getProfit(tempLength);
                countCut = tempLength;
            } else {
                profit += Profit.getProfit(treeLength);
            }
        }

        return profit;

    }

    /**
     * <p>Swaps two positions from the List of Integer representing the tree trunk.</p>
     *
     * @param      trees   a List of Integer representing each tree trunk length.
     * @param      a   an int representing the first position to be swapped.
     * @param      b   an int representing the second position to be swapped.
     */
    private static void swap(List<Integer> trees, int a, int b) {
        Integer tmp = trees.get(a);
        trees.set(a, trees.get(b));
        trees.set(b, tmp);
    }


}
