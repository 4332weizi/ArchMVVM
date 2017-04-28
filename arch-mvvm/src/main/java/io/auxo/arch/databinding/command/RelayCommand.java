package io.auxo.arch.databinding.command;

public class RelayCommand<T extends Action> implements Command {

    private T action;

    public RelayCommand(T action) {
        this.action = action;
    }

    @Override
    public void execute() {
        action.apply();
    }
}
