
package com.blogspot.mowael.athleteslistingcodeexercise.main.pojo;

import java.util.List;
import javax.annotation.Generated;

import com.blogspot.mowael.molib.network.pojo.GeneralResponse;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Response extends GeneralResponse {

    @SerializedName("athletes")
    private List<Athlete> mAthletes;

    public List<Athlete> getAthletes() {
        return mAthletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        mAthletes = athletes;
    }

}
