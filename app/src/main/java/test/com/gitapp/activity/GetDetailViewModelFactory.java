package test.com.gitapp.activity;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import test.com.gitapp.viewModel.GetDetailModel;

public class GetDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    private Application mApplication;
    private String mParam;


    public GetDetailViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new GetDetailModel(mApplication, mParam);
    }

/*
 prefence for custom viewmodel
*/
//    https://stackoverflow.com/questions/46283981/android-viewmodel-additional-arguments
}
