package com.mgckaled.gerenciadordelivros.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mgckaled.gerenciadordelivros.R;
import com.mgckaled.gerenciadordelivros.adapter.LivroAdapter;
import com.mgckaled.gerenciadordelivros.data.LivroDAO;
import com.mgckaled.gerenciadordelivros.dominio.Livro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LivroDAO livroDAO;
    LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ao chamar o Objeto RecyclerView, o pacote poderá ser importado de forma automática
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        // Setup Recyclerview -> atribuir layout linear
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Adicionar objetos Livros na ArrayList de Lista de Livros.
        // Informar todos os atributos declarados na classe Livro

        /*listaLivros.add(new Livro(1L,"Android para Leigos","Michael Burton","Alta books",0));
        listaLivros.add(new Livro(2L,"Android para Programadores","Paul J, Deitel","Bookman",1));
        listaLivros.add(new Livro(3L,"Desenvolvimento para Android","Griffiths, David","Alta books",0));
        listaLivros.add(new Livro(4L,"Android Base de Dados","Queirós, Ricardo","FCA Editora",1));
        listaLivros.add(new Livro(5L,"Android em Ação","King, Chris","Elsevier - Campus",0));
        listaLivros.add(new Livro(6L,"Jogos em Android","Queirós, Ricardo","FCA - Editora",1));
        listaLivros.add(new Livro(7L,"Android Essencial com Kotlin","Ricardo R.","NOVATEC",0));*/

        livroDAO = LivroDAO.getInstance(this);

        // Criar uma lista de views
        List<Livro> listaLivros = livroDAO.list();

        // Definir o layout de cada linha da RecyclerView e quais são seus respectivos dados
        // Para isso é necessário criar um classe com função adaptativa
        livroAdapter = new LivroAdapter(listaLivros, this);

        recyclerView.setAdapter(livroAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_adicionar:
                Intent intent = new Intent(getApplicationContext(), EditarLivroActivity.class);
                // Quando a tela for fechada, passar os dados para a tela seguinte
                startActivityForResult(intent, 100);
                return true;

            case R.id.action_sair:
                finish(); // terminar aplicativo -> terminar ciclo de vida da Activity.
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Se for true, atualizar lista de livros...
        if (requestCode == 100  && resultCode == RESULT_OK) {
            atualizaListaLivros();

        }
    }

    public void atualizaListaLivros() {
        List<Livro> livros = livroDAO.list();
        livroAdapter.setItens(livros);
        livroAdapter.notifyDataSetChanged();
    }
}