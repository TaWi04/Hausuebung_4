/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_4;

import java.util.List;

/**
 *
 * @author Tamara
 */
public class AdderCallable implements java.util.concurrent.Callable<Integer>{
    
    private final int start;
    private final int end;

    public AdderCallable(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public Integer call() throws Exception {
        int total = 0;
        
        for (int i = start; i <= end; i++) {
            total +=i;
        }
        return total;
    }
    
}
