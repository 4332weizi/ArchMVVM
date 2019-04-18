package io.auxo.arch.mvvm.viewmodel.command;

import androidx.databinding.Observable;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import static io.auxo.arch.mvvm.viewmodel.command.CommandTestUtil.assertStateExecuting;
import static io.auxo.arch.mvvm.viewmodel.command.CommandTestUtil.assertStateIdle;
import static io.auxo.arch.mvvm.viewmodel.command.CommandTestUtil.printCommandState;

public class StatefulCommandTest {

    private boolean executing = false;

    private StatefulCommand command = null;

    @Test
    public void testState() throws Exception {

        command = new StatefulCommand() {
            @Override
            public void execute() {
                assertStateIdle(getState());
                executing = true;
                checkAndStart();
                System.out.println("command executing");
                assertStateExecuting(getState());
                executing = false;
                checkAndFinish();
                assertStateIdle(getState());
            }
        };

        printCommandState(command);

        assertStateIdle(command.getState());

        command.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                printCommandState((StatefulCommand) sender);
                if (executing) {
                    assertStateExecuting(((StatefulCommand) sender).getState());
                } else {
                    assertStateIdle(((StatefulCommand) sender).getState());
                }
            }
        });

        Assert.assertTrue(command.canExecute());
        command.execute();
    }

    @Test
    public void testThread() throws Exception {

        int maxThreads = 100;

        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger skipCount = new AtomicInteger(0);

        command = new StatefulCommand() {
            @Override
            public void execute() {
                if (!checkAndStart()) {
                    skipCount.getAndIncrement();
                    return;
                }
                count.getAndIncrement();
                checkAndStart();
                for (; ; ) {

                }
                //checkAndFinish();
            }
        };

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxThreads);
        for (int i = 0; i < maxThreads; i++) {
            executor.submit(command);
        }
        while ((count.get() + skipCount.get()) != maxThreads) {

        }
        Assert.assertEquals(1, count.get());
        Assert.assertEquals(maxThreads - 1, skipCount.get());
    }
}