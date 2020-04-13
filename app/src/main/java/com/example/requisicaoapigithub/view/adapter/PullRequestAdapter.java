package com.example.requisicaoapigithub.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requisicaoapigithub.R;
import com.example.requisicaoapigithub.model.pojo.pull.PullRequest;
import com.example.requisicaoapigithub.view.interfaces.PullRequestOnclick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder> {

    private List<PullRequest> pullRequestList;
    private PullRequestOnclick listener;

    public PullRequestAdapter(List<PullRequest> pullRequestList, PullRequestOnclick listener) {
        this.pullRequestList = pullRequestList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PullRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pull_request, parent, false);
        return new PullRequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestAdapter.ViewHolder holder, int position) {
        final PullRequest pullRequest = pullRequestList.get(position);
        holder.onBind(pullRequest);
        holder.itemView.setOnClickListener(v -> listener.OnClick(pullRequest));
    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }

    public void atualizaLista(List<PullRequest> novaLista) {
        if (this.pullRequestList.isEmpty()) {
            this.pullRequestList = novaLista;
        } else {
            this.pullRequestList.addAll(novaLista);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUsuario;
        TextView titulo;
        TextView descricao;
        TextView nomeUsuario;
        TextView nomeSobrenome;
        TextView data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTituloPull);
            descricao = itemView.findViewById(R.id.txtDescricaoPull);
            nomeUsuario = itemView.findViewById(R.id.txtUsuarioPull);
            nomeSobrenome = itemView.findViewById(R.id.txtNomeSobrenome);
            imgUsuario = itemView.findViewById(R.id.imgFotoPerfilPull);
            data = itemView.findViewById(R.id.txtData);
        }

        public void onBind(PullRequest pullRequest) {
            titulo.setText(pullRequest.getTitle());
            descricao.setText(pullRequest.getBody());
            nomeUsuario.setText(pullRequest.getUser().getLogin());
            nomeSobrenome.setText(pullRequest.getHead().getRepo().getFullName());
            Picasso.get().load(pullRequest.getUser().getAvatarUrl()).into(imgUsuario);

            String[] date = pullRequest.getCreatedAt().split("T");
            data.setText(date[0]);

        }
    }
}
