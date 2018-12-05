package io.auxo.arch.mvvm.viewmodel.command;

import io.auxo.arch.mvvm.utils.Objects;

/**
 * delegate of Command
 * <p>
 * change command wrapper state before command execute and after command executed
 *
 * @author Victor
 */
public class StatefulCommandWrapper extends StatefulCommand implements Command {

    private final Command mCommand;

    public StatefulCommandWrapper(Command command) {
        Objects.requireNonNull(command, "command can not be null");
        mCommand = command;
    }

    @Override
    public boolean canExecute() {
        return super.canExecute() && mCommand.canExecute();
    }

    @Override
    public void execute() {
        if (checkAndStart()) {
            onExecuteCommand();
            checkAndFinish();
        }
    }

    protected void onExecuteCommand() {
        mCommand.execute();
    }
}