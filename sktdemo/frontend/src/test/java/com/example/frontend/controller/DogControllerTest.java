package com.example.frontend.controller;

import com.example.frontend.service.DogService;
import dto.DogDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class DogControllerTest {
    private MockMvc mockMvc;
    @Mock
    DogService dogService;
    @Mock
    Errors fakeError;
    @Mock
    Model model;

    private DogDto dog;
    private List<DogDto> dogs = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dog = DogDto.builder().name("Terry").race("Doverman").age((byte) 2).build();
        dogs.add(dog);
        when(dogService.getDogs()).thenReturn(dogs);
        doNothing().when(dogService).saveDog(any(DogDto.class));
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(new DogController(dogService))
                .setViewResolvers(viewResolver)
                .build();

    }

    @Test
    void show() throws Exception {
        MvcResult mockResult = mockMvc.perform(get("/show")).andReturn();
        String showViewName = mockResult.getModelAndView().getViewName();
        List<DogDto> dogsResult = (List<DogDto>) mockResult.getRequest().getAttribute("dogs");
        assertEquals("show",showViewName);
        assertEquals(dogs,dogsResult);
    }

    @Test
    void register() throws Exception {
        MvcResult mockResult = mockMvc.perform(get("/register")).andReturn();
        String registerViewName = mockResult.getModelAndView().getViewName();
        assertEquals("register",registerViewName);
    }

    @Test
    void newDogRegister() throws Exception {
        //Testing with a badDog
        when(fakeError.hasErrors()).thenReturn(true);
        DogDto dogError = DogDto.builder().name("Error Dog").build();
        DogController dogController = new DogController(dogService);
        String responseView = dogController.newDogRegister(model, dogError, fakeError);
        assertEquals("register",responseView);
        //Testing without errors
        MvcResult mockResult = mockMvc.perform(post("/register")).andReturn();
        String showViewName = mockResult.getModelAndView().getViewName();
        assertEquals("redirect:/show",showViewName);
    }
}