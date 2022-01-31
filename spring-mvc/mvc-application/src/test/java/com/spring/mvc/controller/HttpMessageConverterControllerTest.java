package com.spring.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mvc.model.Cat;
import com.spring.mvc.model.Dog;
import com.spring.mvc.repository.CatRepository;
import com.spring.mvc.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HttpMessageConverterControllerTest {

    private MockMvc mockMvc;

    private Dog ddoick;

    private Cat navi;

    @Autowired
    WebApplicationContext context;

    @Autowired
    Marshaller marshaller;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    CatRepository catRepository;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(System.out::println)
                .build();
        this.ddoick = dogRepository.save(Dog.builder().name("또익").build());
        this.navi = catRepository.save(Cat.builder().name("나비").build());
    }

    @Test
    public void httpMessageTest() throws Exception {
        mockMvc.perform(get("/httpMessage")
                        .content(mapper.writeValueAsString(ddoick))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void jsonMessageTest() throws Exception {
        String json = mapper.writeValueAsString(ddoick);
        mockMvc.perform(get("/jsonMessage")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ddoick.getId()))
                .andExpect(jsonPath("$.name").value(ddoick.getName()));
    }

    @Test
    public void xmlMessageTest() throws Exception {
        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        marshaller.marshal(navi, result);
        String xmlString = stringWriter.toString();

        mockMvc.perform(get("/xmlMessage")
                        .content(xmlString)
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("cat/id").string(navi.getId().toString()))
                .andExpect(xpath("cat/name").string(navi.getName()));
    }
}