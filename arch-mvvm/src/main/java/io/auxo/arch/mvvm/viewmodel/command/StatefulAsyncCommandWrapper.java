package io.auxo.arch.mvvm.viewmodel.command;

/**
 * can execute by only one invoker at the same time
 *
 * @author Victor Chiu
 */
public class StatefulAsyncCommandWrapper extends StatefulCommandWrapper {

    public StatefulAsyncCommandWrapper(Command command) {
        super(command);
    }

    @Override
    public void execute() {
        if (checkAndStart()) {
            onExecuteCommand();
        }
    }

    public void onExecuteFinish(){
        checkAndFinish();
    }
}
