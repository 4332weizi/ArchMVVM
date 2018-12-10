package io.auxo.arch.mvvm.viewmodel.command;

import android.databinding.Observable;

import io.auxo.arch.mvvm.utils.Objects;

/**
 * delegate of Command
 * <p>
 * change command wrapper state before command execute and after command executed
 *
 * @author Victor
 */
public class StatefulCommandWrapper extends StatefulCommand implements Command {

    private boolean isAsyncCommand = false;
    private final Command mCommand;

    public StatefulCommandWrapper(Command command, Observable... dependencies) {
        this(command, false, dependencies);
    }

    public StatefulCommandWrapper(Command command, boolean isAsync, Observable... dependencies) {
        super(dependencies);
        Objects.requireNonNull(command, "command can not be null");
        mCommand = command;
        isAsyncCommand = isAsync;
    }

    @Override
    public boolean canExecute() {
        return !isExecuting() && super.canExecute() && mCommand.canExecute();
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
        mCommand.execute();
    }

}