package com.blogspot.mowael.athleteslistingcodeexercise.main.Presenter;

import com.android.volley.VolleyError;
import com.blogspot.mowael.athleteslistingcodeexercise.main.contract.AthletesContract;
import com.blogspot.mowael.athleteslistingcodeexercise.main.pojo.Response;
import com.blogspot.mowael.athleteslistingcodeexercise.main.service.AthletesService;
import com.blogspot.mowael.molib.network.listeners.ServiceResponseListener;
import com.blogspot.mowael.molib.network.pojo.GeneralResponse;
import com.blogspot.mowael.molib.presenter.MoPresenter;

import org.json.JSONObject;

/**
 * Created by moham on 9/21/2017.
 */

public class AthletesPresenter extends MoPresenter<AthletesContract.AthletesView> implements AthletesContract.AthletesPresenter {

    AthletesContract.AthletesService service;
    private final AthletesContract.AthletesView view;

    public AthletesPresenter(AthletesContract.AthletesView view) {
        super(view);
        this.view = view;
    }

    @Override
    public AthletesContract.AthletesView getView() {
        return view;
    }

    @Override
    public AthletesContract.AthletesService getService() {
        if (service == null) {
            service = new AthletesService();
        }
        return service;
    }

    @Override
    public void getData() {
        getView().onShowProgress(true);
        getService().getData(new ServiceResponseListener() {

            @Override
            public void onResponse(JSONObject response) {

            }

            @Override
            public <T extends GeneralResponse> void onResponseParsingSuccess(T response) {
                getView().onShowProgress(false);
                Response mResponse = (Response) response;
                getView().onHandleSuccessResponse(mResponse.getAthletes());
            }

            @Override
            public void onError(VolleyError error) {
                getView().onShowProgress(false);
                getView().onHandleError();
            }
        });
    }
}
