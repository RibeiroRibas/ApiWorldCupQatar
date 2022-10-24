package br.com.ribeiroribas.worldcupqatar;

import br.com.ribeiroribas.worldcupqatar.controller.dto.TeamsDto;
import br.com.ribeiroribas.worldcupqatar.controller.dto.UpdateDataDto;
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

import java.util.Arrays;
import java.util.List;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.INVALID_ROUND_NUMBER;
import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.THE_UPDATE_FILE_IS_INVALID;
import static br.com.ribeiroribas.worldcupqatar.data.WorldCupMatchesToScrape.WORLD_CUP_MATCHES;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UpdateDataControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldUpdateCupResultsThenReturnStatusOkAndUpdatedData() throws Exception {
        UpdateDataDto updateDataDto = new UpdateDataDto();
        updateDataDto.setData(WORLD_CUP_MATCHES);
        updateDataDto.setRound("3");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/update")
                        .content(mapper.writeValueAsString(updateDataDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TeamsDto> teamDto = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), TeamsDto[].class));

        teamDto.forEach(teamsDto ->{
            Team team1 = teamsDto.getTeam1();
            System.out.println(team1);

            System.out.println();

            Team team2 = teamsDto.getTeam2();
            System.out.println(team2);
        });
    }

    @Test
    public void shouldReturnStatusNotFoundWhenUpdateDataIsInvalid() throws Exception{
        UpdateDataDto updateDataDto = new UpdateDataDto();
        updateDataDto.setData("Invalid Data");
        updateDataDto.setRound("3");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/update")
                        .content(mapper.writeValueAsString(updateDataDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(THE_UPDATE_FILE_IS_INVALID));
    }

    @Test
    public void shouldReturnStatusNotFoundWhenRoundNumberIsInvalid() throws Exception{
        UpdateDataDto updateDataDto = new UpdateDataDto();
        updateDataDto.setData(WORLD_CUP_MATCHES);
        updateDataDto.setRound("Invalid Round");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/update")
                        .content(mapper.writeValueAsString(updateDataDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(INVALID_ROUND_NUMBER));
    }


}
