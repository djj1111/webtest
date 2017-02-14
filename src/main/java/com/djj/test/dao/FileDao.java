package com.djj.test.dao;

import com.djj.test.entity.File;

import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */

public interface FileDao extends AbstractDao<Integer, File> {
    public List<File> getAll();
}
