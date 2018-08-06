package com.buenasideas.jpa.controllers;

import com.buenasideas.jpa.models.entity.Cliente;
import com.buenasideas.jpa.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String getCliente(Model model){
        model.addAttribute("titulo", "JPA-App");
        model.addAttribute("titulo_text", "Listado de Clientes");
        model.addAttribute("list_clientes", service.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String create(Map<String, Object> model){

        Cliente cliente = new Cliente();

        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");

        return "form";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash){

        Cliente cliente = null;

        if(id > 0){
            cliente = service.findOne(id);
            if(cliente == null){
                flash.addFlashAttribute("danger", "El cliente no se encuentra registrado");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("danger", "El id del cliente no puede ser 0");
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid Cliente cliente, BindingResult result, RedirectAttributes flash, SessionStatus status){

        if(result.hasErrors()){
            return "form";
        }
        service.save(cliente);
        status.setComplete();

        flash.addFlashAttribute("success", "Cliente creado con éxito");

        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if(id > 0) {
            service.delete(id);
            flash.addFlashAttribute("success", "Cliente creado con éxito");
        }
        return "redirect:/listar";
    }

}
