package io.auxo.arch.mvvm.viewmodel.command;

/**
 * @author Victor Chiu
 */
public interface Command extends Runnable {

    void execute();

    default boolean canExecute() {
        return true;
    }

    @Override
    default void run() {
        execute();
    }
}