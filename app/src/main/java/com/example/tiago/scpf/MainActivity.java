package com.example.tiago.scpf;

//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Tiago André da Silva Almeida

//******************************************************

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

//classe onde mostra os dados cadastrados no banco

public class MainActivity extends AppCompatActivity {
    private ListView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //configura a action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        //carregar dados do banco
        loadContents();

        //botão que chama a tela de inscrição
        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        //botão de informação sobre o apk
        ImageButton infor = (ImageButton) findViewById(R.id.imageButton);
        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfor();
            }
        });
    }
    //quando retornar da tela de inscrição recarrega os dados
    @Override
    protected void onResume() {
        super.onResume();
        loadContents();
    }

    protected void loadContents (){
        //lista para guarda todas as pessoas que existe no banco
        List<LegalPerson> personModel = new ArrayList<LegalPerson>();
        //carregando os dados do banco
        DatabaseController database = new DatabaseController(getBaseContext());
        Cursor cursor = database.loadData();
        //se existir dados monta a lista de pessoas
        //se não mostra menssagem
        if(cursor.getCount() != 0){
            do {
                //montando lista de pessoas
                LegalPerson newPerson = new LegalPerson(
                        cursor.getString(0).toString(),
                        cursor.getString(1).toString(),
                        cursor.getString(2).toString(),
                        cursor.getString(3).toString(),
                        cursor.getString(4).toString()
                );
                personModel.add(newPerson);
            }while (cursor.moveToNext());
            //setando o adapter a listview
            ArrayAdapter<LegalPerson> adapter = new ArrayAdapter<LegalPerson>(this,
                    android.R.layout.simple_list_item_1, personModel);
            view = findViewById(R.id.listView);
            view.setAdapter(adapter);
        }else {
            String mgs = "Não existe dados, adicione um novo";
            Toast.makeText(getApplicationContext(), mgs, Toast.LENGTH_LONG).show();
        }
    }

    //cria caixa de dialogo
    protected void showInfor (){
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Desenvolvido por: Tiago André.\nVersão: 1.0").setTitle("SCPF");
        alert = builder.create();
        alert.show();
    }
}
