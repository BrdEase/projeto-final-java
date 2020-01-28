package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.RelatorioProdutos;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	

	@RequestMapping(method=RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public RelatorioProdutos index(@RequestParam(value="data") Optional<String> data) throws ParseException {
		if (data.isPresent()) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataParseada = Calendar.getInstance();
			dataParseada.setTime(sdf.parse(data.get()));
			
			RelatorioProdutos relatorio = RelatorioProdutos.gerar(produtoDao.listar(), dataParseada);
			
			return relatorio;
			
		} else {
			return RelatorioProdutos.gerar(produtoDao.listar());
		}
	}

}
