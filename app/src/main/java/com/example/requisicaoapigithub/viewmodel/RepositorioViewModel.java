package com.example.requisicaoapigithub.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.requisicaoapigithub.model.pojo.repositories.Item;
import com.example.requisicaoapigithub.repository.GitHubRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RepositorioViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> listaDeRepositorios = new MutableLiveData<>();
    private GitHubRepository gitRepository = new GitHubRepository();
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Boolean> booleano = new MutableLiveData<>();

    public RepositorioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getListaRespositorios() {
        return this.listaDeRepositorios;
    }

    public LiveData<Boolean> getBooleano() {
        return this.booleano;
    }

    public void getRepositoriosVM(int page) {

        disposable.add(
                gitRepository.getRepositoriosRepository(page)

                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> booleano.setValue(true))
                        .doOnTerminate(() -> booleano.setValue(false))
                        .subscribe(gitResult -> listaDeRepositorios.setValue(gitResult.getItems()),
                                throwable -> Log.i("LOG", "Error: " + throwable.getMessage())));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}