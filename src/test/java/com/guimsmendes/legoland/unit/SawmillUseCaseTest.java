package com.guimsmendes.legoland.unit;

import com.guimsmendes.legoland.domain.Profit;
import com.guimsmendes.legoland.domain.Sawmill;
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
        Integer[] treeVector = new Integer[]{3,2,3,1};
        List<Integer> trees = new ArrayList<Integer>(Arrays.asList(treeVector));
        Sawmill sawmill = new Sawmill(trees);
        List<Sawmill> totalSawmills = new ArrayList<>();
        totalSawmills.add(sawmill);

        List<Sawmill> sawmillList = SawmillUseCase.getMaxProfitList(totalSawmills);

        assertEquals(1, sawmillList.size());
        assertEquals(4, sawmillList.get(0).getMaxProfit().getValue());
        assertEquals(2, sawmillList.get(0).getMaxProfit().getOrderList().size());
    }

    /**
     * <p>Test representing a case of only one Sawmill with tree trunks thrown in the river, which one of
     * the trunks has a greater size to test the Sawmill cuts.</p>
     */
    @Test
    void longerSizeSawmillTest(){
        Integer[] treeVector = new Integer[]{3,1,9,1};
        List<Integer> trees = new ArrayList<Integer>(Arrays.asList(treeVector));
        Sawmill sawmill = new Sawmill(trees);
        List<Sawmill> totalSawmills = new ArrayList<>();
        totalSawmills.add(sawmill);

        List<Sawmill> sawmillList = SawmillUseCase.getMaxProfitList(totalSawmills);

        assertEquals(1, sawmillList.size());
        assertEquals(2, sawmillList.get(0).getMaxProfit().getValue());
        assertEquals(2, sawmillList.get(0).getMaxProfit().getOrderList().size());
    }

    /**
     * <p>Test representing a case of three Sawmills with tree trunks thrown in each river.</p>
     */
    @Test
    void multipleSawmillsTest(){
        List<Sawmill> sawmillList =  SawmillUseCase.getMaxProfitList(mockMultipleSawmillTrees());

        assertEquals(3, sawmillList.size());
        assertEquals(1, sawmillList.get(0).getMaxProfit().getValue());
        assertEquals(2, sawmillList.get(0).getMaxProfit().getOrderList().size());
        assertEquals(2, sawmillList.get(1).getMaxProfit().getValue());
        assertEquals(2, sawmillList.get(1).getMaxProfit().getOrderList().size());
        assertEquals(5, sawmillList.get(2).getMaxProfit().getValue());
        assertEquals(1, sawmillList.get(2).getMaxProfit().getOrderList().size());

    }

    /**
     * <p>Test representing a case of only one Sawmill with nine trunks thrown in the river.</p>
     */
    @Test
    void greatSizeMultipleSawmillsTest(){
        Integer[] treeVector = new Integer[]{9,1,2,1,3,4,5,1,2,1};
        List<Integer> trees = new ArrayList<Integer>(Arrays.asList(treeVector));
        Sawmill sawmill = new Sawmill(trees);
        List<Sawmill> totalSawmills = new ArrayList<>();
        totalSawmills.add(sawmill);

        List<Sawmill> sawmillList = SawmillUseCase.getMaxProfitList(totalSawmills);

        assertEquals(1, sawmillList.size());
        assertEquals(14, sawmillList.get(0).getMaxProfit().getValue());
        assertEquals(300, sawmillList.get(0).getMaxProfit().getOrderList().size());
    }

    /**
     * <p>Method to mock the multiple inputs for a multiple sawmill.</p>
     */
    private List<Sawmill> mockMultipleSawmillTrees(){
        Integer[] treeVector = new Integer[]{3,1,2,1};
        List<Integer> trees = new ArrayList<Integer>(Arrays.asList(treeVector));
        Sawmill sawmill = new Sawmill(trees);
        List<Sawmill> totalSawmills = new ArrayList<>();
        totalSawmills.add(sawmill);
        treeVector = new Integer[]{2,1,2};
        trees = new ArrayList<Integer>(Arrays.asList(treeVector));
        Sawmill sawmill2 = new Sawmill(trees);
        totalSawmills.add(sawmill2);
        treeVector = new Integer[]{2,1,4};
        trees = new ArrayList<Integer>(Arrays.asList(treeVector));
        Sawmill sawmill3 = new Sawmill(trees);
        totalSawmills.add(sawmill3);
        return totalSawmills;
    }

}
