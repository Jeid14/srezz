package com.srezz.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srezz.exception.advicecontroller.AdviceController;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.services.IMusicGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import java.util.stream.Stream;
import static com.srezz.utils.Mappings.SAVE_GROUP;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SaveControllerTest {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";
    private final IMusicGroupService iMusicGroupService = Mockito.mock(IMusicGroupService.class);
    private final BindingResult bindingResult = Mockito.mock(BindingResult.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        SaveController cut = new SaveController(iMusicGroupService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cut)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static Stream<Arguments> saveMusicGroupTestNominal() {
        return Stream.of(
                Arguments.arguments(new MusicGroupSaveDto("oldName", (short) 2010, (short) 2020)),
                Arguments.arguments(new MusicGroupSaveDto("jeid", (short) 2012, null)),
                Arguments.arguments(new MusicGroupSaveDto("vurado", (short) 2012, (short) 2020)),
                Arguments.arguments(new MusicGroupSaveDto("realtime", (short) 2012, null)
                ));
    }


    @ParameterizedTest
    @MethodSource("saveMusicGroupTestNominal")
    void saveMusicGroupTest(MusicGroupSaveDto musicGroupSaveDto) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doNothing().when(iMusicGroupService).addMusicGroup(musicGroupSaveDto);

        mockMvc.perform(post(SAVE_GROUP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(musicGroupSaveDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    public static Stream<Arguments> updateExceptionTestNominal() {
        return Stream.of(
                Arguments.arguments(new MusicGroupSaveDto(null,  (short) 2020, (short) 2020), "Invalid name!"),
                Arguments.arguments(new MusicGroupSaveDto("jeid",  (short) 2030, null),
                        "Year creation must be less than 2022"),
                Arguments.arguments(new MusicGroupSaveDto( "trolan", (short) 2012, (short) 2040),
                        "Year decay must be less than 2022"),
                Arguments.arguments(new MusicGroupSaveDto("realtime", (short) 2050, null),
                        "Year creation must be less than 2022")
        );
    }

    @ParameterizedTest
    @MethodSource("updateExceptionTestNominal")
    void updateExceptionTest(MusicGroupSaveDto musicGroupSaveDto, String expected) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        mockMvc.perform(post(SAVE_GROUP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(musicGroupSaveDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expected))
                .andExpect(header().stringValues(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .andReturn();
    }
}