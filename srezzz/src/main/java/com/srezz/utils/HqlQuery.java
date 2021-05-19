package com.srezz.utils;

public class HqlQuery {
    public static final String NAME_PARAMETER = "name";
    public static final String SELECT_MUSIC_GROUP_BY_NAMES = "FROM MusicGroup WHERE LOWER(name) IN (:name)";
    public static final String SELECT_MUSIC_GROUP_BY_NAME = "FROM MusicGroup WHERE LOWER(name) = LOWER(:name)";
}
