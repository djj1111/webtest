package com.djj.test.service;

import com.djj.test.dao.BlobFileDao;
import com.djj.test.entity.BlobFile;

/**
 * Created by djj on 2017/2/10.
 */
public class BlobFileServiceImpl implements BlobFileService {
    private BlobFileDao blobFileDao;

    public void setBlobFileDao(BlobFileDao blobFileDao) {
        this.blobFileDao = blobFileDao;
    }

    @Override
    public BlobFile getBlobFile(int id) {
        return blobFileDao.getBlobFile(id);
    }

    @Override
    public void addBlobFile(BlobFile blobFile) {
        blobFileDao.addBlobFile(blobFile);
    }
}
