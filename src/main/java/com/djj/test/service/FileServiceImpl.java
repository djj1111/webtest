package com.djj.test.service;

import com.djj.test.dao.FileDao;
import com.djj.test.entity.File;

import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */
public class FileServiceImpl implements FileService {
    private FileDao fileDao;

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Override
    public File getFile(int id) {
        return fileDao.getFile(id);
    }

    @Override
    public List<File> getAllFile() {
        return fileDao.getAllFile();
    }

    @Override
    public void addFile(File file) {
        fileDao.addFile(file);
    }

    @Override
    public boolean delFile(int id) {
        return fileDao.delFile(id);
    }

    @Override
    public boolean updateFile(File file) {
        return fileDao.updateFile(file);
    }
}
