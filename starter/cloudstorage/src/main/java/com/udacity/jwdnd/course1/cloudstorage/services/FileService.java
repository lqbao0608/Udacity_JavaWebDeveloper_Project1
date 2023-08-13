package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserService userService;

    public FileService(
            FileMapper fileMapper,
            UserService userService
    ) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public List<File> getFileListByUserId() {
        return fileMapper.getAllByUserId(userService.getUserId());
    }

    public File getFileByFileId(Integer fileId) {
        return fileMapper.get(fileId);
    }

    public boolean isExistFileName(String fileName) {
        return fileMapper.exist(fileName) != null;
    }

    public void createFile(File file) {
        fileMapper.insert(file);
    }

    public boolean deleteFile(Integer fileId) {
        return fileMapper.delete(fileId);
    }

}
