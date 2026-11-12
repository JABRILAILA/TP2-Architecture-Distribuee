package com.example.servicecours.controller;

import com.example.servicecours.dto.EtudiantDto;
import com.example.servicecours.model.Cours;
import com.example.servicecours.repository.CoursRepository;
import com.example.servicecours.service.EtudiantClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cours")
public class CoursController {

    private final CoursRepository repo;
    private final EtudiantClient etudiantClient;

    public CoursController(CoursRepository repo, EtudiantClient etudiantClient) {
        this.repo = repo;
        this.etudiantClient = etudiantClient;
    }

    @GetMapping
    public List<Cours> all() {
        return repo.findAll();
    }

    @PostMapping
    public Cours create(@RequestBody Cours c) {
        return repo.save(c);
    }

    // Structure de réponse pour le JSON de la vue enrichie
    public static class CoursEnrichi {
        private Integer id;
        private String titre;
        private String etudiantNom;

        public CoursEnrichi(Integer id, String titre, String etudiantNom) {
            this.id = id;
            this.titre = titre;
            this.etudiantNom = etudiantNom;
        }

        public Integer getId() { return id; }
        public String getTitre() { return titre; }
        public String getEtudiantNom() { return etudiantNom; }
    }

    @GetMapping("/enrichis")
    public List<CoursEnrichi> getCoursEnrichis() {
        List<Cours> cours = repo.findAll();
        List<CoursEnrichi> result = new ArrayList<>();

        for (Cours c : cours) {
            String nomEtudiant = "INCONNU";
            try {
                if (c.getEtudiantId() != null) {
                    EtudiantDto et = etudiantClient.getEtudiantById(c.getEtudiantId());
                    if (et != null && et.getNom() != null) {
                        nomEtudiant = et.getNom();
                    }
                }
            } catch (Exception ex) {
                nomEtudiant = "INCONNU";
            }
            result.add(new CoursEnrichi(c.getId(), c.getTitre(), nomEtudiant));
        }
        return result;
    }
}