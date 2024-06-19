package com.example.gestaofuncionarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestaofuncionarios.model.Departamento;
import com.example.gestaofuncionarios.repository.DepartamentoRepository;

import java.util.*;

import javax.management.relation.RelationNotFoundException;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable Long id, @RequestBody Departamento departamentoDetails) throws RelationNotFoundException {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Departamento not found for this id :: " + id));
        
        departamento.setNome(departamentoDetails.getNome());
        departamento.setLocal(departamentoDetails.getLocal());
        final Departamento updatedDepartamento = departamentoRepository.save(departamento);
        return ResponseEntity.ok(updatedDepartamento);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteDepartamento(@PathVariable Long id) throws RelationNotFoundException {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Departamento not found for this id :: " + id));
        departamentoRepository.delete(departamento);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}