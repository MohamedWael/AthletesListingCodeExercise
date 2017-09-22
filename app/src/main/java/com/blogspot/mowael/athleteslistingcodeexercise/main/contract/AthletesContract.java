package com.blogspot.mowael.athleteslistingcodeexercise.main.contract;

import com.blogspot.mowael.athleteslistingcodeexercise.main.pojo.Athlete;
import com.blogspot.mowael.molib.network.listeners.ServiceResponseListener;
import com.blogspot.mowael.molib.presenter.MoContract;

import java.util.List;

/**
 * Created by moham on 9/21/2017.
 */

public interface AthletesContract {

    interface AthletesView extends MoContract.MoView {

        void onShowProgress(boolean show);

        void onHandleSuccessResponse(List<Athlete> athletes);

        void onHandleError();
    }

    interface AthletesPresenter extends MoContract.MoPresenter {
        void getData();
    }

    interface AthletesService extends MoContract.MoBusiness {
        void getData(ServiceResponseListener responseListener);
    }

}
