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
       List<int[]> temp =  calcChunkArrays(choice[0], rd.readFileWithStream());
       int h = 0;
       for (int[] is : temp) {
            System.out.println("Array "+h +"                "+ is.length);
            for (int i : is) {
               //System.out.println(i);
           }
            h++;
        }
        
        ExecutorService executor = Executors.newCachedThreadPool();
         List<Future<int[]>> futureList = new ArrayList();
        
         
        //Future<int[]> result = executor.submit();
        List<Callable<int[]>> callableList = new ArrayList();
        for (int i = 0; i < temp.size(); i++) {
            callableList.add(new DivideCallable(choice[1],temp.get(i)));
        }
        
//        for(int i=0; i< 100; i++){
//            try {
//                futureList = executor.invokeAll(callableList);
//                //futureList.add(future);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Beispiel_1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
                try {
        for (final Future<int[]> future :
        executor.invokeAll(callableList)) {
        System.out.println(Arrays.toString(future.get()));
        }
        } catch (ExecutionException | InterruptedException ex) { }
        executor.shutdown();
//        for(Future<int[]> fut : futureList){
//            for (Callable<int[]> callable : callableList) {
                 //System.out.println(.call());
            //}
//            try {
//                //print the return value of Future, notice the output delay in console
//                // because Future.get() waits for task to get completed
//               // System.out.println(fut.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
        
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
    
        public static List<int[]> calcChunkArrays(int chunk,int[] numbers){
                int temp = 0;
            List<int[]> numbersWithChunks = new ArrayList<int[]>();
                 int numberForArrays = (int) Math.ceil((double)(numbers.length/chunk));
                 System.out.println(numbers.length);
                 //System.out.println(numberForArrays);
                 int j = 0;
                while (temp < chunk) {  
                    int[] temp_Array = new int[numberForArrays];
                    for (int i = 0; i < numberForArrays; i++) {
                       temp_Array[i] = numbers[j];
                        j++;
                    }
                    numbersWithChunks.add(temp_Array);
                    temp++;
                }

                 return numbersWithChunks;
        }
}
