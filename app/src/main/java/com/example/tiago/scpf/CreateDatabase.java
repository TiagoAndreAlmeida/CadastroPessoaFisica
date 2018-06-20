package com.example.tiago.scpf;

//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Tiago André da Silva Almeida

//******************************************************

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//classe usada para a criação do banco de dados e atualização no caso de novas versões

public class CreateDatabase extends SQLiteOpenHelper {
    //dados necessários para criação do banco e tabela
    public static final String DATABASE_NAME = "base.db";
    public static final String ID = "_id";
    public static final String NAME = "nome";
    public static final String CPF = "cpf";
    public static final String PHONE = "telefone";
    public static final String EMAIL = "email";
    public static final String AGE = "idade";
    public static final String TABLE_NAME = "person";
    public static final int VERSION = 1;

    public CreateDatabase (Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //instrucao criacao da tabela
        String sql = "CREATE TABLE "+TABLE_NAME+ " ("+
                ID + " integer primary key autoincrement,"+
                NAME + " text, "+
                CPF + " text, "+
                PHONE + " text, "+
                EMAIL + " text, "+
                AGE + " text "+
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //atualização do banco
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
