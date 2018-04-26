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

import com.gerenciadorchamados.GerenciadorChamados.DAO.SoftwareDAO;
import com.gerenciadorchamados.GerenciadorChamados.model.Software;

@Controller
@RequestMapping("/softwares")
public class SoftwareController {

	@Autowired
	private SoftwareDAO softwareDAO;

	@RequestMapping(value = { "", "/" })
	public ModelAndView telaSoftwares() {
		return new ModelAndView("template-software");

	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastroSoftware(@RequestParam(required = false) Integer id) {
		ModelAndView model = new ModelAndView("template-cadastro-software");
		if (id != null && id > 0) {
			model.addObject("software", softwareDAO.findOne(id));
		} else {
			model.addObject("software", new Software());
		}
		return model;
	}

	@RequestMapping(value = "/cadastro/salvar", method = RequestMethod.POST)
	public ModelAndView salvarCadastroSoftware(@ModelAttribute(value = "software") Software software) {
		ModelAndView model = new ModelAndView("template-cadastro-software");
		try {
			Software sof = softwareDAO.save(software);
			if (sof != null) {
				model.addObject("sucesso", "Software salvo com sucesso!");
			} else {
				model.addObject("erro", "Falha ao salvar!");
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao salvar!");
		}
		model.addObject("software", software);
		return model;
	}

	@RequestMapping("/pesquisar")
	public ModelAndView pesquisarSoftwares(@RequestParam String nome) {
		ModelAndView model = new ModelAndView("template-software");
		try {
			List<Software> softwares = new ArrayList<>();
			if (!nome.isEmpty()) {
				softwares = softwareDAO.findByNome(nome);
			} else {
				softwares = softwareDAO.findAll();
			}
			model.addObject("softwares", softwares);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/remover")
	public ModelAndView removerCliente(@RequestParam Integer id) {
		ModelAndView model = new ModelAndView("template-software");
		try {
			softwareDAO.delete(id);
			model.addObject("sucesso", "Software removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao remover software!");
		}
		return model;
	}

}
