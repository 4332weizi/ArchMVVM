package io.auxo.arch.mvvm.viewmodel.command;

import android.databinding.BaseObservable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * command with state
 *
 * @author Victor
 */
public abstract class StatefulCommand extends BaseObservable implements Command {

    private AtomicBoolean mCommandExecutingFlag = new AtomicBoolean(false);

    @Override
    public boolean canExecute() {
        return !mCommandExecutingFlag.get();
    }

    public boolean checkAndStart() {
        if (mCommandExecutingFlag.compareAndSet(false, true)) {
            notifyChange();
            return true;
        }
        return false;
    }

    public boolean checkAndFinish() {
        if (mCommandExecutingFlag.compareAndSet(true, false)) {
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