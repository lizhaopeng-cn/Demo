package com.example.jackson;

import java.io.Serializable;

/**
 * Created by lzp on 2017/5/15.
 */

    public class City extends StringIdBaseEntity implements
            Serializable {

        private String name;

        private String code;

        private Province province;

        private String bdCode;

        private String bdName;

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

        public Province getProvince() {
            return province;
        }

        public void setProvince(Province province) {
            this.province = province;
        }

        public String getBdCode() {
            return bdCode;
        }

        public void setBdCode(String bdCode) {
            this.bdCode = bdCode;
        }

        public String getBdName() {
            return bdName;
        }

        public void setBdName(String bdName) {
            this.bdName = bdName;
        }

    }
