package com.hankey.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hankey.cursomc.domain.Categoria;
import com.hankey.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService servico;
	
	//ResponseEntity - Tipo especial do Spring que ja encapsula as inf de uma resposta http para rest
	// ? = qualquer tipo (pode encontrar ou nao)
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		return ResponseEntity.ok(servico.buscar(id));
		
	}
	
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1,"Informática");
		Categoria cat2 = new Categoria(2,"Escritório");
		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
	}

}
