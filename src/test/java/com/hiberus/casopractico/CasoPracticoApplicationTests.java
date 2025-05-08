package com.hiberus.casopractico;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.casopractico.infrastructure.adapters.AppConfiguration;
import com.hiberus.casopractico.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = {
                CasoPracticoApplication.class,
                AppConfiguration.class
        })
@AutoConfigureMockMvc
class CasoPracticoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void request() throws Exception {
        String expectedResult = """
                [
                    {"id":2,"name":"CONTRASTING FABRIC T-SHIRT","salesUnits":50,"stock":{"S":35,"L":9,"M":9}},
                    {"id":3,"name":"RAISED PRINT T-SHIRT","salesUnits":80,"stock":{"S":20,"L":20,"M":2}},
                    {"id":1,"name":"V-NECH BASIC SHIRT","salesUnits":100,"stock":{"S":4,"L":0,"M":9}}
                ]
                """;
        mockMvc.perform(
                        post("/products/sort")
                                .contentType("application/json")
                                .content("""
                                        [
                                        	{"criteriaName": "salesUnits", "weight": 1},
                                        	{"criteriaName": "stockRatio", "weight": 2}
                                        ]
                                        """))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
                .andExpect(content().json(expectedResult));
    }
}
