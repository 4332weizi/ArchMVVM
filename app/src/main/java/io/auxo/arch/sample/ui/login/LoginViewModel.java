package io.auxo.arch.sample.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.github.api.model.User;

import io.auxo.arch.mvvm.viewmodel.command.StatefulCommand;
import io.auxo.arch.mvvm.viewmodel.command.builder.CommandBuilder;
import io.auxo.arch.mvvm.viewmodel.livedata.LiveEvent;
import io.auxo.arch.mvvm.viewmodel.livedata.SingleLiveEvent;
import io.auxo.arch.sample.ErrorParser;
import io.auxo.arch.sample.GitHubApp;
import io.auxo.arch.sample.UserManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

/**
 * @author Victor Chiu
 */
public class LoginViewModel extends ViewModel {

    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public final SingleLiveEvent<String> message = new SingleLiveEvent<>();

    private final LiveEvent loginSuccessEvent = new LiveEvent();

    public final StatefulCommand loginCommand = CommandBuilder.stateful()
            .async()
            .dependenceOn(username)
            .dependenceOn(password)
            .canExecute(() -> {
                if (TextUtils.isEmpty(username.get())
                        || TextUtils.isEmpty(password.get())) {
                    return false;
                }
                return true;
            })
            .execute(notifier -> Observable.create(
                    emitter -> {
                        GitHubApp.get().getAuthorizationManager().login(username.get(), password.get());
                        emitter.onNext(new Object());
                        emitter.onComplete();
                    })
                    .flatMap((Function<Object, ObservableSource<User>>) obj ->
                            UserManager.get().syncUserFromServer())
                    .doFinally(() -> notifier.notifyExecuteFinish())
                    .subscribe(result -> loginSuccessEvent.call(),
                            throwable -> {
                                if (throwable instanceof HttpException) {
                                    if (((HttpException) throwable).code() == 401) {
                                        message.setValue("用户名或密码错误");
                                        return;
                                    }
                                }
                                message.setValue(ErrorParser.parse(throwable));
                            }))
            .build();

    public LiveData getLoginSuccessEvent() {
        return loginSuccessEvent;
    }
}
