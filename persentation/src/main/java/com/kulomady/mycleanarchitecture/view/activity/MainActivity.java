package com.kulomady.mycleanarchitecture.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kulomady.mycleanarchitecture.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {



    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.gotoListButton)
    void navigateToList() {
        this.navigator.navigateToUserList(this);
    }
}
