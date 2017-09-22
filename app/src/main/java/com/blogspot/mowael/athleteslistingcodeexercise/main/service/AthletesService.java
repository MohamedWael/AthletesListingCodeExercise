package com.blogspot.mowael.athleteslistingcodeexercise.main.service;

import com.blogspot.mowael.athleteslistingcodeexercise.base.AthleteAPIConstants;
import com.blogspot.mowael.athleteslistingcodeexercise.main.contract.AthletesContract;
import com.blogspot.mowael.athleteslistingcodeexercise.main.pojo.Response;
import com.blogspot.mowael.molib.business.MoBusiness;
import com.blogspot.mowael.molib.network.listeners.ServiceResponseListener;
import com.google.gson.JsonSyntaxException;

/**
 * Created by moham on 9/21/2017.
 */

public class AthletesService extends MoBusiness<Response> implements AthletesContract.AthletesService {

    public void getData(ServiceResponseListener responseListener) {
        try {
            executeGETForType(Response.class, AthleteAPIConstants.BASE_URL, null, responseListener);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
