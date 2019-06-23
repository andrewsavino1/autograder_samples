package ParallelCheck;

import com.gradescope.ParallelCheck.SumForLoop;
import com.gradescope.jh61b.grader.GradedTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import static edu.wustl.cse231s.v5.V5.launchAppWithReturn;
import static org.junit.Assert.*;

public class SumForLoopTest {
    SumForLoop theSummer;

    @Before
    public void setup(){
        int size = 10000;
        Random random = new Random();
        int[] theTestArr;
        theTestArr = random.ints(size, 0, 10).toArray();
        theSummer = new SumForLoop(theTestArr);
    }

    @Test
    @GradedTest(name="Everything Runs", max_score=1)
    public void testAllTestsCanRun() throws ExecutionException, InterruptedException {
        assertNotNull(launchAppWithReturn(() -> theSummer.sumByParallelForLoop()));
        assertNotNull(theSummer.sumByParallelStream());
        assertNotNull(theSummer.sumBySequentialForLoop());
        assertNotNull(theSummer.sumBySequentialStream());
    }

    @Test
    @GradedTest(name="Sums are correct", max_score=1)
    public void testAllReturnSameSum() throws ExecutionException, InterruptedException {
        // assume that the sequential for loop is definitely correct
        int theTrueSum = theSummer.sumBySequentialForLoop();
        assertEquals(theSummer.sumBySequentialStream(), theTrueSum);
        assertEquals(theSummer.sumByParallelStream(), theTrueSum);
        int parForSum = launchAppWithReturn(() -> theSummer.sumByParallelForLoop());
        assertEquals(parForSum, theTrueSum);
    }

    @Test
    @GradedTest(name="Timing Makes Sense", max_score=1)
    public void testParallelIsFaster() {
        for(int i = 0; i< 20; i++) {
            long mySeqStreamTime = timeIt(() -> theSummer.sumBySequentialStream());
            long myParStreamTime = timeIt(() -> theSummer.sumByParallelStream());
            long mySeqForTime = timeIt(() -> theSummer.sumBySequentialForLoop());
            long myParForTime = timeIt(() -> {
                try {
                    launchAppWithReturn(() -> theSummer.sumByParallelForLoop());
                } catch (Exception e) {
                    //do nothing
                }
            });
            assertTrue(mySeqStreamTime > myParStreamTime);
            assertTrue(mySeqForTime > myParForTime);
        }
    }


    private long timeIt(Runnable aRunnable){
        long StartTime = System.nanoTime();
        aRunnable.run();
        return System.nanoTime() - StartTime;
    }
}
