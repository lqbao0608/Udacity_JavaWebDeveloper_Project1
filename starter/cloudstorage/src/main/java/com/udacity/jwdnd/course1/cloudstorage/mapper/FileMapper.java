package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM Files "
            + " WHERE userId = #{userId}")
    List<File> getAllByUserId(Integer userId);

    @Select("SELECT * FROM Files "
            + " WHERE fileId = #{fileId}")
    File get(Integer fileId);

    @Insert("INSERT INTO Files (fileName, contentType, fileSize, userId, fileData) "
            + " VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM Files "
            + " WHERE fileId = #{fileId}")
    boolean delete(int fileId);

    @Select("SELECT * FROM Files "
            + " WHERE fileName = #{fileName}")
    File exist(String fileName);

}
