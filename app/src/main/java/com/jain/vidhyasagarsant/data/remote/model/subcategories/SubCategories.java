package com.jain.vidhyasagarsant.data.remote.model.subcategories;

/**
 * Created by @iamBedant on 01/06/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategories {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

}