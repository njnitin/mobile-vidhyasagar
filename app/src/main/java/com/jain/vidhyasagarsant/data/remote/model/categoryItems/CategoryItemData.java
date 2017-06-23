package com.jain.vidhyasagarsant.data.remote.model.categoryItems;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryItemData implements Parcelable{

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    protected CategoryItemData(Parcel in) {
        if (in.readByte() == 0) {
            error = null;
        } else {
            error = in.readInt();
        }
        detail = in.readString();
        response = in.createTypedArrayList(Response.CREATOR);
    }

    public static final Creator<CategoryItemData> CREATOR = new Creator<CategoryItemData>() {
        @Override
        public CategoryItemData createFromParcel(Parcel in) {
            return new CategoryItemData(in);
        }

        @Override
        public CategoryItemData[] newArray(int size) {
            return new CategoryItemData[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (error == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(error);
        }
        dest.writeString(detail);
        dest.writeTypedList(response);
    }

    public static class Response implements Parcelable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("subtitle")
        @Expose
        private String subtitle;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("logo")
        @Expose
        private String logo;
        @SerializedName("description")
        @Expose
        private String description;

        protected Response(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            title = in.readString();
            subtitle = in.readString();
            link = in.readString();
            logo = in.readString();
            description = in.readString();
        }

        public static final Creator<Response> CREATOR = new Creator<Response>() {
            @Override
            public Response createFromParcel(Parcel in) {
                return new Response(in);
            }

            @Override
            public Response[] newArray(int size) {
                return new Response[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(title);
            dest.writeString(subtitle);
            dest.writeString(link);
            dest.writeString(logo);
            dest.writeString(description);
        }
    }

}