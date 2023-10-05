package com.mallPlaza.tienda.controller;

import com.mallPlaza.tienda.model.entity.Cliente;
import com.mallPlaza.tienda.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ICliente clienteService;
    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {
       return clienteService.save(cliente);
    }
    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }
    @DeleteMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Cliente clienteDelete = clienteService.findById(id);
         clienteService.delete(clienteDelete);
    }
    @GetMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente showById(@PathVariable Integer id) {
        return clienteService.findById(id);
    }
}
