package com.srezz.services.impl;

import com.srezz.modelDto.GroupDto;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.services.IMusicGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Set;

@Slf4j
@Service("jdbcGroupService")
@ConditionalOnProperty(name = "db.connector", havingValue = "jdbc")
public class MusicGroupServiceJdbc implements IMusicGroupService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }


    @Override
    public void editMusicGroup(MusicGroupUpdateDto musicGroupUpdateDto) {

    }

    @Override
    public void addMusicGroup(MusicGroupSaveDto musicGroupSaveDto) {

    }

    @Override
    public Set<GroupDto> getMusicGroups(Set<String> names) {
        return null;
    }
}
