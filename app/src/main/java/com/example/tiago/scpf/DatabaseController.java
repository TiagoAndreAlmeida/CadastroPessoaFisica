package com.example.tiago.scpf;

//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Tiago André da Silva Almeida

//******************************************************

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//classe usada para acessar e inserir dados no banco

public class DatabaseController {
    private SQLiteDatabase db;
    private CreateDatabase database;

    public DatabaseController(Context context){
        database = new CreateDatabase(context);
    }

    //método para inserir um novo cadastro no banco
    public String insertData(String name, String cpf, String email, String phone, String age){
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();

        values.put(database.NAME, name);
        values.put(database.CPF, cpf);
        values.put(database.EMAIL, email);
        values.put(database.PHONE, phone);
        values.put(database.AGE, age);

        result = db.insert(database.TABLE_NAME, null, values);
        db.close();

        if (result ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    //método para carregar dados do banco
    //um objeto do tipo Cursor é retornado para facilitar a leitura dos dados em outra classe
    public Cursor loadData (){
        Cursor cursor;
        //campos que devem ser selecionados ( select name, cpf, phone, email, age )
        String fields [] = {
                CreateDatabase.NAME,
                CreateDatabase.CPF,
                CreateDatabase.PHONE,
                CreateDatabase.EMAIL,
                CreateDatabase.AGE
        };
        //aqui pegamos acesso ao banco no modo de leitura e executamos o comando sql
        db = database.getReadableDatabase();
        cursor = db.query(CreateDatabase.TABLE_NAME, fields, null, null, null, null, null, null);

        //se retorno dados movemos o ponteiro para o primeiro dado
        if(cursor != null){
            cursor.moveToFirst();
        }
        //fechamos a conexão e retornamos o cursor
        db.close();
        return cursor;
    }
}
