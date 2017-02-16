package com.djj.test.service;

import com.djj.test.dao.BlobFileDao;
import com.djj.test.entity.BlobFile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by djj on 2017/2/10.
 */

//@Component("blobfileServiceBase")
@Component("blobFileService")
public class BlobFileServiceImpl implements BlobFileService {
    @Resource
    private BlobFileDao blobFileDao;

    @Override
    public BlobFile getBlobFile(int id) {
        return blobFileDao.getByKey(id);
    }

    /*@Override
    public String getFileName(int id) {
        return blobFileDao.getFileName(id);
    }*/

    @Override
    public int addBlobFile(BlobFile blobFile) {
        return (Integer) blobFileDao.save(blobFile);
    }
}
