package br.com.ribeiroribas.worldcupqatar.controller;

import br.com.ribeiroribas.worldcupqatar.controller.dto.UpdateDataDto;
import br.com.ribeiroribas.worldcupqatar.service.UpdateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/update")
public class UpdateDataController {

    @Autowired
    private UpdateDataService updateDataService;

    @PostMapping
    private ResponseEntity<?> updateData(@RequestBody @Valid UpdateDataDto dataDto) throws IOException {
        updateDataService.update(dataDto);
        return ResponseEntity.ok().build();
    }

}
