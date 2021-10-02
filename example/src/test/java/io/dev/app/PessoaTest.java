package io.dev.app;

import io.dev.model.Pessoa;

import java.util.Calendar;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PessoaTest {
    
    @Test
    public void verificaTamanhoDoNomeMaiorQueZero(){
        Pessoa pessoa = new Pessoa("James");
        assertEquals("James", pessoa.getNome());
    }

   @Test
    public void testaSeIdadeIgualADataAtualMenosDataDeNascimento(){

        Calendar dataDeNascimento = Calendar.getInstance();
        dataDeNascimento.set(1981, 1, 1);
        Pessoa pessoa = new Pessoa(dataDeNascimento.getTime());
        assertEquals(40, pessoa.getIdade());
    }
}