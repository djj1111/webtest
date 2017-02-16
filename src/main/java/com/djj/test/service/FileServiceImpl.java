package com.djj.test.service;

import com.djj.test.dao.FileDao;
import com.djj.test.entity.File;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */
//@Component("fileServiceBase")
@Component("fileService")
public class FileServiceImpl implements FileService {
    @Resource
    private FileDao fileDao;

    /*public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }*/

    @Override
    public File getFileByID(int id) {
        return fileDao.getByKey(id);
    }

    @Override
    public List<File> getAllFiles() {
        return fileDao.getAll();
    }

    @Override
    public int addFile(File file) {
        return (Integer) fileDao.save(file);
    }

    /*@Override
    public void delFile(File file) {
        fileDao.delFile(file);
    }
*/
   /* @Override
    public void updateFile(File file) {
        fileDao.updateFile(file);
    }

    @Override
    public void updateFileById(int id) {
        File f=fileDao.getFile(id);
        f.setMid(147);
    }*/
}
