package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}

	  @RequestMapping("/form")
	    public ModelAndView form(Produto produto){
		 ModelAndView modelAndview = new ModelAndView("/produtos/form");
		 modelAndview.addObject( "tipos",TipoPreco.values());
	        return modelAndview;
	    }


	@RequestMapping (method=RequestMethod.POST)
	public ModelAndView salva(MultipartFile sumario , @Valid Produto produto,BindingResult result, RedirectAttributes attributes) {
	
		System.out.println(produto.toString());
		System.out.println(sumario.getOriginalFilename());
		if(result.hasErrors()) {
			return form(produto);
		}
		System.out.println("sem erros");
		
		String path = fileSaver.write("arquivo-sumario", sumario);
		produto.setSumarioPath(path);
		
		System.out.println(produto.toString() + "2");
		produtoDao.grava(produto);
		attributes.addFlashAttribute("sucesso", "Produto cadastado com sucesso!!");
		return new ModelAndView("redirect: /casadocodigo/produtos");
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView lista() {
		List<Produto> produtos= produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("/produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
}
