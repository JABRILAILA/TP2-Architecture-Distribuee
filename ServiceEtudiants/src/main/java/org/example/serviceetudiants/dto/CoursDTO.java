package org.example.serviceetudiants.dto;

public class CoursDTO {
    private Integer id;
    private String intitule;
    private String enseignant;

    public CoursDTO() {}

    public CoursDTO(Integer id, String intitule, String enseignant) {
        this.id = id;
        this.intitule = intitule;
        this.enseignant = enseignant;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }

    public String getEnseignant() { return enseignant; }
    public void setEnseignant(String enseignant) { this.enseignant = enseignant; }
}