package br.com.ribeiroribas.worldcupqatar;

import br.com.ribeiroribas.worldcupqatar.controller.dto.UpdateDataDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
        updateDataDto.setFileName("groupStage.txt");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/update")
                        .content(mapper.writeValueAsString(updateDataDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnStatusNotFoundWhenUpdateDataIsInvalid() throws Exception{
        UpdateDataDto updateDataDto = new UpdateDataDto();
        updateDataDto.setData("Invalid Data");
        updateDataDto.setFileName("groupStage.txt");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/update")
                        .content(mapper.writeValueAsString(updateDataDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(THE_UPDATE_FILE_IS_INVALID));
    }

    public void shouldReturnStatusNotFoundWhenUpdateFileIsInvalid() throws Exception{
        UpdateDataDto updateDataDto = new UpdateDataDto();
        updateDataDto.setData(WORLD_CUP_MATCHES);
        updateDataDto.setFileName("3");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/update")
                        .content(mapper.writeValueAsString(updateDataDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(THE_UPDATE_FILE_IS_INVALID));
    }


}
