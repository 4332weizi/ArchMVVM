package io.auxo.arch.mvvm.viewmodel;

public interface Command<T> {
    void execute(T param);
}
