package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Scope("request")
@Controller
@RequestMapping("/carrinho")
public class CarrinhoComprasController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@Autowired
	private CarrinhoCompras carrinhoCompras;

	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco preco ) {
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
		CarrinhoItem carrinho = criaItem(produtoId,preco);
		carrinhoCompras.add(carrinho);
		return modelAndView;
	}
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco preco) {
		Produto produto = dao.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, preco );
		return carrinhoItem;
	}
}
