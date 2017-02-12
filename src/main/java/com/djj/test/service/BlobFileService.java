package com.djj.test.service;

import com.djj.test.entity.BlobFile;

/**
 * Created by djj on 2017/2/10.
 */
public interface BlobFileService {
    public BlobFile getBlobFile(int id);

    public String getFileName(int id);
    public void addBlobFile(BlobFile blobFile);
}
