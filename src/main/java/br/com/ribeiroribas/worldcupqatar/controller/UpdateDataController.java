package br.com.ribeiroribas.worldcupqatar.controller;

import br.com.ribeiroribas.worldcupqatar.controller.dto.UpdateDataDto;
import br.com.ribeiroribas.worldcupqatar.controller.dto.TeamsDto;
import br.com.ribeiroribas.worldcupqatar.service.UpdateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateDataController {

    @Autowired
    private UpdateDataService updateDataService;

    @PostMapping
    private List<TeamsDto> updateData(@RequestBody @Valid UpdateDataDto dataDto) throws IOException {
        return updateDataService.update(dataDto);
    }

}
