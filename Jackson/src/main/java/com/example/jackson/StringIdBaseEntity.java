package com.example.jackson;

import java.io.Serializable;

/**
 * Created by lzp on 2017/5/15.
 */

public abstract class StringIdBaseEntity implements
        Serializable {

    /**
     * 主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
