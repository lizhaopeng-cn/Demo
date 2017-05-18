package com.example.jackson;

import java.io.Serializable;

/**
 * Created by lzp on 2017/5/15.
 */

public class Country extends StringIdBaseEntity implements
        Serializable {

    private String name;
    private String code;
    private String alpha1;
    private String alpha2;
    private String enName;
    private String twName;
    private String hkName;
    private Integer sort;

    public Country() {
    }

    public Country(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getAlpha1() {
        return alpha1;
    }

    public void setAlpha1(String alpha1) {
        this.alpha1 = alpha1;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getTwName() {
        return twName;
    }

    public void setTwName(String twName) {
        this.twName = twName;
    }

    public String getHkName() {
        return hkName;
    }

    public void setHkName(String hkName) {
        this.hkName = hkName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}