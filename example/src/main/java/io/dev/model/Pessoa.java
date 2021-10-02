package io.dev.model;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.github.javafaker.Faker;

public class Pessoa {

    private final String nome; 
    private final Date dataDeNascimento;   

    public Pessoa() {
        this.nome = new Faker().name().fullName();
        this.dataDeNascimento = new Faker().date().birthday();
    }

    public Pessoa(String nome) {
        this.nome = nome;
        this.dataDeNascimento = new Faker().date().birthday();
    }    

    public Pessoa(Date dataDeNascimento) {
        this.nome = new Faker().name().fullName();
        this.dataDeNascimento = dataDeNascimento;
    }       

    public Pessoa(String nome, Date dataDeNascimento){
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome(){
        return this.nome;
    }

    public int getIdade(){
        Calendar now = Calendar.getInstance();
        DateTime start = new DateTime(this.dataDeNascimento.getTime());
        DateTime end = new DateTime(now.getTime());

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        Log log = LogFactory.getLog(Pessoa.class);
        log.info("Start: " + fmt.print(start) + " End: " + fmt.print(end));

        Years anos = Years.yearsBetween(start, end);

        return anos.getYears();
    }

    @Override
    public String toString() {
        return this.getNome();
    }

}
