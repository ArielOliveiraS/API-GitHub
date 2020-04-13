package com.example.requisicaoapigithub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requisicaoapigithub.R;
import com.example.requisicaoapigithub.model.pojo.repositories.Item;
import com.example.requisicaoapigithub.view.adapter.RepositoriesAdapter;
import com.example.requisicaoapigithub.view.interfaces.RepositoriesOnClick;
import com.example.requisicaoapigithub.viewmodel.RepositorioViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RepositoriesOnClick {

    private RecyclerView recyclerView;
    private RepositorioViewModel viewModel;
    private RepositoriesAdapter adapter;
    private ProgressBar progressBar;
    private List<Item> itemList = new ArrayList<>();
    public static final String LOGIN = "creator";
    public static final String REPOSITORY = "repositorio";
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        setScrollView();

        viewModel.getRepositoriosVM(page);
        viewModel.getListaRespositorios().observe(this, requestList1 -> {
            adapter.atualizalista(requestList1);
        });

        viewModel.getBooleano().observe(this, aBoolean -> {
            if(aBoolean){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.RecyclerViewRepositories);
        viewModel = ViewModelProviders.of(this).get(RepositorioViewModel.class);
        adapter = new RepositoriesAdapter(itemList, this);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void OnClick(Item result) {
        Intent intent = new Intent(MainActivity.this, PullRequestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(REPOSITORY, result.getName());
        bundle.putString(LOGIN, result.getOwner().getLogin());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setScrollView(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean ultimoItem = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && ultimoItem){
                    page++;
                    viewModel.getRepositoriosVM(page);
                }

            }
        });
    }
}
