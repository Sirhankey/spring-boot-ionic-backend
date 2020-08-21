package com.hankey.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankey.cursomc.domain.Cliente;
import com.hankey.cursomc.repositories.ClienteRepository;
import com.hankey.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//Automaticamente instanciado @Autowired
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
