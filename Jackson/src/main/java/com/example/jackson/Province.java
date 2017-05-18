package com.example.jackson;

import java.io.Serializable;

/**
 * Created by lzp on 2017/5/15.
 */

public class Province extends StringIdBaseEntity  implements
        Serializable {
    private static final long serialVersionUID = 1L;

    private Country country;

    private String name;

    private String code;

    public Province() {
    }

    public Province(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
