package com.example.diego.controller;


import com.example.diego.model.Subject;
import com.example.diego.repository.SubjectRepository;
import com.example.diego.service.SubjectService;

import com.example.diego.service.SubjectServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SubjectController.class, secure = false)
public class SubjectControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SubjectService subjectService;

    @InjectMocks
    SubjectServiceImpl subjectServiceT;

    @Mock
    SubjectRepository subjectRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }




    List<Subject> listSubjectMock = new ArrayList<>();


    @Test
    public void testGetAllSubjectTrue() throws Exception {
        Subject subjectMock = new Subject(1,"Subject1",1);
        listSubjectMock.add(subjectMock);
        Mockito.when(subjectService.getAll()).thenReturn(listSubjectMock);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/apirest/api/v1/subjects")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //log.info(result.getResponse().toString());
        String expected = "[{subjectId: 1,subjectName:Subject1,status:1}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void testGetOneSubjectTrue() throws Exception {
        Subject subjectMock = new Subject(1,"Subject1",1);
        Mockito.when(subjectService.findId(Mockito.anyInt())).thenReturn(subjectMock);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/apirest/api/v1/subjects/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        log.info(result.getResponse().toString());
        String expected = "{subjectId: 1,subjectName:Subject1,status:1}";
        System.out.println("--------------->"+result.getResponse().getContentAsString());
        JSONAssert. assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void testGetOneSubjectFalse() throws Exception {
        Subject subjectMock = new Subject(1,"Subject1",1);
        Mockito.when(subjectService.findId(Mockito.anyInt())).thenReturn(subjectMock);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/apirest/api/v1/subjects/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //log.info(result.getResponse().toString());
        String expected = "{subjectId:1,subjectName:CCNA,subjectStatus:1}";
        JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createSubject() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/apirest/api/v1/subjects")
                .content(asJsonString(new Subject(1,"Subject1",1)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectName").exists());

    }



    @Test
    public void createSubjectServiceTest()
    {
        Subject subjectMock = new Subject(1,"Subject1",1);

        subjectServiceT.save(subjectMock);

        Mockito.verify(subjectRepository, times(1)).save(subjectMock);
    }








}
