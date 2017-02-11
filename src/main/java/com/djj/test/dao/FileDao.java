package com.djj.test.dao;

import com.djj.test.entity.File;

import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */
public interface FileDao {
    public File getFile(int id);

    public List<File> getAllFile();

    public void addFile(File file);

    public void delFile(File file);

    public void updateFile(File file);
}
