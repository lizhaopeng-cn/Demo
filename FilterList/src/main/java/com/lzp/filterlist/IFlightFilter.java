package com.lzp.filterlist;

import java.util.List;

/**
 * Created by lzp48947 on 2017/11/2.
 */

public class IFlightFilter {
    public static final String SINGLE_SELECT = "single";
    public static final String MULTI_SELECT = "multi";

    private String selectType;
    private int leftId;
    private String leftName;
    private List<String> rights;

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public int getLeftId() {
        return leftId;
    }

    public void setLeftId(int leftId) {
        this.leftId = leftId;
    }

    public String getLeftName() {
        return leftName;
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }
}
