package com.jain.vidhyasagarsant.data.remote.model.gallery;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryOutputModel implements Parcelable {

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

    public static class Response implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("type")
        @Expose
        private String type;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


        protected Response(Parcel in) {
            id = in.readByte() == 0x00 ? null : in.readInt();
            description = in.readString();
            link = in.readString();
            type = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) (0x00));
            } else {
                dest.writeByte((byte) (0x01));
                dest.writeInt(id);
            }
            dest.writeString(description);
            dest.writeString(link);
            dest.writeString(type);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
            @Override
            public Response createFromParcel(Parcel in) {
                return new Response(in);
            }

            @Override
            public Response[] newArray(int size) {
                return new Response[size];
            }
        };
    }


    protected GalleryOutputModel(Parcel in) {
        error = in.readByte() == 0x00 ? null : in.readInt();
        detail = in.readString();
        if (in.readByte() == 0x01) {
            response = new ArrayList<Response>();
            in.readList(response, Response.class.getClassLoader());
        } else {
            response = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (error == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(error);
        }
        dest.writeString(detail);
        if (response == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(response);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<GalleryOutputModel> CREATOR = new Parcelable.Creator<GalleryOutputModel>() {
        @Override
        public GalleryOutputModel createFromParcel(Parcel in) {
            return new GalleryOutputModel(in);
        }

        @Override
        public GalleryOutputModel[] newArray(int size) {
            return new GalleryOutputModel[size];
        }
    };
}