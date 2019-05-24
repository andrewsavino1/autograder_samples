package com.gradescope.ParallelCheck.tests;

import com.gradescope.jh61b.grader.GradedTestListenerJSON;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// import junit.tests.framework.TestListenerTest

@RunWith(Suite.class)
@Suite.SuiteClasses({
            SumForLoopTest.class,
            })
public class RunTests {
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new GradedTestListenerJSON());
        // runner.addListener(new TestListenerTest());
        Result r = runner.run(RunTests.class);
    }
}
