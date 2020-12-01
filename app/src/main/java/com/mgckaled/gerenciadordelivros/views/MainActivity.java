package com.mgckaled.gerenciadordelivros.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mgckaled.gerenciadordelivros.R;
import com.mgckaled.gerenciadordelivros.adapter.LivroAdapter;
import com.mgckaled.gerenciadordelivros.dominio.Livro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Alterar título da toolbar
        this.setTitle("Gerenciador de Livros");

        // Ao chamar o Objeto RecyclerView, o pacote poderá ser importado de forma automática
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        // Setup Recyclerview -> atribuir layout linear
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Criar uma lista de views
        List<Livro> listaLivros = new ArrayList<>();

        // Adicionar objetos Livros na ArrayList de Lista de Livros.
        // Informar todos os atributos declarados na classe Livro
        listaLivros.add(new Livro(1L,"Android para Leigos","Michael Burton","Alta books",false));
        listaLivros.add(new Livro(2L,"Android para Programadores","Paul J, Deitel","Bookman",true));
        listaLivros.add(new Livro(3L,"Desenvolvimento para Android","Griffiths, David","Alta books",false));
        listaLivros.add(new Livro(4L,"Android Base de Dados","Queirós, Ricardo","FCA Editora",true));
        listaLivros.add(new Livro(5L,"Android em Ação","King, Chris","Elsevier - Campus",false));
        listaLivros.add(new Livro(6L,"Jogos em Android","Queirós, Ricardo","FCA - Editora",true));
        listaLivros.add(new Livro(7L,"Android Essencial com Kotlin","Ricardo R.","NOVATEC",false));

        // Definir o layout de cada linha da RecyclerView e quais são seus respectivos dados
        // Para isso é necessário criar um classe com função adaptativa
         LivroAdapter livroAdapter = new LivroAdapter(listaLivros, this);

        recyclerView.setAdapter(livroAdapter);

    }
}