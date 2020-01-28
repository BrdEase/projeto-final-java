package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedido;

@Controller
@RequestMapping("/pedidos")
public class PedidosServicoController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("pedidos/pedidos");
		
		
		Pedido[] pedidos = restTemplate.getForObject("https://book-payment.herokuapp.com/orders", Pedido[].class);
		
//		for (Pedido pedido : pedidos) {
//			System.out.println(pedido);
//		}
		
		modelAndView.addObject("pedidos", pedidos);
		
		return modelAndView;
	}

}
