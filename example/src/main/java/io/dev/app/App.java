package io.dev.app;

import io.dev.model.Pessoa;

public class App 
{
    public static void main( String[] args )
    {   
        Pessoa pessoa = new Pessoa();
        System.out.printf("[PRINT] Nome: %s, Idade: %d anos.\n",
            pessoa.getNome(), pessoa.getIdade());
    }
}
