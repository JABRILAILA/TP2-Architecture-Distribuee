package org.example.serviceetudiants.controller;

import org.example.serviceetudiants.model.Etudiant;
import org.example.serviceetudiants.repository.EtudiantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantRepository repo;

    public EtudiantController(EtudiantRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Etudiant> all() {
        return repo.findAll();
    }

    @PostMapping
    public Etudiant create(@RequestBody Etudiant e) {
        return repo.save(e);
    }

    @GetMapping("/{id}")
    public Etudiant getById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.delete(id); // Appel de la méthode delete(int) de votre repository
    }
}