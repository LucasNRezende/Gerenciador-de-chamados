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

import com.gerenciadorchamados.GerenciadorChamados.DAO.AtendimentoDAO;
import com.gerenciadorchamados.GerenciadorChamados.DAO.ClienteDAO;
import com.gerenciadorchamados.GerenciadorChamados.DAO.SoftwareDAO;
import com.gerenciadorchamados.GerenciadorChamados.DAO.UsuarioDAO;
import com.gerenciadorchamados.GerenciadorChamados.model.Atendimento;




@Controller
@RequestMapping ("/atendimentos")

public class AtendimentoController {
	

	@Autowired
	private AtendimentoDAO atendimentoDAO;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private SoftwareDAO softwareDAO;
    @Autowired
	private UsuarioDAO usuarioDAO;
	
	@RequestMapping (value = { "","/" })
	public ModelAndView telaatendimentos (){
		return new ModelAndView("template-atendimento");
	}
	
	@RequestMapping ("/cadastro")
	public ModelAndView cadastroAtendimento (@RequestParam (required = false)Integer id ){
		ModelAndView model = new ModelAndView("template-cadastro-atendimento");
		if (id != null && id > 0) {
			model.addObject("atendimento", atendimentoDAO.findOne(id));
		} else {
			model.addObject("atendimento", new Atendimento());
		}
		model.addObject("clientes", clienteDAO.findAll());
		model.addObject("softwares", softwareDAO.findAll());
		model.addObject("usuarios", usuarioDAO.findAll());
		return model;
	}
	
	@RequestMapping(value = "/cadastro/salvar", method = RequestMethod.POST)
	public ModelAndView salvarCadastroAtendimento(@ModelAttribute(value = "atendimento") Atendimento atendimento) {
		ModelAndView model = new ModelAndView("template-cadastro-atendimento");
		try {
			Atendimento ate = atendimentoDAO.save(atendimento);
			if (ate != null) {
				model.addObject("sucesso", "Atendimento salvo com sucesso!");
			} else {
				model.addObject("erro", "Falha ao salvar!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao salvar!");
		}
		model.addObject("atendimento", atendimento);
		model.addObject("clientes", clienteDAO.findAll());
		model.addObject("softwares", softwareDAO.findAll());
		return model;
	}
	
	@RequestMapping("/pesquisar")
	public ModelAndView pesquisarAtendimentos(@RequestParam String tipoatendimento) {
		ModelAndView model = new ModelAndView("template-atendimento");
		try {
			List<Atendimento> atendimentos = new ArrayList<>();
			if (!tipoatendimento.isEmpty()) {
				atendimentos = atendimentoDAO.findByTipoatendimento(tipoatendimento);
			} else {
				atendimentos = atendimentoDAO.findAll();
			}
			model.addObject("atendimentos", atendimentos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	

	@RequestMapping(value = "/remover")
	public ModelAndView removerAtendimento(@RequestParam Integer id) {
		ModelAndView model = new ModelAndView("template-atendimento");
		try {
			atendimentoDAO.delete(id);
			model.addObject("sucesso", "Atendimento removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao remover atendimento!");
		}
		return model;
	}


}
