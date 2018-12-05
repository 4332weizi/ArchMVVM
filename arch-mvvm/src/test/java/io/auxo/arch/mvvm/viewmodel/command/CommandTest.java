package io.auxo.arch.mvvm.viewmodel.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandTest {

    private Calculator mCalculator;

    @Test
    public void execute() {
        assertEquals(mCalculator.result, 0);
        assertTrue(mCalculator.add.canExecute());
        mCalculator.add.execute();
        assertEquals(mCalculator.result, 1);
    }

    @Test
    public void canExecute() {
        assertTrue(mCalculator.add.canExecute());
    }

    @Test
    public void run() {
        assertEquals(mCalculator.result, 0);
        mCalculator.add.run();
        assertEquals(mCalculator.result, 1);
    }

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
    }

    public class Calculator {

        public int result = 0;

        public final Command add = () -> result++;
    }
}