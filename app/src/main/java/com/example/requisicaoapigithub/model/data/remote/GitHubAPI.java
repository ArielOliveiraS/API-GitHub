package com.example.requisicaoapigithub.model.data.remote;


import com.example.requisicaoapigithub.model.pojo.pull.PullRequest;
import com.example.requisicaoapigithub.model.pojo.repositories.Repositories;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubAPI {

    @GET("search/repositories?q=language:Java&sort=stars")
    Observable<Repositories> getRepositoriosApi(@Query("page") int page);

    @GET("repos/{creator}/{repository}/pulls")
    Observable<List<PullRequest>> getPullRequestsApi(@Path("creator") String creatorString,
                                                     @Path("repository") String repoString);

}
