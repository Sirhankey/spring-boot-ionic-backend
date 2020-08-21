package com.hankey.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hankey.cursomc.domain.Categoria;
import com.hankey.cursomc.domain.Cidade;
import com.hankey.cursomc.domain.Cliente;
import com.hankey.cursomc.domain.Endereco;
import com.hankey.cursomc.domain.Estado;
import com.hankey.cursomc.domain.Produto;
import com.hankey.cursomc.domain.enums.TipoCliente;
import com.hankey.cursomc.repositories.CategoriaRepository;
import com.hankey.cursomc.repositories.CidadeRepository;
import com.hankey.cursomc.repositories.ClienteRepository;
import com.hankey.cursomc.repositories.EnderecoRepository;
import com.hankey.cursomc.repositories.EstadoRepository;
import com.hankey.cursomc.repositories.ProdutoRepository;

//CommandLineRunner usado para instanciar o banco com dados no inicio da aplicacao
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2= new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Rio de Janeiro");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Rio de Janeiro", est3);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		est3.getCidades().addAll(Arrays.asList(c4));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2,est3));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Cliente cli1 = new Cliente(null,"Daniel","dvmguimaraes@gmail.com","13003889788",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("988759212","25480835"));
		Cliente cli2 = new Cliente(null,"Daniela","daniela@gmail.com","10558987400",TipoCliente.PESSOAJURIDICA);
		cli2.getTelefones().addAll(Arrays.asList("988652145","22569850"));
		
		Endereco e1 = new Endereco(null, "Pompeu Loureiro", "106", "Apto 302", "Copacabana", "22061000", cli1, c4);
		Endereco e2 = new Endereco(null, "Rua Flores", "300", "Apto 501", "Jardim", "38642555", cli2, c1);
		Endereco e3 = new Endereco(null, "Rua Terras", "301", "Apto 502", "Jardins", "38642000", cli1, c3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e3));
		cli2.getEnderecos().addAll(Arrays.asList(e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
	}

}
