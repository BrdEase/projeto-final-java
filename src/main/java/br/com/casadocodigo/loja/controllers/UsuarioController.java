package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.infra.GeraSenha;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	@RequestMapping( method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Usuario> usuarios = usuarioDao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/form");
		modelAndView.addObject("usuario",usuario);
		return modelAndView;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation(usuarioDao));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, 
				RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()) {
			return form(usuario);
		}
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setSenhaRepetida(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		usuarioDao.gravar(usuario);
		
		redirectAttributes.addFlashAttribute("sucesso", "Usuario cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping(value="/gravarRoles",method=RequestMethod.POST)
	public ModelAndView gravarRoles(Usuario usuario, String email,RedirectAttributes redirectAttributes){
		
		Usuario completo = usuarioDao.loadUserByUsername(email);
		
		completo.setRoles(usuario.getRoles());
		
		usuarioDao.gravar(completo);
		
		redirectAttributes.addFlashAttribute("sucesso", "Permissões editadas com sucesso!");
		
		return new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping(value="/editarRoles", method=RequestMethod.POST)
	public ModelAndView editarRoles(String email) {
		ModelAndView modelAndView = new ModelAndView("usuarios/formRoles");
		
		Usuario usuario = usuarioDao.loadUserByUsername(email);
		
		List<Role> listaRoles = roleDao.listar();
		
		modelAndView.addObject("usuario",usuario);
		modelAndView.addObject("roles",listaRoles);
		
		return modelAndView;
	}

}
