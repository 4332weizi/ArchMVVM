package io.auxo.arch.mvvm.viewmodel.command.builder;

import android.databinding.Observable;

import io.auxo.arch.mvvm.viewmodel.command.Command;
import io.auxo.arch.mvvm.viewmodel.command.Executable;
import io.auxo.arch.mvvm.viewmodel.command.StatefulCommand;

/**
 * @author Victor
 */
class DefaultStatefulCommand extends StatefulCommand implements Command {

    private boolean isAsyncCommand = false;

    protected CanExecuteChecker mCanExecuteChecker;
    protected Executable mDoOnStart;
    protected Executable mDoOnFinish;
    protected Executable mExecutable;
    protected AsyncExecutable mAsyncExecutable;
    protected ExecuteFinishNotifier mFinishNotifier;

    public DefaultStatefulCommand(Observable... dependencies) {
        this(false, dependencies);
    }

    public DefaultStatefulCommand(boolean isAsync, Observable... dependencies) {
        super(dependencies);
        isAsyncCommand = isAsync;
        if (isAsyncCommand) {
            mFinishNotifier = () -> checkAndFinish();
        }
    }

    public void setDoOnStart(Executable executable) {
        mDoOnStart = executable;
    }

    public void setDoOnFinish(Executable executable) {
        mDoOnFinish = executable;
    }

    public void setCanExecuteChecker(CanExecuteChecker checker) {
        mCanExecuteChecker = checker;
    }

    public void setExecutable(Executable executable) {
        mExecutable = executable;
    }

    public void setAsyncExecutable(AsyncExecutable executable) {
        mAsyncExecutable = executable;
    }

    @Override
    public boolean canExecute() {
        boolean checker = mCanExecuteChecker == null ? true : mCanExecuteChecker.canExecute();
        return !isExecuting() && checker;
    }

    @Override
    public void execute() {
        if (checkAndStart()) {
            try {
                onExecuteCommand();
            } finally {
                if (!isAsyncCommand) {
                    checkAndFinish();
                }
            }
        }
    }

    protected void onExecuteCommand() {
        if (isAsyncCommand) {
            if (mAsyncExecutable != null) {
                mAsyncExecutable.execute(mFinishNotifier);
            }
        } else {
            if (mExecutable != null) {
                mExecutable.execute();
            }
        }
    }

    @Override
    protected void onExecuteStart() {
        super.onExecuteStart();
        if (mDoOnStart != null) {
            mDoOnStart.execute();
        }
    }

    @Override
    protected void onExecuteFinish() {
        super.onExecuteFinish();
        if (mDoOnFinish != null) {
            mDoOnFinish.execute();
        }
    }
}