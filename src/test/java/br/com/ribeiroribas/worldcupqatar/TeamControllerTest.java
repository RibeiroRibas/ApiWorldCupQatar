package br.com.ribeiroribas.worldcupqatar;

import br.com.ribeiroribas.worldcupqatar.controller.dto.TeamsDto;
import br.com.ribeiroribas.worldcupqatar.model.Team;
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

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.TEAM_NOT_FOUND;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnTwoTeamsWhenGetByRespectiveNames() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/team/" + "Brasil/"+"Argentina")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TeamsDto teamsDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), TeamsDto.class);

        Team team1 = teamsDto.getTeam1();
        System.out.println(team1);

        System.out.println();

        Team team2 = teamsDto.getTeam2();
        System.out.println(team2);

    }

    @Test
    public void shouldReturnStatusNotFoundIfTeamNameIsInvalid() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders
                        .get("/team/" + "Brasil/" + "invalidTeam")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(TEAM_NOT_FOUND));
    }

}
