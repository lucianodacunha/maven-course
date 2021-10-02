package io.dev.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.javafaker.Faker;

public class Pessoa {

	private final String nome;
	private Log log = LogFactory.getLog(Pessoa.class);

	public Pessoa() {
		String nome = new Faker().name().fullName();
		log.info("Gerando um nome aleatório: " + nome);
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
