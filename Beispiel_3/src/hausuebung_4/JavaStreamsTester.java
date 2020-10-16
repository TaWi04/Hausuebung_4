/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Tamara
 */
public class JavaStreamsTester {
    
    public static void main(String[] args) {
        List<String> strings = new ArrayList();
        strings.add("Haus");
        strings.add("Husten");
        strings.add("Kasten");
        strings.add("");
        strings.add("Tami");
        strings.add("");
       
        Integer[] integer = {2,3,4,5,3,2,3,9,7,65,4};
        List<Integer> numbers = new ArrayList<>(Arrays.asList(integer));
       
        System.out.println("getCountEmptyString");
        System.out.println(getCountEmptyString(strings));
        
        System.out.println("getCountLength3");
        System.out.println(getCountLength3(strings));
        
        System.out.println("deleteEmptyStrings");
        System.out.println(deleteEmptyStrings(strings));
        
        System.out.println("getMergedString");
        System.out.println(getMergedString(strings,";"));
        
        System.out.println("getSquares");
        System.out.println(getSquares(numbers));
        
        System.out.println("getMax");
        System.out.println(getMax(numbers));
        
        System.out.println("getMin");
        System.out.println(getMin(numbers));
        
        System.out.println("getSum");
        System.out.println(getSum(numbers));
        
        System.out.println("getAverage");
        System.out.println(getAverage(numbers));
    }
    
    private static int getCountEmptyString(List<String> strings){
        return strings.stream()
                .filter(string -> string.equals(""))
                .toArray()
                .length;
    }
    
    private static int getCountLength3(List<String> strings){
        return strings.stream()
                .filter(string -> string.length() == 3)
                .toArray()
                .length;
    }
    
    private static List<String> deleteEmptyStrings(List<String> strings){
        return strings.stream()
                .filter(string -> !string.equals(""))
                .collect(Collectors.toList());
    }
    
    private static String getMergedString(List<String> strings, String separator){
        return strings.stream()
                .collect(Collectors.joining(separator));
    }
    
    private static List<Integer> getSquares(List<Integer> numbers){
        return numbers.stream()
                .map(number -> (int)Math.pow(number, 2))
                .collect(Collectors.toList());
    }
    
    private static int getMax(List<Integer> numbers){
        return numbers.stream()
                .max((n1,n2) -> n1 - n2)
                .orElse(0);
    }
    
    private static int getMin(List<Integer> numbers){
        return numbers.stream()
               .min((n1,n2) -> n1 - n2)
               .orElse(0);
   }
    private static int getSum(List<Integer> numbers){
        return numbers.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }
    private static int getAverage(List<Integer> numbers){
        return numbers.stream()
                .reduce(Integer::sum)
                .map(number -> number / numbers.size())
                .orElse(0);
    }
}
