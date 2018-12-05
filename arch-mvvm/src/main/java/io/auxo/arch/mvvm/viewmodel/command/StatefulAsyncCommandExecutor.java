package io.auxo.arch.mvvm.viewmodel.command;

/**
 * can execute by only one invoker at the same time
 *
 * @author Victor Chiu
 */
public class StatefulAsyncCommandExecutor extends StatefulCommandWrapper {

    public StatefulAsyncCommandExecutor(Command command) {
        super(command);
    }

    @Override
    public void execute() {
        if (checkAndStart()) {
            onExecuteCommand();
        }
    }
}
