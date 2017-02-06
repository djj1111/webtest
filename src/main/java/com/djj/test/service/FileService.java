package com.djj.test.service;

import com.djj.test.entity.File;

import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */
public interface FileService {
    public File getFile(int id);

    public List<File> getAllFile();

    public void addFile(File file);

    public boolean delFile(int id);

    public boolean updateFile(File file);
}
