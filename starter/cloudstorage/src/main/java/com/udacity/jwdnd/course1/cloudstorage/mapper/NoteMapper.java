package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM Notes "
            + " WHERE userId = #{userId}")
    List<Note> getAllByUserId(Integer userId);

    @Insert("INSERT INTO Notes (noteTitle, noteDescription,  userId) "
            + " VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Update("UPDATE Notes SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} "
            + " WHERE userId = #{userId} AND noteId = #{noteId}")
    boolean update(Note note);

    @Delete("DELETE FROM Notes "
            + " WHERE noteId = #{noteId}")
    boolean delete(Integer noteId);

}
