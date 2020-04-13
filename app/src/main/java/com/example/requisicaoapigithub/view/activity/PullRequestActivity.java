package com.example.requisicaoapigithub.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.requisicaoapigithub.R;
import com.example.requisicaoapigithub.model.pojo.pull.PullRequest;
import com.example.requisicaoapigithub.view.adapter.PullRequestAdapter;
import com.example.requisicaoapigithub.view.interfaces.PullRequestOnclick;
import com.example.requisicaoapigithub.viewmodel.PullRequestViewModel;
import java.util.ArrayList;
import java.util.List;
import static com.example.requisicaoapigithub.view.activity.MainActivity.LOGIN;
import static com.example.requisicaoapigithub.view.activity.MainActivity.REPOSITORY;


public class PullRequestActivity extends AppCompatActivity implements PullRequestOnclick {

    private List<PullRequest> pullRequestList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PullRequestAdapter adapter;
    private PullRequestViewModel viewModel;
    private ProgressBar progressBar;
    private String login;
    private String repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        initViews();

        login = getIntent().getExtras().getString(LOGIN);
        repository = getIntent().getExtras().getString(REPOSITORY);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getPullRequestVM(login, repository);
        viewModel.getRequest().observe(this, requestList -> {
            adapter.atualizaLista(requestList);
        });

        viewModel.getBooleano().observe(this, aBoolean -> {
            if (aBoolean) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void initViews() {
        viewModel = ViewModelProviders.of(this).get(PullRequestViewModel.class);
        adapter = new PullRequestAdapter(pullRequestList, this);
        recyclerView = findViewById(R.id.RecyclerViewPR);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void OnClick(PullRequest pullRequest) {
        String url = pullRequest.getHtmlUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}