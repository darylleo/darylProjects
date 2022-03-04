package com.daryl.config;

import com.daryl.utils.IdWorker;
import tk.mybatis.mapper.genid.GenId;

/**
 * @author wl
 * @create 2022-01-21
 */
public class MyGenId implements GenId<Long> {

    private final IdWorker idWorker = new IdWorker(0,0);

    @Override
    public Long genId(String s, String s1) {
        return idWorker.nextId();
    }
}
