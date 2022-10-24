package br.com.ribeiroribas.worldcupqatar;

import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.INVALID_ROUND_NUMBER;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorldCupMatchesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnWorldCupGamesByRound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/qatar/" + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<QatarCupMatch> cupMatches = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), QatarCupMatch[].class));

        cupMatches.forEach(System.out::println);

    }
    @Test
    public void shouldReturnStatusNotFoundWhenRoundNumberIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/qatar/" + "Invalid Round")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(INVALID_ROUND_NUMBER));
    }
}
