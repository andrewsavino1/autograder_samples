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
        return Arrays.stream(theArr).sum();
    }

    public int sumByParallelStream(){
        return Arrays.stream(theArr).parallel().sum();
    }

    public int sumByParallelForLoop() throws ExecutionException, InterruptedException {
        int[] sum = {0,0};
        finish(() -> {
            async(()->{
                for(int i=0; i<theArr.length/2; i++){
                    sum[0] += theArr[i];
                }
            });
            for(int i=theArr.length/2; i<theArr.length; i++){
                sum[1] += theArr[i];
            }
        });
        return sum[0] + sum[1];
    }
}
