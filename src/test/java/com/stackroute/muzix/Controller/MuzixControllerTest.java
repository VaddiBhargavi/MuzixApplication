package com.stackroute.muzix.Controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.stackroute.controller.MuzixController;
import com.stackroute.domain.Muzix;
import com.stackroute.service.MuzixService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MuzixControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MuzixService muzixService;

    Muzix muzix;

    @InjectMocks
    MuzixController muzixController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();
        muzix = new Muzix(1, "bhargavi", 5, "Good");
    }

    @Test
    public void testMuzixController() throws Exception {
        when(muzixService.saveMuzix(muzix)).thenReturn(true);
        mockMvc.perform(post("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(muzix)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(muzixService, times(1)).saveMuzix(Mockito.any(Muzix.class));
        verifyNoMoreInteractions(muzixService);
    }

    public String jsonToString(final Muzix muzix) {
        String string = "";
        ObjectMapper mapper= new ObjectMapper();
        try {
            string = mapper.writeValueAsString(muzix);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return string;
    }
}