package com.djj.test.service;

import com.djj.test.dao.FileDao;
import com.djj.test.entity.File;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */
@Component("fileServiceBase")
public class FileServiceImpl implements FileService {
    @Resource
    private FileDao fileDao;

    /*public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }*/

   /* @Override
    public File getFile(int id) {
        return fileDao.getFile(id);
    }
*/

    @Override
    public List<File> getAllFiles() {
        return fileDao.getAll();
    }

    @Override
    public void addFile(File file) {
        fileDao.;
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
