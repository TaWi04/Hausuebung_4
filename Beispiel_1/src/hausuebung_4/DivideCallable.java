/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Tamara
 */
public class DivideCallable implements Callable<List<Integer>>{
    final private int divider;
    final private List<Integer> numbers;

    public DivideCallable(int divider,List<Integer> numbers) {
        this.divider = divider;
        this.numbers = numbers;
    }

    @Override
    public List<Integer> call() throws Exception {
        return numbers.stream()
            .filter(number -> (number % divider) == 0)
            .collect(Collectors.toList());
    }
}
