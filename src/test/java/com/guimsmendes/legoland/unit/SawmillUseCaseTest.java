package com.guimsmendes.legoland.unit;

import com.guimsmendes.legoland.domain.Profit;
import com.guimsmendes.legoland.usecase.SawmillUseCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SawmillUseCaseTest {

    /**
     * <p>Test representing a case of only one Sawmill with tree trunks thrown in the river.</p>
     */
    @Test
    void oneSawmillTest(){
        Integer[] treeVector = new Integer[]{2,3,1};
        List<Integer> trees = Arrays.asList(treeVector);
        List<List<Integer>> totalTrees = new ArrayList<>();
        totalTrees.add(trees);

        List<Profit> profitList = SawmillUseCase.getMaxProfitList(totalTrees);

        assertEquals(1, profitList.size());
        assertEquals(4, profitList.get(0).getValue());
        assertEquals(2, profitList.get(0).getOrderList().size());
    }

    /**
     * <p>Test representing a case of only one Sawmill with tree trunks thrown in the river, which one of
     * the trunks has a greater size to test the Sawmill cuts.</p>
     */
    @Test
    void longerSizeSawmillTest(){
        Integer[] treeVector = new Integer[]{1,9,1};
        List<Integer> trees = Arrays.asList(treeVector);
        List<List<Integer>> totalTrees = new ArrayList<>();
        totalTrees.add(trees);

        List<Profit> profitList = SawmillUseCase.getMaxProfitList(totalTrees);

        assertEquals(1, profitList.size());
        assertEquals(2, profitList.get(0).getValue());
        assertEquals(2, profitList.get(0).getOrderList().size());
    }

    /**
     * <p>Test representing a case of three Sawmills with tree trunks thrown in each river.</p>
     */
    @Test
    void multipleSawmillsTest(){
        List<Profit> profitList = SawmillUseCase.getMaxProfitList(mockMultipleSawmillTrees());

        assertEquals(3, profitList.size());
        assertEquals(1, profitList.get(0).getValue());
        assertEquals(2, profitList.get(0).getOrderList().size());
        assertEquals(2, profitList.get(1).getValue());
        assertEquals(2, profitList.get(1).getOrderList().size());
        assertEquals(5, profitList.get(2).getValue());
        assertEquals(1, profitList.get(2).getOrderList().size());

    }

    /**
     * <p>Test representing a case of only one Sawmill with nine trunks thrown in the river.</p>
     */
    @Test
    void greatSizeMultipleSawmillsTest(){
        Integer[] treeVector = new Integer[]{1,2,1,3,4,5,1,2,1};
        List<Integer> trees = Arrays.asList(treeVector);
        List<List<Integer>> totalTrees = new ArrayList<>();
        totalTrees.add(trees);

        List<Profit> profitList = SawmillUseCase.getMaxProfitList(totalTrees);

        assertEquals(1, profitList.size());
        assertEquals(14, profitList.get(0).getValue());
        assertEquals(300, profitList.get(0).getOrderList().size());
    }

    /**
     * <p>Method to mock the multiple inputs for a multiple sawmill.</p>
     */
    private List<List<Integer>> mockMultipleSawmillTrees(){
        Integer[] treeVector = new Integer[]{1,2,1};
        List<Integer> trees = Arrays.asList(treeVector);
        List<List<Integer>> totalTrees = new ArrayList<>();
        totalTrees.add(trees);
        treeVector = new Integer[]{1,2};
        trees = Arrays.asList(treeVector);
        totalTrees.add(trees);
        treeVector = new Integer[]{1,4};
        trees = Arrays.asList(treeVector);
        totalTrees.add(trees);
        return totalTrees;
    }

}
