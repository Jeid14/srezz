package com.srezz.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srezz.exception.advicecontroller.AdviceController;
import com.srezz.modelDto.GroupDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.services.IMusicGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.srezz.utils.ExceptionsMessage.EMPTY_PARAMETER;
import static com.srezz.utils.Mappings.READ_BY_NAME;
import static com.srezz.utils.Mappings.UPDATE_GROUP;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FindControllerTest {
    private final IMusicGroupService iMusicGroupService = Mockito.mock(IMusicGroupService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    private static HashSet<String> hashSet = new HashSet<>();
    FindController cut = new FindController(iMusicGroupService);

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(cut)
                .setControllerAdvice(new AdviceController())
                .build();
        hashSet = new HashSet<>();

    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }


    @Test
    void getAllByNamesTest() throws Exception {
        hashSet.add("jeid");
        Set<GroupDto> groupDtoSet = new HashSet<>();
        Mockito.when(iMusicGroupService.getMusicGroups(hashSet)).thenReturn(groupDtoSet);
        mockMvc.perform(get(READ_BY_NAME + "?" + "name=" + hashSet.stream().findAny())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapToJson(groupDtoSet)));
    }
    @Test
    void getAllByNamesExceptionTest() throws Exception {
        hashSet = new HashSet<>();
        hashSet.add("");
        mockMvc.perform(get(READ_BY_NAME+"?" + "name=")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(EMPTY_PARAMETER));
    }
}