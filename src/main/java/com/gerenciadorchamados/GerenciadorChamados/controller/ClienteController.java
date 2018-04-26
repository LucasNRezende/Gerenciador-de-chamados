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

import com.gerenciadorchamados.GerenciadorChamados.DAO.ClienteDAO;
import com.gerenciadorchamados.GerenciadorChamados.model.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteDAO clienteDAO;

	@RequestMapping(value = { "", "/" })
	public ModelAndView telaClientes() {
		return new ModelAndView("template-cliente");

	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastroCliente(@RequestParam(required = false) Integer id) {
		ModelAndView model = new ModelAndView("template-cadastro-cliente");
		if (id != null && id > 0) {
			model.addObject("cliente", clienteDAO.findOne(id));
		} else {
			model.addObject("cliente", new Cliente());
		}
		return model;
	}

	@RequestMapping(value = "/cadastro/salvar", method = RequestMethod.POST)
	public ModelAndView salvarCadastroCliente(@ModelAttribute(value = "cliente") Cliente cliente) {
		ModelAndView model = new ModelAndView("template-cadastro-cliente");
		try {
			Cliente cli = clienteDAO.save(cliente);
			if (cli != null) {
				model.addObject("sucesso", "Cliente salvo com sucesso!");
			} else {
				model.addObject("erro", "Falha ao salvar!");
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao salvar!");
		}
		model.addObject("cliente", cliente);
		return model;
	}

	@RequestMapping("/pesquisar")
	public ModelAndView pesquisarClientes(@RequestParam String razaosocial) {
		ModelAndView model = new ModelAndView("template-cliente");
		try {
			List<Cliente> clientes = new ArrayList<>();
			if (!razaosocial.isEmpty()) {
				clientes = clienteDAO.findByRazaosocial(razaosocial);
			} else {
				clientes = clienteDAO.findAll();
			}
			model.addObject("clientes", clientes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/remover")
	public ModelAndView removerCliente(@RequestParam Integer id) {
		ModelAndView model = new ModelAndView("template-cliente");
		try {
			clienteDAO.delete(id);
			model.addObject("sucesso", "Cliente removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("erro", "Falha ao remover cliente!");
		}
		return model;
	}

}
