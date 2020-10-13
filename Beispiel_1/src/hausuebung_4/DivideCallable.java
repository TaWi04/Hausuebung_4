/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_4;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 *
 * @author Tamara
 */
public class DivideCallable implements Callable<int[]>{
    final private int divider;
    final private int[] numbers;

    public DivideCallable(int divider,int[] numbers) {
        this.divider = divider;
        this.numbers = numbers;
    }

    @Override
    public int[] call() throws Exception {
        Arrays.stream(numbers)
              .filter(number -> (number % divider) == 0);
        return numbers;
    }
}
