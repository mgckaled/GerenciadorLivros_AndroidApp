package com.mgckaled.gerenciadordelivros.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mgckaled.gerenciadordelivros.R;
import com.mgckaled.gerenciadordelivros.dominio.Livro;

import java.util.List;

// Função: adaptar a coleção de objetos (livros) a cada linha do layout
// Padrão View Holder irá reutilizar as views que se apresentam na tela
public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroHolder> {

    private List<Livro> livros;
    private Context context;

    private OnLivroListener onLivroListener;

    public LivroAdapter(List<Livro> livros, Context context, OnLivroListener onLivroListener) {
        this.livros = livros;
        this.context = context;
        this.onLivroListener = onLivroListener;
    }

    // Configuração do Layout e criação do objeto LivroHolder
    @NonNull
    @Override
    public LivroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context)
                .inflate(R.layout.item_livro, parent, false);

        LivroHolder livroHolder = new LivroHolder(view, onLivroListener);

        return livroHolder;
    }

    // Reaproveitamento das views e a inclusão das respectivas informações de cada
    // objeto da lista de Livros
    @Override
    public void onBindViewHolder(@NonNull LivroHolder holder, int position) {

        Livro livro = livros.get(position);

        holder.txtTitulo.setText(livro.getTitulo());
        holder.txtAutor.setText(livro.getAutor());
        holder.txtEditora.setText(livro.getEditora());

        if (livro.getEmprestado() == 1) {
            holder.ic_livro.setColorFilter(Color.GRAY);
            holder.ic_star.setVisibility(View.VISIBLE);
        } else {
            holder.ic_livro.setColorFilter(Color.parseColor("#04C4D9"));
            holder.ic_star.setVisibility(View.INVISIBLE);
        }

    }

    // Retorna o tamanho da lista de livros.
    @Override
    public int getItemCount() {
        return livros.size();
    }

    public void setItens(List<Livro> livros) {
        this.livros = livros;
    }

    public Livro getItem(int posicao) {
        return livros.get(posicao);
    }


    // Irá representar cada linha da lista de livros
    public class LivroHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        // Parâmetros
        public TextView txtTitulo;
        public TextView txtAutor;
        public TextView txtEditora;
        public ImageView ic_livro;
        public ImageView ic_star;

        public OnLivroListener onLivroListener;

        // Método que representa cada linha de livro na tela
        public LivroHolder(@NonNull View view, OnLivroListener onLivroListener) {
            super(view);

            // Atribuição de variáveis a cada elemento view do arquivo .xml
            txtTitulo = view.findViewById(R.id.txtTitulo);
            txtAutor = view.findViewById(R.id.txtAutor);
            txtEditora = view.findViewById(R.id.txtEditora);
            ic_livro = view.findViewById(R.id.ic_livro);
            ic_star = view.findViewById(R.id.ic_star);

            this.onLivroListener = onLivroListener;

            // implementar funcionalidade de onclicklistener para abertura de uma nova
            // screen para objeto Livro
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onLivroListener.onLivroClick(getAdapterPosition());

        }

        @Override
        public boolean onLongClick(View v) {
            onLivroListener.onLivroLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnLivroListener {

        void onLivroClick(int posicao);

        void onLivroLongClick(int posicao);

    }
}
