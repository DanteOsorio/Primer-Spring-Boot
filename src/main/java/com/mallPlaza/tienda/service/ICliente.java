package com.mallPlaza.tienda.service;

import com.mallPlaza.tienda.model.entity.Cliente;

public interface ICliente {
    Cliente save(Cliente cliente);
    Cliente findById(Integer id);
    void delete(Cliente cliente);


}
