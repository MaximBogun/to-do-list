package com.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.controller.DealController;
import com.todolist.model.Deal;
import com.todolist.repository.DealRepository;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DealControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    private DealController dealController;
    @Mock
    private DealRepository dealRepository;

    @PostConstruct
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(dealController)
                .addFilters(new CorsFilter())
                .build();
    }

    @Test
    public void save_collection_deals_then_return_collections_deal() throws Exception {
        mockMvc.perform(post("/deal/save")
                .content(asJsonString(Arrays.asList(new Deal("content", true)))).
                        contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void clear_all_deals() throws Exception {
        mockMvc.perform(delete("/deal/clear-all-completed"))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
