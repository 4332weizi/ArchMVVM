package io.auxo.arch.mvvm.viewmodel.command.builder;

import io.auxo.arch.mvvm.viewmodel.command.Command;
import io.auxo.arch.mvvm.viewmodel.command.Executable;

/**
 * @author Victor
 */
class DefaultCommand implements Command {

    private CanExecuteChecker mExecuteChecker;
    private Executable mExecutable;

    public DefaultCommand(CanExecuteChecker checker, Executable executable) {
        this.mExecuteChecker = checker;
        this.mExecutable = executable;
    }

    @Override
    public void execute() {
        if (mExecutable != null && canExecute()) {
            mExecutable.execute();
        }
    }

    @Override
    public boolean canExecute() {
        return mExecuteChecker == null ? true : mExecuteChecker.canExecute();
    }
}
