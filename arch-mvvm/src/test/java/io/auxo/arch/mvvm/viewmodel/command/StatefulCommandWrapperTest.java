package io.auxo.arch.mvvm.viewmodel.command;

import android.databinding.Observable;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static io.auxo.arch.mvvm.viewmodel.command.CommandTestUtil.assertStateExecuting;
import static io.auxo.arch.mvvm.viewmodel.command.CommandTestUtil.assertStateIdle;
import static io.auxo.arch.mvvm.viewmodel.command.CommandTestUtil.printCommandState;

public class StatefulCommandWrapperTest {

    boolean executing = false;

    @Test
    public void testWrapper() throws Exception {
        StatefulCommandWrapper wrapper = new StatefulCommandWrapper(
                () -> System.out.println("command executing")) {

            @Override
            public void execute() {
                executing = false;
                printCommandState(this);
                assertStateIdle(getState());
                super.execute();
                executing = false;
                printCommandState(this);
                assertStateIdle(getState());
            }

            @Override
            public boolean checkAndStart() {
                executing = true;
                return super.checkAndStart();
            }

            @Override
            public boolean checkAndFinish() {
                executing = false;
                return super.checkAndFinish();
            }
        };

        AtomicInteger callbackTimes = new AtomicInteger(0);

        wrapper.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                printCommandState((StatefulCommand) sender);
                callbackTimes.getAndIncrement();
                if (executing) {
                    assertStateExecuting(((StatefulCommand) sender).getState());
                } else {
                    assertStateIdle(((StatefulCommand) sender).getState());
                }
            }
        });

        Assert.assertTrue(wrapper.canExecute());
        wrapper.execute();
        Assert.assertEquals(2, callbackTimes.get());
    }

    @Test
    public void testCanExecute() {
        StatefulCommandWrapper wrapper = new StatefulCommandWrapper(new Command() {
            @Override
            public void execute() {

            }

            @Override
            public boolean canExecute() {
                return false;
            }
        });

        Assert.assertFalse(wrapper.canExecute());
    }
}