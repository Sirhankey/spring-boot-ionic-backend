package com.hankey.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankey.cursomc.domain.Categoria;
import com.hankey.cursomc.repositories.CategoriaRepository;
import com.hankey.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	//Automaticamente instanciado @Autowired
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria inserir(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
