package io.auxo.arch.mvvm.viewmodel.command.builder;

/**
 * @author Victor
 */
public interface AsyncExecutable {
    void execute(final ExecuteFinishNotifier notifier);
}
