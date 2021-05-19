package com.srezz.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindController {
    @GetMapping("/readAll")
    public void getAllGroup(){

    }
    @GetMapping("/getGroupBuName")
    public void getAllByNames(@RequestBody List<String> names){

    }
}
