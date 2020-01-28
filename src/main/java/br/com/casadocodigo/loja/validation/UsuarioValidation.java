package br.com.casadocodigo.loja.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator{
	
	private UsuarioDAO usuarioDao;

	public UsuarioValidation(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senhaRepetida", "field.required");
		
		Usuario usuario = (Usuario) target;
		if (usuario.getSenha().length() < 5) {
			errors.rejectValue("senha", "field.size");
		}
		if (!usuario.getSenha().contentEquals(usuario.getSenhaRepetida())) {
			errors.rejectValue("senhaRepetida", "field.match");
		}
		List<Usuario> lista = usuarioDao.listar();
		for (Usuario user : lista) {
			if (user.getEmail().contentEquals(usuario.getEmail())) {
				errors.rejectValue("email", "field.match");
			}
		}

	}

}
