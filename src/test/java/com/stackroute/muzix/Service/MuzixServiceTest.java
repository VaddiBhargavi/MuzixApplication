package com.stackroute.muzix.Service;

import com.stackroute.Repository.MuzixRepository;
import com.stackroute.domain.Muzix;
import com.stackroute.service.MuzixService;
import com.stackroute.service.MuzixServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MuzixServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    MuzixRepository muzixRepository;

    Muzix muzix;

    @InjectMocks
    MuzixServiceImpl muzixService;
    List<Muzix> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        list.add(muzix);
        // muzix=new Muzix(4,"vaddi",1,"Awesome/Marvellous");
        muzix = new Muzix();
        muzix.setId(10);
        muzix.setName("John");
        muzix.setRating(3);
        muzix.setComments("v.good");
    }

    @Test
    public void saveUser() throws Exception{
        when(muzixRepository.save(muzix)).thenReturn(muzix);
        boolean savedUser = muzixService.saveMuzix(muzix);
        Assert.assertEquals(true,savedUser);
        verify(muzixRepository,times(1)).save(muzix);
    }

    @Test
    public void getAllUser(){
        muzixRepository.save(muzix);
        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> muzixlist = muzixService.getAllMuzixUsers();
        Assert.assertEquals(list,muzixlist);
    }

    @Test
    public void deleteUser() throws Exception {
        doThrow(EmptyStackException.class).when(muzixRepository).delete(muzix);
        boolean deletemuzix = muzixService.deleteMuzix(10);
        verify(muzixRepository, times(1)).deleteById(anyInt());
        //mockMvc= MockMvcBuilders.standaloneSetup(muzixService).build();
        //Assert.assertEquals(true,deletemuzix);

    }
    }

