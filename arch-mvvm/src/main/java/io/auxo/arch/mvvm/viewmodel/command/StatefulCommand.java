package io.auxo.arch.mvvm.viewmodel.command;

import androidx.databinding.BaseObservable;
import androidx.databinding.Observable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * command with state
 *
 * @author Victor
 */
public abstract class StatefulCommand extends BaseObservable implements Command {

    private AtomicBoolean mCommandExecutingFlag = new AtomicBoolean(false);

    public StatefulCommand() {

    }

    public StatefulCommand(Observable... dependencies) {
        if (dependencies != null && dependencies.length != 0) {
            DependencyCallback callback = new DependencyCallback();

            for (int i = 0; i < dependencies.length; i++) {
                dependencies[i].addOnPropertyChangedCallback(callback);
            }
        }
    }

    @Override
    public boolean canExecute() {
        return !mCommandExecutingFlag.get();
    }

    protected boolean checkAndStart() {
        if (canExecute() && mCommandExecutingFlag.compareAndSet(false, true)) {
            onExecuteStart();
            notifyChange();
            return true;
        }
        return false;
    }

    protected boolean checkAndFinish() {
        if (mCommandExecutingFlag.compareAndSet(true, false)) {
            onExecuteFinish();
            notifyChange();
            return true;
        }
        return false;
    }

    public State getState() {
        return isExecuting() ? State.EXECUTING : State.IDLE;
    }

    public boolean isExecuting() {
        return mCommandExecutingFlag.get();
    }

    protected void onExecuteStart() {

    }

    protected void onExecuteFinish() {

    }

    class DependencyCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            notifyChange();
        }
    }

    public enum State {
        IDLE(-1), EXECUTING(0);

        private int value;

        State(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}