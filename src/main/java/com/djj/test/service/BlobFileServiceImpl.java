package com.djj.test.service;

import com.djj.test.dao.BlobFileDao;
import com.djj.test.entity.BlobFile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by djj on 2017/2/10.
 */

@Component("blobfileServiceBase")
public class BlobFileServiceImpl implements BlobFileService {
    @Resource
    private BlobFileDao blobFileDao;

    public void setBlobFileDao(BlobFileDao blobFileDao) {
        this.blobFileDao = blobFileDao;
    }

    @Override
    public BlobFile getBlobFile(int id) {
        return blobFileDao.getBlobFile(id);
    }

    @Override
    public String getFileName(int id) {
        return blobFileDao.getFileName(id);
    }

    @Override
    public void addBlobFile(BlobFile blobFile) {
        blobFileDao.addBlobFile(blobFile);
    }
}
