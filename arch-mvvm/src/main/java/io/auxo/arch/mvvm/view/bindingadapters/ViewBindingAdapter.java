package io.auxo.arch.mvvm.view.bindingadapters;

import android.databinding.BindingAdapter;
import android.view.View;

import io.auxo.arch.mvvm.viewmodel.command.Command;
import io.auxo.arch.mvvm.viewmodel.command.StatefulCommandWrapper;

public class ViewBindingAdapter {

    @BindingAdapter("clickCommand")
    public static void setClickCommand(View view, final Command command) {
        view.setOnClickListener(v -> {
            if (command != null && command.canExecute()) {
                command.execute();
            }
        });
    }

    @BindingAdapter("clickCommand")
    public static void setClickCommand(View view, final StatefulCommandWrapper command) {
        setClickCommand(view, command, true);
    }

    @BindingAdapter(value = {"clickCommand", "disableOnCommandExecuting"})
    public static void setClickCommand(View view, final StatefulCommandWrapper command,
                                       boolean disableOnCommandExecuting) {
        setClickCommand(view, (Command) command);
        if (disableOnCommandExecuting) {
            view.setEnabled(!command.isExecuting());
        }
    }
}
