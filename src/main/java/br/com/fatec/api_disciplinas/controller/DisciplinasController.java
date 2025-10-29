package br.com.fatec.api_disciplinas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST para gerenciar disciplinas da FATEC.
 */
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinasController {

    private final Map<Integer, Map<String, String>> disciplinasDB = new HashMap<>();
    private int nextId = 7;

    public DisciplinasController() {
        disciplinasDB.put(1, Map.of("id", "1", "Disciplina", "Estatística Aplicada"));
        disciplinasDB.put(2, Map.of("id", "2", "Disciplina", "Internet Das Coisas e Aplicações"));
        disciplinasDB.put(3, Map.of("id", "3", "Disciplina", "Experiência do Usuário"));
        disciplinasDB.put(4, Map.of("id", "4", "Disciplina", "Programação de Dispositivos Móveis I"));
        disciplinasDB.put(5, Map.of("id", "5", "Disciplina", "Integração e Entrega Contínua"));
        disciplinasDB.put(6, Map.of("id", "6", "Disciplina", "Laboratório de Desenvolvimento Web"));
    }

    @GetMapping
    public List<Map<String, String>> getDisciplinas() {
        return new ArrayList<>(disciplinasDB.values());
    }

    @GetMapping("/{id}")
    public Map<String, String> getDisciplinaById(@PathVariable int id) {
        return disciplinasDB.get(id);
    }

    @PostMapping
    public Map<String, String> createDisciplina(@RequestBody Map<String, String> data) {
        Map<String, String> novaDisciplina = new HashMap<>();
        novaDisciplina.put("id", String.valueOf(nextId));
        novaDisciplina.put("Disciplina", data.get("Disciplina"));

        disciplinasDB.put(nextId, novaDisciplina);
        nextId++;

        return novaDisciplina;
    }
}
