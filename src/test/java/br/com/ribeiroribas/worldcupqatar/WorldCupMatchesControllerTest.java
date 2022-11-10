package br.com.ribeiroribas.worldcupqatar;

import br.com.ribeiroribas.worldcupqatar.controller.dto.MatchesByDateDto;
import br.com.ribeiroribas.worldcupqatar.model.Match;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorldCupMatchesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnWorldCupMatchesByDate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/qatar/" + LocalDate.of(2022, 11, 22))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MatchesByDateDto cupMatches = mapper.readValue(mvcResult.getResponse().getContentAsString(), MatchesByDateDto.class);

        System.out.println(cupMatches);

    }

    @Test
    public void shouldReturnAllWorldCupMatches() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/qatar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<Match> cupMatches = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), Match[].class));

        System.out.println(cupMatches);

    }

}
