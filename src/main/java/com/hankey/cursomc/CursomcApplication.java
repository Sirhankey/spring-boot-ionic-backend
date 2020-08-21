package com.hankey.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hankey.cursomc.domain.Categoria;
import com.hankey.cursomc.repositories.CategoriaRepository;

//CommandLineRunner usado para instanciar o banco com dados no inicio da aplicacao
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2= new Categoria(null, "Escritório");
		
		repo.saveAll(Arrays.asList(cat1,cat2));
	}

}
