package io.auxo.arch.mvvm.viewmodel.command;

/**
 * @author Victor Chiu
 */
public interface Command<T> {
    void execute(T param);
}
