package io.auxo.arch.mvvm.viewmodel.command;

import org.junit.Assert;

public class CommandTestUtil {

    public static void printCommandState(StatefulCommand command) {
        System.out.println("command state : " + command.getState());
    }

    public static void assertStateIdle(StatefulCommand.State state) {
        Assert.assertEquals(StatefulCommand.State.IDLE, state);
    }

    public static void assertStateExecuting(StatefulCommand.State state) {
        Assert.assertEquals(StatefulCommand.State.EXECUTING, state);
    }
}
