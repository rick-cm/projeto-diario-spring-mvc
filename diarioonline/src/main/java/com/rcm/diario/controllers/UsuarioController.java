package com.rcm.diario.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rcm.diario.models.Usuario;
import com.rcm.diario.repositories.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;
	
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ModelAndView listar(){
		ModelAndView mv = new ModelAndView("usuario/lista_usuarios");
		Iterable<Usuario> usuarios = ur.findAll();
		mv.addObject("usuarios", usuarios);
		return mv;
	}
	
	@RequestMapping(value = "/usuarios/cadastrar", method = RequestMethod.GET)
	public String paginaCadastrar() {
		return "usuario/cadastrar_usuarios";
	}
	
	@RequestMapping(value = "/usuarios/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@Valid Usuario usuario,  BindingResult result, RedirectAttributes attributes) {
		ArrayList<String> mensagens = new ArrayList<String>();
		if(result.hasErrors()){
			for (ObjectError m : result.getAllErrors()) {
	            mensagens.add(m.getDefaultMessage());
	        }
			attributes.addFlashAttribute("mensagens", mensagens);
			return "redirect:/usuarios/cadastrar";
		}else {
			mensagens.add("usuario cadastrado com sucesso!");
			usuario.setDataCriacao(new Date());
			ur.save(usuario);
			attributes.addFlashAttribute("mensagens", mensagens);
		}
		return "redirect:/usuarios";
	}
	
	@RequestMapping(value = "/deletarUsuario/{codigo}")
	public String delete(@PathVariable("codigo") long codigo, RedirectAttributes attributes) {
		ArrayList<String> mensagens = new ArrayList<String>();
		ur.deleteById(codigo);
		mensagens.add("Usuario deletado com sucesso!!!");
		attributes.addFlashAttribute("mensagens", mensagens);
		return "redirect:/usuarios";
	}
	
	@RequestMapping(value = "/deletarUsuario")
	public String delete(Long codigo, RedirectAttributes attributes) {
		ArrayList<String> mensagens = new ArrayList<String>();
		ur.deleteById(codigo);
		mensagens.add("Usuario deletado com sucesso!!!");
		attributes.addFlashAttribute("mensagens", mensagens);
		return "redirect:/usuarios";
	}
	
	@RequestMapping(value = "/editarUsuario/{codigo}", method = RequestMethod.GET)
	public ModelAndView paginaEditar(@PathVariable("codigo") long codigo) {
		ModelAndView mv = new ModelAndView("usuario/cadastrar_usuarios");
		Optional<Usuario> usuario = ur.findById(codigo);
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping(value = "/editarUsuario", method = RequestMethod.GET)
	public ModelAndView paginaEdita(Long codigo) {
		ModelAndView mv = new ModelAndView("usuario/detalhes_usuarios");
		Usuario usuario = ur.findByCodigo(codigo);
		mv.addObject("usuarios", usuario);
		return mv;
	}
}
