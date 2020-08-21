package com.hankey.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankey.cursomc.domain.Pedido;
import com.hankey.cursomc.repositories.PedidoRepository;
import com.hankey.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	//Automaticamente instanciado @Autowired
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
