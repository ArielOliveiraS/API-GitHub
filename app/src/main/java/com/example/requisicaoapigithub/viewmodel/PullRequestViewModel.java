package com.example.requisicaoapigithub.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.requisicaoapigithub.model.pojo.pull.PullRequest;
import com.example.requisicaoapigithub.repository.GitHubRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PullRequestViewModel extends AndroidViewModel {

    private GitHubRepository gitRepository = new GitHubRepository();
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<PullRequest>> requestList = new MutableLiveData<>();
    private MutableLiveData<Boolean> booleano = new MutableLiveData<>();

    public PullRequestViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<PullRequest>> getRequest() {
        return this.requestList;
    }

    public LiveData<Boolean> getBooleano() {
        return this.booleano;
    }


    public void getPullRequestVM(String creatorString, String repoString) {
        disposable.add(
                gitRepository.getPullRequestRepository(creatorString, repoString)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> booleano.setValue(true))
                        .doOnTerminate(() -> booleano.setValue(false))
                        .subscribe(request1 -> requestList.setValue(request1), throwable -> {
                            Log.i("Log", "erro " + throwable.getMessage());
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
