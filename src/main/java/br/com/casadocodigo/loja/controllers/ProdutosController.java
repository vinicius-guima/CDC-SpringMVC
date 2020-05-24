package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;

	  @RequestMapping("/produtos/form")
	    public ModelAndView form(){
		 ModelAndView modelAndview = new ModelAndView("/produtos/form");
		 modelAndview.addObject( "tipos",TipoPreco.values());
	        return modelAndview;
	    }


	@RequestMapping("/produtos")
	public String grava(Produto produto) {
		System.out.println(produto.toString());
		produtoDao.grava(produto);
		
		return "produtos/ok";
	}
}
