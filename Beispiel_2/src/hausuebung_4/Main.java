/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Tamara
 */
public class Main {
    
    private static final Scanner scanner = new Scanner(System.in,"Windows-1252");
   
    /**
     * @param args the command line arguments
     */
       
    public static void main(String[] args) {
        
        System.out.println("n> ");
        int number = 0;
        try {
            number = Integer.parseInt(scanner.nextLine()); 
        } catch (NumberFormatException e) {
        }
        //List<Integer> temp =  getCallable(number);
        
        ExecutorService executor = Executors.newCachedThreadPool();
         //List<Future<Integer>> futureList = new ArrayList();
         
        
        int total = 0;
        try {
            for (Future<Integer> future : executor.invokeAll(getCallable(number))) {
                total += future.get();
                //System.out.println(future.get());
            }
        } catch (ExecutionException | InterruptedException ex) { }
        System.out.println("Sum: " + total);
        executor.shutdown();
    }
    
    public static List<AdderCallable> getCallable(Integer number){
        List<AdderCallable> callableList = new ArrayList(); 
           
        for (int i = 1; i < number; i+=100) {
            if(i+99 > number){
                System.out.println("");
                callableList.add(new AdderCallable(i,number));
            } else{
                callableList.add(new AdderCallable(i,i+99));
            }
        }
        
        return callableList;
    }
}
