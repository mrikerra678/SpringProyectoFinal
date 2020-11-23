package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public List<Cliente> listAllCliente() {
        return clienteRepository.findAll();
    }

    public void saveCliente(Cliente cliente) {
    	clienteRepository.save(cliente);
    }

    public Cliente getCliente(Integer id) {
        return clienteRepository.findById(id).get();
    }

    public void deleteCliente(Integer id) {
    	clienteRepository.deleteById(id);
    }
}