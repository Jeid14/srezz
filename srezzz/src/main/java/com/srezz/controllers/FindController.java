package com.srezz.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.srezz.utils.Mappings.*;

@RestController
public class FindController {
    @GetMapping(READ_ALL)
    public void getAllGroup(){

    }
    @GetMapping(READ_BY_NAME)
    public void getAllByNames(@RequestBody List<String> names){

    }
}
