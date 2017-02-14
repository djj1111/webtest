package com.djj.test.service;

import com.djj.test.entity.File;

import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */
public interface FileService {
    //public File getFileByID(int id);

    public List<File> getAllFiles();

    public void addFile(File file);

    //public void delFile(File file);

    //public void updateFile(File file);

    //public void updateFileById(int id);
}
