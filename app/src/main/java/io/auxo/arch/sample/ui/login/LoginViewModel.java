package io.auxo.arch.sample.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;

import io.auxo.arch.mvvm.viewmodel.command.Command;
import io.auxo.arch.mvvm.viewmodel.command.StatefulAsyncCommandWrapper;
import io.auxo.arch.mvvm.viewmodel.command.StatefulCommand;
import io.auxo.arch.mvvm.viewmodel.command.StatefulCommandWrapper;
import io.auxo.arch.mvvm.viewmodel.livedata.LiveEvent;
import io.auxo.arch.mvvm.viewmodel.livedata.SingleLiveEvent;
import io.auxo.arch.sample.ErrorParser;
import io.auxo.arch.sample.GitHubApp;
import io.auxo.arch.sample.UserManager;
import retrofit2.HttpException;

/**
 * @author Victor Chiu
 */
public class LoginViewModel extends ViewModel {

    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public final SingleLiveEvent<String> message = new SingleLiveEvent<>();

    private final LiveEvent loginSuccessEvent = new LiveEvent();

    public final StatefulAsyncCommandWrapper loginCommand = new StatefulAsyncCommandWrapper(new Command() {

        @Override
        public boolean canExecute() {
            if (TextUtils.isEmpty(username.get())) {
                message.setValue("请输入用户名");
                return false;
            }
            if (TextUtils.isEmpty(password.get())) {
                message.setValue("请输入密码");
                return false;
            }

            try {
                GitHubApp.get().getAuthorizationManager().login(username.get(), password.get());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                message.setValue("用户名密码格式有误");
                return false;
            }

            return true;
        }

        @Override
        public void execute() {
            UserManager.get()
                    .syncUserFromServer()
                    .doFinally(() -> loginCommand.onExecuteFinish())
                    .subscribe(result -> loginSuccessEvent.call(),
                            throwable -> {
                                if (throwable instanceof HttpException) {
                                    if (((HttpException) throwable).code() == 401) {
                                        message.setValue("用户名或密码错误");
                                        return;
                                    }
                                }
                                message.setValue(ErrorParser.parse(throwable));
                            });
        }
    });

    public LiveData getLoginSuccessEvent() {
        return loginSuccessEvent;
    }
}
