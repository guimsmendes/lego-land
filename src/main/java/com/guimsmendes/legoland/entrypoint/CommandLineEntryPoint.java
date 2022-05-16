package com.guimsmendes.legoland.entrypoint;

import com.guimsmendes.legoland.domain.Profit;
import com.guimsmendes.legoland.domain.Sawmill;
import com.guimsmendes.legoland.entrypoint.exception.EntryPointException;
import com.guimsmendes.legoland.usecase.SawmillUseCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CommandLineEntryPoint {

    /**
     * <p>Method that receives the input from the Command Line, converts it
     * to the business input, calculates the max profit for the given input
     * and prints it to the console.</p>
     */
    public static void entry() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int lineCount = 1;
        int caseNum = 0;
        while (lineCount > 0) {
            lineCount = Integer.parseInt(bufferedReader.readLine().trim());

            List<String> line = IntStream.range(0, lineCount).mapToObj(i -> {
                        try {
                            return bufferedReader.readLine();
                        } catch (IOException ex) {
                            throw new EntryPointException.ReadLineException("Unable to read line from buffered reader. " + ex);
                        }
                    })
                    .collect(toList());
            List<Sawmill> input = line.stream().map(s -> Arrays.stream(s.split(" "))
                            .map(CommandLineEntryPoint::parseInt)
                            .collect(toList()))
                    .map(Sawmill::new)
                    .collect(toList());
            caseNum++;
            if (lineCount != 0) printMaxProfit(caseNum, input);
        }
        bufferedReader.close();
    }

    /**
     * <p>Parses the String input n to Integer and checks if its a positive number.</p>
     *
     * @param      n   a String object representing a number.
     * @return     a native int representing a positive number Profit.
     * @throws     IllegalArgumentException if n is lower or equals to 0.
     */
    private static int parseInt(String n) {
        int i = Integer.parseInt(n);
        if (i <= 0) throw new IllegalArgumentException("Tree trunks length must be positive");
        return i;
    }

    /**
     * <p>Calculates the max profit for the given input
     * and prints it to the console.</p>
     *
     * @param      caseNum   a native int representing the case number.
     * @param      input a List representing multiple rivers containing in each one
     * a List of Integer representing each tree trunk lengths.
     */
    private static void printMaxProfit(int caseNum, List<Sawmill> input) {
        input = SawmillUseCase.getMaxProfitList(input);

        int totalMaxProfit = input.stream()
                .map(Sawmill::getMaxProfit)
                .map(Profit::getValue)
                .reduce(0, Integer::sum);

        System.out.println("Case " + caseNum);
        System.out.println("Max profit: " + totalMaxProfit);
        System.out.print("Order: ");
        printOrderList(input);
    }

    /**
     * <p>Prints each of the order set for the given input.</p>
     *
     * @param      sawmillList   a List of Profit object containing, for each Profit object,
     * the max profit calculated and the order sets to achieve the max profit.
     */
    private static void printOrderList(List<Sawmill> sawmillList) {
        for (int i = 0; i < sawmillList.size(); i++) {
            for (List<Integer> order : sawmillList.get(i).getMaxProfit().getOrderList()) {
                System.out.print("[");
                for (int j = 0; j < order.size(); j++) {
                    System.out.print(order.get(j));
                    if (j < order.size() - 1) System.out.print(" ");
                }
                System.out.print("]");
            }
            if (i < sawmillList.size() - 1) System.out.print(", ");

        }
        System.out.println();
    }
}
