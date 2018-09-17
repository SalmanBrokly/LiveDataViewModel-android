package test.com.gitapp.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import test.com.gitapp.R;
import test.com.gitapp.adapter.GitListAdapter;
import test.com.gitapp.model.GitList;
import test.com.gitapp.viewModel.GetProjectModel;
import test.com.gitapp.databinding.ActivityLoginBinding;


public class ListActivity extends AppCompatActivity {


    private ActivityLoginBinding mBinding;
    GitListAdapter mAdapter;
List<GitList> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mBinding.toolBar.tvTitle.setText(R.string.git_project);
        mList= new ArrayList<>();
         mAdapter = new GitListAdapter( ListActivity.this);
        mAdapter.setAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListActivity.this);
        mBinding.myRecyclerView.setLayoutManager(mLayoutManager);
        mBinding.myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mBinding.myRecyclerView.setAdapter(mAdapter);


    callGetRepositories();




    }

    /**
     * Calling API to get the Repositories
     */
    private void callGetRepositories() {


        GetProjectModel categoryModel = ViewModelProviders.of(this).get(GetProjectModel.class);

        categoryModel.getRepositoriesList().observe(this, new Observer<List<GitList>>() {

            @Override
            public void onChanged(@Nullable List<GitList> gitLists) {
                mList=gitLists;
                Log.e("hold data","valuesssss");
                mAdapter.setAdapter(mList);
//                mAdapter.notifyDataSetChanged();

            }
        });



    }

}
