package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;

	  @RequestMapping("/form")
	    public ModelAndView form(){
		 ModelAndView modelAndview = new ModelAndView("/produtos/form");
		 modelAndview.addObject( "tipos",TipoPreco.values());
	        return modelAndview;
	    }


	@RequestMapping (method=RequestMethod.POST)
	public ModelAndView grava(Produto produto, RedirectAttributes attributes) {
		produtoDao.grava(produto);
		attributes.addFlashAttribute("sucesso", "Produto cadastado com sucesso!!");
		return new ModelAndView("redirect: produtos");
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView lista() {
		List<Produto> produtos= produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("/produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
}
