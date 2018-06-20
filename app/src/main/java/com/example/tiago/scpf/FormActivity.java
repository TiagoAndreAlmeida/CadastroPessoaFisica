package com.example.tiago.scpf;

//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Tiago André da Silva Almeida

//******************************************************

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//classe que controla a activity formulario para inserção de novos dados no banco

public class FormActivity extends AppCompatActivity {

    private Button send_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        send_button = (Button) findViewById(R.id.sendButton);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pegamos a referência do banco para fazer inserção
                DatabaseController database = new DatabaseController(getBaseContext());

                EditText name = (EditText)findViewById(R.id.nameEdit);
                EditText cpf = (EditText)findViewById(R.id.cpfEdit);
                EditText email = (EditText)findViewById(R.id.emailEdit);
                EditText phone = (EditText)findViewById(R.id.phoneEdit);
                EditText age = (EditText)findViewById(R.id.ageEdit);
                //verefica se todos os campos foram preenchidos
                if(name.length()>0 && cpf.length()>0 && email.length()>0 && phone.length()>0 &&
                        age.length()>0) {
                    //insere dos dados no banco
                    String result = database.insertData(
                            name.getText().toString(),
                            cpf.getText().toString(),
                            email.getText().toString(),
                            phone.getText().toString(),
                            age.getText().toString()
                    );
                    //mostra a menssagem ao usuário e retorna para activity anterior
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    String mgs = "Todos os campos devem ser preenchidos";
                    Toast.makeText(getApplicationContext(), mgs, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
