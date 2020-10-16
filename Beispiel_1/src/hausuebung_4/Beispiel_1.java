/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tamara
 */
public class Beispiel_1 {

    /**
     * @param args the command line arguments
     */
    private static final String[] menuDetail = {"Bitte geben Sie eine Zahl für die chunks ein!","Bitte geben Sie eine Zahl als Teiler ein!"};
    private static final Scanner scanner = new Scanner(System.in,"Windows-1252");
    
    public static void main(String[] args) throws Exception {
        ReadFile rd = new ReadFile("numbers.csv");
        
        int[] choice = menu();
       List<List<Integer>> temp =  calcChunkArrays(choice[0], rd.readFileWithStream());
        
        ExecutorService executor = Executors.newCachedThreadPool();
         List<Future<List<Integer>>> futureList = new ArrayList();
         
        List<Callable<List<Integer>>> callableList = new ArrayList();
        for (int i = 0; i < temp.size(); i++) {
            callableList.add(new DivideCallable(choice[1],temp.get(i)));
        }
        
        try {
            for (Future<List<Integer>> future : executor.invokeAll(callableList)) {
                future.get().stream()
                        .forEach(System.out::println);
            }
        } catch (ExecutionException | InterruptedException ex) { }
        executor.shutdown();
}
    
    public static int[] menu(){
        int chunk = 0;
        int divider = 0;
        
        System.out.println(menuDetail[0]);
        try {
             chunk = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Bitte geben Sie eine Zahl ein!");
           return menu();
        }
        
        System.out.println(menuDetail[1]);
        try {
             divider = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Bitte geben Sie eine Zahl ein!");
            return menu();
        }
        return new int[]{chunk, divider};
    }
    
    public static List<List<Integer>> calcChunkArrays(int chunk,List<Integer> numbers){
        List<List<Integer>> numbersWithChunks = new ArrayList<>();
        int numberForArrays = (int) Math.ceil((double)numbers.size()/chunk);
        
        int temp = 0;
        int j = 0;
        while (temp < chunk) {
            List<Integer> temp_Array = new ArrayList<>(numberForArrays);
            for (int i = 0; i < numberForArrays && j< numbers.size(); i++) {
               temp_Array.add(numbers.get(j));
                j++;
            }
            numbersWithChunks.add(temp_Array);
            temp++;
        }

        return numbersWithChunks;
    }
}
