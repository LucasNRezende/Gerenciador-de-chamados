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

import com.gerenciadorchamados.GerenciadorChamados.DAO.ChamadoDAO;
import com.gerenciadorchamados.GerenciadorChamados.DAO.ClienteDAO;
import com.gerenciadorchamados.GerenciadorChamados.DAO.SoftwareDAO;
import com.gerenciadorchamados.GerenciadorChamados.DAO.UsuarioDAO;
import com.gerenciadorchamados.GerenciadorChamados.model.Chamado;

@Controller
@RequestMapping("/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoDAO chamadoDAO;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private SoftwareDAO softwareDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping(value = { "", "/" })
	public ModelAndView telaChamados() {
		return new ModelAndView("template-chamado");
	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastroChamado(@RequestParam(required = false) Integer id) {
		ModelAndView model = new ModelAndView("template-cadastro-chamado");
		if (id != null && id > 0) {
			model.addObject("chamado", chamadoDAO.findOne(id));
		} else {
			model.addObject("chamado", new Chamado());
		}
		model.addObject("clientes", clienteDAO.findAll());
		model.addObject("softwares", softwareDAO.findAll());
		model.addObject("usuarios", usuarioDAO.findAll());
		return model;
	}

	@RequestMapping(value = "/cadastro/salvar", method = RequestMethod.POST)
	public ModelAndView salvarCadastroChamado(@ModelAttribute(value = "chamado") Chamado chamado) {
		ModelAndView model = new ModelAndView("template-cadastro-chamado");
		try {
			Chamado cha = chamadoDAO.save(chamado);
			if (cha != null) {
				model.addObject("sucesso", "Chamado salvo com sucesso!");
			} else {
				model.addObject("erro", "Falha ao salvar!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao salvar!");
		}
		model.addObject("chamado", chamado);
		model.addObject("clientes", clienteDAO.findAll());
		model.addObject("softwares", softwareDAO.findAll());
		model.addObject("usuarios", usuarioDAO.findAll());
		return model;
	}

	@RequestMapping("/pesquisar")
	public ModelAndView pesquisarChamados(@RequestParam String statuschamado) {
		ModelAndView model = new ModelAndView("template-chamado");
		try {
			List<Chamado> chamados = new ArrayList<>();
			if (!statuschamado.isEmpty()) {
				chamados = chamadoDAO.findByStatuschamado(statuschamado);
			} else {
				chamados = chamadoDAO.findAll();
			}
			model.addObject("chamados", chamados);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return model;
	}

	@RequestMapping(value = "/remover")
	public ModelAndView removerChamado(@RequestParam Integer id) {
		ModelAndView model = new ModelAndView("template-chamdo");
		try {
			chamadoDAO.delete(id);
			model.addObject("sucesso", "Chamado removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao remover chamado!");
		}
		return model;
	}

}
