package com.gerenciadorchamados.GerenciadorChamados.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gerenciadorchamados.GerenciadorChamados.DAO.UsuarioDAO;
import com.gerenciadorchamados.GerenciadorChamados.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping(value = { "", "/" })
	public ModelAndView telaUsuarios() {
		return new ModelAndView("template-usuario");

	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastroUsuario(@RequestParam(required = false) Integer id) {
		ModelAndView model = new ModelAndView("template-cadastro-usuario");
		if (id != null && id > 0) {
			model.addObject("usuario", usuarioDAO.findOne(id));
		} else {
			model.addObject("usuario", new Usuario());
		}
		return model;
	}

	@RequestMapping(value = "/cadastro/salvar", method = RequestMethod.POST)
	public ModelAndView salvarCadastroUsuario(@ModelAttribute(value = "usuario") Usuario usuario) {
		ModelAndView model = new ModelAndView("template-cadastro-usuario");
		try {
			Usuario usu = usuarioDAO.save(usuario);
			if (usu != null) {
				model.addObject("sucesso", "Usuario salvo com sucesso!");
			} else {
				model.addObject("erro", "Falha ao salvar!");
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao salvar!");
		}
		model.addObject("usuario", usuario);
		return model;
	}

	@RequestMapping("/pesquisar")
	public ModelAndView pesquisarUsuarios(@RequestParam String nome) {
		ModelAndView model = new ModelAndView("template-usuario");
		try {
			List<Usuario> usuarios = new ArrayList<>();
			if (!nome.isEmpty()) {
				usuarios = usuarioDAO.findByNome(nome);
			} else {
				usuarios = usuarioDAO.findAll();
			}
			model.addObject("usuarios", usuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/remover")
	public ModelAndView removerUsuario(@RequestParam Integer id) {
		ModelAndView model = new ModelAndView("template-usuario");
		try {
			usuarioDAO.delete(id);
			model.addObject("sucesso", "Usuario removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao remover usuario!");
		}
		return model;
	}

}
