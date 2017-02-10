package com.djj.test.dao;

import com.djj.test.entity.BlobFile;

/**
 * Created by djj on 2017/2/10.
 */
public interface BlobFileDao {
    public BlobFile getBlobFile(int id);

    public void addBlobFile(BlobFile blobFile);
}
