package com.example.tiago.scpf;

//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Tiago André da Silva Almeida

//******************************************************

// classe modelo para visualização das pessoas cadastradas

public class LegalPerson {

    private String name, cpf, phone, email, age;

    public LegalPerson(String name, String cpf, String phone, String email, String age) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "NOME: "+this.name+
                " CPF: "+this.cpf+
                " TELEFONE: "+this.phone+
                " EMAIL: "+this.email+
                " IDADE: "+this.age;
    }
}
