package io.auxo.arch.mvvm.viewmodel.command.builder;

import android.databinding.Observable;

import java.util.HashSet;
import java.util.Set;

import io.auxo.arch.mvvm.utils.Objects;
import io.auxo.arch.mvvm.viewmodel.command.Command;
import io.auxo.arch.mvvm.viewmodel.command.Executable;
import io.auxo.arch.mvvm.viewmodel.command.StatefulCommand;

/**
 * @author Victor
 */
public class CommandBuilder {

    private CommandBuilder() {
    }

    public static ExecutionStep stateful() {
        return new ExecutionStep();
    }

    public static StatelessCommandBuilder stateless() {
        return new StatelessCommandBuilder();
    }

    public static class ExecutionStep {
        public StatefulCommandBuilder sync() {
            return new StatefulCommandBuilder();
        }

        public StatefulAsyncCommandBuilder async() {
            return new StatefulAsyncCommandBuilder();
        }
    }

    public static class StatelessCommandBuilder {

        protected CanExecuteChecker mCanExecuteChecker;
        protected Executable mExecutable;

        public StatelessCommandBuilder canExecute(CanExecuteChecker checker) {
            mCanExecuteChecker = checker;
            return this;
        }

        public StatelessCommandBuilder execute(Executable executable) {
            mExecutable = executable;
            return this;
        }

        public Command build() {
            return new DefaultCommand(mCanExecuteChecker, mExecutable);
        }
    }

    static abstract class StatefulCommandBaseBuilder<T extends StatefulCommandBaseBuilder> {

        protected Set<Observable> mDependencies = new HashSet<>();
        protected CanExecuteChecker mCanExecuteChecker;
        protected Executable mDoOnStart;
        protected Executable mDoOnFinish;

        public T dependenceOn(Observable observable) {
            Objects.requireNonNull(observable, "dependenceOn observable can not be null");
            mDependencies.add(observable);
            return (T) this;
        }

        public T canExecute(CanExecuteChecker checker) {
            mCanExecuteChecker = checker;
            return (T) this;
        }

        public T doOnStart(Executable executable) {
            mDoOnStart = executable;
            return (T) this;
        }

        public T doOnFinish(Executable executable) {
            mDoOnFinish = executable;
            return (T) this;
        }

        public StatefulCommand build() {
            return onCreateCommand();
        }

        protected Observable[] getDependencies() {
            return mDependencies.toArray(new Observable[mDependencies.size()]);
        }

        protected abstract StatefulCommand onCreateCommand();
    }

    public static class StatefulCommandBuilder extends
            StatefulCommandBaseBuilder<StatefulCommandBuilder> {

        private Executable mExecutable;

        public StatefulCommandBuilder execute(Executable executable) {
            mExecutable = executable;
            return this;
        }

        @Override
        protected StatefulCommand onCreateCommand() {
            DefaultStatefulCommand command = new DefaultStatefulCommand(getDependencies());
            command.setDoOnStart(mDoOnStart);
            command.setDoOnFinish(mDoOnFinish);
            command.setExecutable(mExecutable);
            command.setCanExecuteChecker(mCanExecuteChecker);
            return command;
        }
    }

    public static class StatefulAsyncCommandBuilder extends
            StatefulCommandBaseBuilder<StatefulAsyncCommandBuilder> {

        protected AsyncExecutable mAsyncExecutable;

        public StatefulAsyncCommandBuilder execute(AsyncExecutable executable) {
            mAsyncExecutable = executable;
            return this;
        }

        @Override
        protected StatefulCommand onCreateCommand() {
            DefaultStatefulCommand command = new DefaultStatefulCommand(true, getDependencies());
            command.setDoOnStart(mDoOnStart);
            command.setDoOnFinish(mDoOnFinish);
            command.setAsyncExecutable(mAsyncExecutable);
            command.setCanExecuteChecker(mCanExecuteChecker);
            return command;
        }
    }
}
