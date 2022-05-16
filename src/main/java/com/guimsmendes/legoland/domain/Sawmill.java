package com.guimsmendes.legoland.domain;

import java.util.HashSet;
import java.util.List;

public class Sawmill {
    private static final Integer MAX_CUT = 3;
    private List<Integer> trees;
    private Profit maxProfit;

    public Profit getMaxProfit(){
        return this.maxProfit;
    }

    /**
     * <p>Checks if the first argument that represents the size of the tree trunks are equal the size of them.
     * Returns the list of tree trunks length.</p>
     *
     * @param      list   a List of Integer object representing in the first argument the size of the tree trunks and the
     * other arguments the length of each tree trunk.
     * @return     a List of Integer object representing the length of each tree trunk.
     * @throws     IllegalArgumentException if the size of the tree trunks is different from the first argument.
     */
    public Sawmill(List<Integer> list) {
        if (list.size() - 1 != list.get(0))
            throw new IllegalArgumentException("The first argument should match the number of tree trunks.");
        list.remove(0);
        this.trees = list;
    }

    /**
     * <p>Calculates the max profit for a sawmill and the possible order sets to achieve it
     *  given a series of tree trunks.</p>
     *
     * @return     a Profit object containing the max profit calculated and the order sets to achieve the max profit.
     */
    public void calculateMaxProfit(){
        this.maxProfit = new Profit(0, new HashSet<>());
        calculateProfitRecursively(trees.size());
    }

    /**
     * <p>Calculates recursively the profit of every order set of the given series of tree trunks
     * and updates the maxProfit param with the max profit calculated and the possible order sets to achieve it.</p>
     *
     * @param      n   number of steps for recursive calculation. Initial input: number of given tree trunks.
     */
    private void calculateProfitRecursively(int n) {

        if (n == 1) {
            Integer profitValue = calculateProfit();
            this.maxProfit.updateProfit(this.trees, profitValue);
        } else {
            for (int i = 0; i < n - 1; i++) {
                calculateProfitRecursively(n - 1);
                if ((n % 2 == 0)) {
                    swap(i, n - 1);
                } else {
                    swap(0, n - 1);
                }
            }
            calculateProfitRecursively(n - 1);
        }
    }

    /**
     * <p>Calculates the profit of the sawmill for a given order set of tree trunk lengths.</p>
     *
     * @return     an Integer object representing the calculated profit.
     */
    private Integer calculateProfit() {
        int countCut = 0;
        int profit = 0;

        for (Integer treeLength : this.trees) {
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
     * @param      a   an int representing the first position to be swapped.
     * @param      b   an int representing the second position to be swapped.
     */
    private void swap(int a, int b) {
        Integer tmp = this.trees.get(a);
        this.trees.set(a, this.trees.get(b));
        this.trees.set(b, tmp);
    }
}
