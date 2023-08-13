package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM Credentials "
            + " WHERE userId = #{userId}")
    List<Credential> getAllByUserId(Integer userId);

    @Insert("INSERT INTO Credentials (url, username, key, password, userId) "
            + " VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Update("UPDATE Credentials SET url = #{url}, username = #{username}, key = #{key}, password = #{password} "
            + " WHERE userId = #{userId} AND credentialId = #{credentialId}")
    boolean update(Credential credential);

    @Delete("DELETE FROM Credentials "
            + " WHERE credentialId = #{credentialId}")
    boolean delete(Integer credentialId);

}
