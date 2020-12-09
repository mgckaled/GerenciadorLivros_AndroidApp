package com.mgckaled.gerenciadordelivros.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Classe que define o BD e conecta com o App
public class DBHelper extends SQLiteOpenHelper {

    // Constante -> nome do banco de dados
    public static final String BD_NAME = "livrosdb";
    // Constante -> Versão do banco de dados
    public static final int BD_VERSION = 1;
    // O Banco de Dados precisará acessar a mesma instância da classe.
    private static DBHelper instance;

    // String código SQL para execução de criação da tabela:
    private static String SQL_CREATE = String.format (
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL)",
            LivroContract.TABLE_NAME,
            LivroContract.Columns._ID,
            LivroContract.Columns.titulo,
            LivroContract.Columns.autor,
            LivroContract.Columns.editora,
            LivroContract.Columns.emprestado
    );

    // String código SQL para execução de remoção de tabela
    private static String SQL_DROP = "DROP TABLE IF EXISTS " + LivroContract.TABLE_NAME;



    // Constructor (privado, porque não poderá ser acessado fora da classe)
    private DBHelper(Context context){
        super(context,BD_NAME, null, BD_VERSION);
    }
    
    public static DBHelper getInstance(Context context) {
        // Se o objeto não existe, criar uma nova instância, passando o contexo
        if (instance == null) {
            instance = new DBHelper(context);
        }
        // Caso o objeto já exista, retornar o objeto previamente criado. (Reuso contínuo)
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Primeiro vai apagar o banco de dados
        db.execSQL(SQL_DROP);
        // Depois, executar a query de criação de tabela
        db.execSQL(SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db); // Update -> apagar bd e criar um novo -> método onCreate
    }
}
