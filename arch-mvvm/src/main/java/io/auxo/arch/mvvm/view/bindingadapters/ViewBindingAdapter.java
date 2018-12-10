package io.auxo.arch.mvvm.view.bindingadapters;

import android.databinding.BindingAdapter;
import android.view.View;

import io.auxo.arch.mvvm.viewmodel.command.Command;
import io.auxo.arch.mvvm.viewmodel.command.StatefulCommand;

public class ViewBindingAdapter {

    @BindingAdapter("onClickCommand")
    public static void setClickCommand(View view, final Command command) {
        view.setOnClickListener(v -> {
            if (command != null && command.canExecute()) {
                command.execute();
            }
        });
    }

    @BindingAdapter("onClickCommand")
    public static void setClickCommand(View view, final StatefulCommand command) {
        setClickCommand(view, command, true);
    }

    @BindingAdapter(value = {"onClickCommand", "observeCommandState"})
    public static void setClickCommand(View view, final StatefulCommand command,
                                       boolean observeCommandState) {
        setClickCommand(view, (Command) command);
        if (command != null && observeCommandState) {
            view.setEnabled(command.canExecute());
        }
    }
}
