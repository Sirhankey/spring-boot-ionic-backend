package com.hankey.cursomc;

import java.text.SimpleDateFormat;
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
import com.hankey.cursomc.domain.ItemPedido;
import com.hankey.cursomc.domain.Pagamento;
import com.hankey.cursomc.domain.PagamentoComBoleto;
import com.hankey.cursomc.domain.PagamentoComCartao;
import com.hankey.cursomc.domain.Pedido;
import com.hankey.cursomc.domain.Produto;
import com.hankey.cursomc.domain.enums.EstadoPagamento;
import com.hankey.cursomc.domain.enums.TipoCliente;
import com.hankey.cursomc.repositories.CategoriaRepository;
import com.hankey.cursomc.repositories.CidadeRepository;
import com.hankey.cursomc.repositories.ClienteRepository;
import com.hankey.cursomc.repositories.EnderecoRepository;
import com.hankey.cursomc.repositories.EstadoRepository;
import com.hankey.cursomc.repositories.ItemPedidoRepository;
import com.hankey.cursomc.repositories.PagamentoRepository;
import com.hankey.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat= new Categoria(null, "Escritório");
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2= new Categoria(null, "Quarto");
		Categoria cat3 = new Categoria(null, "Banheiro");
		Categoria cat4= new Categoria(null, "Cozinha");
		Categoria cat5 = new Categoria(null, "Portaria");
		Categoria cat6= new Categoria(null, "Lavanderia");
		Categoria cat7 = new Categoria(null, "Sala de Jogos");
		Categoria cat8= new Categoria(null, "Varanda");
		Categoria cat9 = new Categoria(null, "Quarto do bb");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat,cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2019 19:42"), cli1, e3);
		
		Pagamento pagm1 = new PagamentoComCartao(null,EstadoPagamento.PENDENTE,ped1,2);
		ped1.setPagamento(pagm1);
		
		Pagamento pagm2 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped2, sdf.parse("21/08/2020 00:00"), null);
		ped2.setPagamento(pagm2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagm1,pagm2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2.000);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
