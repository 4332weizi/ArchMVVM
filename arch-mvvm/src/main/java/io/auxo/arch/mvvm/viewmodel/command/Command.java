package io.auxo.arch.mvvm.viewmodel.command;

public interface Command<T> {
    void execute(T param);
}
