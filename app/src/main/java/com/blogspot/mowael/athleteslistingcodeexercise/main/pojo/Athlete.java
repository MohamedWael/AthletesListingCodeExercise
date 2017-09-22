
package com.blogspot.mowael.athleteslistingcodeexercise.main.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Athlete implements Serializable {

    @SerializedName("brief")
    private String mBrief;
    @SerializedName("image")
    private String mImage;
    @SerializedName("name")
    private String mName;

    public String getBrief() {
        return mBrief;
    }

    public void setBrief(String brief) {
        mBrief = brief;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
