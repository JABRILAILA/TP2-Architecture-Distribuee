package com.example.servicecours.service;

import com.example.servicecours.dto.EtudiantDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EtudiantClient {
    private final RestTemplate rest = new RestTemplate();
    private final String baseUrl = "http://localhost:8081/etudiants";

    public EtudiantDto getEtudiantById(int id) {
        return rest.getForObject(baseUrl + "/" + id, EtudiantDto.class);
    }
}