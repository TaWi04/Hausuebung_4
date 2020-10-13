/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Tamara
 */
public class ReadFile {
    final private String fileName;
    final  List<Integer> numbers = new ArrayList<>();

    public ReadFile(String fileName) {
        this.fileName = fileName;
    }
   public int[] readFileWithStream(){ // new
       try {
            File file = new File(fileName);
            
            if (!file.canRead() || !file.isFile()) {
                System.out.println("ERROR!");
                System.exit(0);
            }
            Files.lines(new File("numbers.csv").toPath())
                    .map(s -> s.split(":"))
                    .forEach(s -> Arrays.stream(s)
                                .forEach(string ->{
                                    try {
                                        numbers.add(Integer.parseInt(string));
                                    } catch (NumberFormatException e) {
                                    }}));
                    } catch (IOException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
       int[] numbersArray = new int[numbers.size()];
       for (int i = 0; i < numbers.size();i++) {
           numbersArray[i] = numbers.get(i);
       }
        return numbersArray;
   }
}
