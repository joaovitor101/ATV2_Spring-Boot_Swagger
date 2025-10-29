package br.com.fatec.api_disciplinas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DisciplinasController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DisciplinasControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveListarTodasAsDisciplinas() throws Exception {
        mockMvc.perform(get("/api/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(6))
                .andExpect(jsonPath("$[0].Disciplina").value("Estatística Aplicada"));
    }

    @Test
    void deveBuscarDisciplinaPorIdExistente() throws Exception {
        mockMvc.perform(get("/api/disciplinas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.Disciplina").value("Estatística Aplicada"));
    }

    @Test
    void deveRetornarVazioParaIdInexistente() throws Exception {
        mockMvc.perform(get("/api/disciplinas/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deveCriarNovaDisciplinaComTituloEUrl() throws Exception {
        String novaDisciplina = """
            {
                "Disciplina": "Engenharia de Software"
            }
            """;

        mockMvc.perform(post("/api/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novaDisciplina))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("7"))
                .andExpect(jsonPath("$.Disciplina").value("Engenharia de Software"));

    }
}
