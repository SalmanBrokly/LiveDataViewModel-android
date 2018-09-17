package test.com.gitapp.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.com.gitapp.apiCall.ApiClient;
import test.com.gitapp.apiCall.ApiInterface;
import test.com.gitapp.model.DetailList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class GetDetailModel extends ViewModel {


    private MutableLiveData<DetailList> mutableLiveData;


    public GetDetailModel(Application application,String userProfile){


        loadDetailRepositories(userProfile);
    }

    /**
     * @return Repositories List
     */
    public LiveData<DetailList> getRepositoriesList(String userProfile) {

        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();

        }

//        loadDetailRepositories(userProfile);
        return mutableLiveData;
    }


    private void loadDetailRepositories(String userProfile) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<DetailList> detailCall = apiService.getItems(userProfile);

        detailCall.enqueue(new Callback<DetailList>() {
            @Override
            public void onResponse(Call<DetailList> call, Response<DetailList> response) {
                if (response.code() == 200) {
//                    Log.e("")
                    mutableLiveData.setValue(response.body());

                }

                Log.e("RESPONSE", response.body().toString());

            }

            @Override
            public void onFailure(Call<DetailList> call, Throwable t) {

                Log.e("RESPONSE", t.toString());
            }
        });
    }


}