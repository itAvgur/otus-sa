package com.itavgur.otus.sa.lab1.web;

import com.itavgur.otus.sa.lab1.web.dto.StatusDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    @GetMapping("/health")
    public StatusDto getHealth() {
        return StatusDto.ok();
    }


}
