package com.hankey.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hankey.cursomc.domain.Pedido;
import com.hankey.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService servico;
	
	//ResponseEntity - Tipo especial do Spring que ja encapsula as inf de uma resposta http para rest
	// ? = qualquer tipo (pode encontrar ou nao)
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Pedido> listar(@PathVariable Integer id) {
		return ResponseEntity.ok(servico.buscar(id));
		
	}

}
