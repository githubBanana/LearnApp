package com.xs.retrofitlib.model;

import java.util.List;

/**
 * Created by CY on 2016/3/30.
 *
 */
public class BaseModel<T> {

    public String ErrNum;
    public String ErrMsg;
    public List<T> data;
    public int TotalCount;

    public String getErrNum() {
        return ErrNum;
    }

    public void setErrNum(String errNum) {
        ErrNum = errNum;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public boolean isSuccessed(){
        return "0".equals(ErrNum);
    }
//    {"ErrNum":"0","ErrMsg":"","data":[{"Code":"287586"}]}


}
