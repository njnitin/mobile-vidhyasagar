package com.jain.vidhyasagarsant.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Winnerkm on 01-06-2017.
 */


public class Category {
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


    public class Response {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("logo")
        @Expose
        private String logo;
        @SerializedName("is_dashboard")
        @Expose
        private Boolean isDashboard;
        @SerializedName("is_side_menu")
        @Expose
        private Boolean isSideMenu;
        @SerializedName("display_style")
        @Expose
        private String displayStyle;
        @SerializedName("is_subcategory")
        @Expose
        private Boolean isSubcategory;
        @SerializedName("type")
        @Expose
        private String type;

        @SerializedName("order_number")
        @Expose
        private Integer orderNumber;

        @SerializedName("display_name")
        @Expose
        private String dispalyName;

        public Response() {
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public Boolean getIsDashboard() {
            return isDashboard;
        }

        public void setIsDashboard(Boolean isDashboard) {
            this.isDashboard = isDashboard;
        }

        public Boolean getIsSideMenu() {
            return isSideMenu;
        }

        public void setIsSideMenu(Boolean isSideMenu) {
            this.isSideMenu = isSideMenu;
        }

        public String getDisplayStyle() {
            return displayStyle;
        }

        public void setDisplayStyle(String displayStyle) {
            this.displayStyle = displayStyle;
        }

        public Boolean getIsSubcategory() {
            return isSubcategory;
        }

        public void setIsSubcategory(Boolean isSubcategory) {
            this.isSubcategory = isSubcategory;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(Integer orderNumber) {
            this.orderNumber = orderNumber;
        }

        public void setDispalyName(String dispalyName) {
            this.dispalyName = dispalyName;
        }

        public String getDispalyName() {
            return dispalyName;
        }
    }

}
