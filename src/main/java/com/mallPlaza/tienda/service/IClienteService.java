package com.mallPlaza.tienda.service;

import com.mallPlaza.tienda.model.dto.ClienteDto;
import com.mallPlaza.tienda.model.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();

    Cliente save(ClienteDto cliente);
    Cliente findById(Integer id);
    void delete(Cliente cliente);


    boolean isExistsById(Integer id);
}
