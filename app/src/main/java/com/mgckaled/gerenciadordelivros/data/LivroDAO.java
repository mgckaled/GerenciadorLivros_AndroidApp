package com.mgckaled.gerenciadordelivros.data;

// Classe responsável por receber os dados colocà-los no BD, ou seja,
// vai manipular todos os dados
// -> DAO - Data Access Object

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mgckaled.gerenciadordelivros.dominio.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private SQLiteDatabase db;
    private static LivroDAO instance;

    private LivroDAO (Context context) {
        // getInstance() Método de reutilização da instância, após sua sua criação
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static LivroDAO getInstance(Context context) {
        if (instance == null) {
            instance = new LivroDAO(context.getApplicationContext());
        }
        return instance;
    }

    // Método: Retornar todos os dados -> Lista de Livors
    public List<Livro> list() {

        String[] columns = {
                LivroContract.Columns._ID,
                LivroContract.Columns.titulo,
                LivroContract.Columns.autor,
                LivroContract.Columns.editora,
                LivroContract.Columns.emprestado
        };

        List<Livro> livros = new ArrayList<>();

        try (
                // Cursor --> percorre cada linha do bd.
                Cursor c = db.query(LivroContract.TABLE_NAME,
                        columns,
                        null,
                        null,
                        null,
                        null,
                        LivroContract.Columns.titulo);  // Ordenar por título.
            ) {

            if (c.moveToFirst()) {
                do {
                    // Criar um objeto com cada dado que vem desse cursor
                    Livro l = LivroDAO.fromCursor(c);
                    livros.add(l);

                }while (c.moveToNext());
            }
        }
        return livros;
    }

    private static Livro fromCursor (Cursor c) {

        Long id = c.getLong(c.getColumnIndex(LivroContract.Columns._ID));
        String titulo = c.getString(c.getColumnIndex(LivroContract.Columns.titulo));
        String autor = c.getString(c.getColumnIndex(LivroContract.Columns.autor));
        String editora = c.getString(c.getColumnIndex(LivroContract.Columns.editora));
        int emprestado = c.getInt(c.getColumnIndex(LivroContract.Columns.emprestado));

        return new Livro(id, titulo, autor, editora, emprestado);
    }

    public void save(Livro livro) {

        ContentValues values = new ContentValues();

        values.put(LivroContract.Columns.titulo, livro.getTitulo());
        values.put(LivroContract.Columns.autor, livro.getAutor());
        values.put(LivroContract.Columns.editora, livro.getEditora());
        values.put(LivroContract.Columns.emprestado, livro.getEmprestado());

        Long id = db.insert(LivroContract.TABLE_NAME, null, values);
        livro.setId(id);
    }

    public void update (Livro livro) {

        ContentValues values = new ContentValues();

        values.put(LivroContract.Columns.titulo, livro.getTitulo());
        values.put(LivroContract.Columns.autor, livro.getAutor());
        values.put(LivroContract.Columns.editora, livro.getEditora());
        values.put(LivroContract.Columns.emprestado, livro.getEmprestado());

        db.update(LivroContract.TABLE_NAME,
                values,
                LivroContract.Columns._ID + "=?",
                new String[] {String.valueOf(livro.getId())}
            );
    }

    public void delete(Livro livro) {

        db.delete(LivroContract.TABLE_NAME,
                LivroContract.Columns._ID + "=?",
                new String[] {String.valueOf(livro.getId())}
        );
    }
}
