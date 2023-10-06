package com.mallPlaza.tienda.controller;

import com.mallPlaza.tienda.model.dto.ClienteDto;
import com.mallPlaza.tienda.model.entity.Cliente;
import com.mallPlaza.tienda.model.payload.MensajeResponse;
import com.mallPlaza.tienda.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<?> showAll() {
        List<Cliente> getList = clienteService.findAll();

        if (getList== null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No hay registros !!")
                    .objeto(null)
                    .build(), HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                .mensaje("")
                .objeto(getList)
                .build(), HttpStatus.OK);

    }


    @PostMapping("/cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDto);
            clienteDto = ClienteDto.builder()
                    .idCliente(clienteSave.getIdCliente())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .correo(clienteSave.getCorreo())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Cliente creado exitosamente")
                    .objeto(clienteDto)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException exDta) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDta.getMessage())
                    .objeto(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto,@PathVariable Integer id) {
        Cliente clienteUpdate = null;

        try {
            if (clienteService.isExistsById(id)) {
                clienteDto.setIdCliente(id);
                clienteUpdate = clienteService.save(clienteDto);
                clienteDto = ClienteDto.builder()
                        .idCliente(clienteUpdate.getIdCliente())
                        .nombre(clienteUpdate.getNombre())
                        .apellido(clienteUpdate.getApellido())
                        .correo(clienteUpdate.getCorreo())
                        .fechaRegistro(clienteUpdate.getFechaRegistro())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Cliente guardado exitosamente")
                        .objeto(clienteDto)
                        .build(), HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Registro no existente")
                        .objeto(null)
                        .build(), HttpStatus.NOT_FOUND);

            }

        } catch (DataAccessException exDta) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDta.getMessage())
                    .objeto(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDta) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDta.getMessage())
                    .objeto(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Cliente clienteShow = clienteService.findById(id);

        if (clienteShow == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro no existe !!")
                    .objeto(null)
                    .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(ClienteDto.builder()
                        .idCliente(clienteShow.getIdCliente())
                        .nombre(clienteShow.getNombre())
                        .apellido(clienteShow.getApellido())
                        .correo(clienteShow.getCorreo())
                        .fechaRegistro(clienteShow.getFechaRegistro())
                        .build())
                .build(), HttpStatus.OK);

    }
}
