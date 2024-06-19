package com.example.gestaofuncionarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestaofuncionarios.model.Funcionario;
import com.example.gestaofuncionarios.repository.FuncionarioRepository;

import java.util.*;

import javax.management.relation.RelationNotFoundException;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @PostMapping
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetails) throws RelationNotFoundException {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Funcionario not found for this id :: " + id));
        
        funcionario.setNome(funcionarioDetails.getNome());
        funcionario.setEndereco(funcionarioDetails.getEndereco());
        funcionario.setTelefone(funcionarioDetails.getTelefone());
        funcionario.setEmail(funcionarioDetails.getEmail());
        funcionario.setDataNascimento(funcionarioDetails.getDataNascimento());
        final Funcionario updatedFuncionario = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok(updatedFuncionario);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteFuncionario(@PathVariable Long id) throws RelationNotFoundException {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Funcionario not found for this id :: " + id));
        funcionarioRepository.delete(funcionario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
