package com.mallPlaza.tienda.service.Impl;

import com.mallPlaza.tienda.model.dao.ClienteDao;
import com.mallPlaza.tienda.model.entity.Cliente;
import com.mallPlaza.tienda.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteImpl implements ICliente {
    @Autowired
    private ClienteDao clienteDao;
    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }
    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }
}
