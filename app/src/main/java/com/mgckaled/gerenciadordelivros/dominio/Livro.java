package com.mgckaled.gerenciadordelivros.dominio;

import java.io.Serializable;

/* Modelo de Objeto Livro. Essa classem separado tem o objetivo organizar o código,
* proteger suas informações e auxiliar na criação e acesso de Objetos Livro, */

// Deve implementar a Classe Serializable. Como os dados dos banco de dados serão armazenados
// no celular, os dados precisam ser serializados e persistidos
public class Livro implements Serializable {

    // Atributos da classe -> acesso privado (encapsulamento)
    private long id;
    private String titulo;
    private String autor;
    private String editora;
    private int emprestado;  // 0 ou 1, pois o SQLite não suporta dados booleanos

    // Constructor com id
    public Livro(long id, String titulo, String autor, String editora, int emprestado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.emprestado = emprestado;
    }

    // Constructor sem id
    public Livro( String titulo, String autor, String editora, int emprestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.emprestado = emprestado;
    }

    //Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getEmprestado() {
        return emprestado;
    }

    public void setEmprestado(int emprestado) {
        this.emprestado = emprestado;
    }
}
