package com.gradescope.ParallelCheck;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static edu.wustl.cse231s.v5.V5.async;
import static edu.wustl.cse231s.v5.V5.finish;

public class SumForLoop {
    private int[] theArr;

    public SumForLoop(int[] anArr){
        theArr = anArr;
    }


    public int sumBySequentialForLoop(){
        int sum = 0;
        for(int i=0; i<theArr.length; i++){
            sum += theArr[i];
        }
        return sum;
    }

    public int sumBySequentialStream(){
        return 2;
    }

    public int sumByParallelStream(){
        return 1;
    }

    public int sumByParallelForLoop() throws ExecutionException, InterruptedException {
        return 0;
    }
}
