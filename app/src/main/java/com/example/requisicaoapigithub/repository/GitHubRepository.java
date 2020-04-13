package com.example.requisicaoapigithub.repository;

import com.example.requisicaoapigithub.model.pojo.pull.PullRequest;
import com.example.requisicaoapigithub.model.pojo.repositories.Repositories;

import java.util.List;

import io.reactivex.Observable;

import static com.example.requisicaoapigithub.model.data.remote.RetrofitService.getApiService;

public class GitHubRepository {

    public Observable<Repositories> getRepositoriosRepository(int page) {
        return getApiService().getRepositoriosApi(page);
    }

    public Observable<List<PullRequest>> getPullRequestRepository(String creatorString, String repoString){
        return getApiService().getPullRequestsApi(creatorString, repoString);
    }


}
