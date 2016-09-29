/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.hack.arm.view.utils.ViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseDrawerActivity {

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    @BindView(com.hack.arm.R.id.fabSearch)
    FloatingActionButton fabSearch;
    @BindView(com.hack.arm.R.id.content)
    CoordinatorLayout clContent;

    private boolean pendingIntroAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.hack.arm.R.layout.activity_main);
        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }


    @OnClick(com.hack.arm.R.id.fabSearch)
    void fabClicked() {
        Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show();
        this.navigator.navigateToProductResult(this, "mouse");
    }

    private void startIntroAnimation() {
        fabSearch.setTranslationY(2 * getResources().getDimensionPixelOffset(com.hack.arm.R.dimen.btn_fab_size));
        int actionbarSize = ViewUtils.dpToPx(56);
        if(getToolbar()!=null) {
            getToolbar().setTranslationY(-actionbarSize);

            if(getLogoTitle()!=null) {
                getLogoTitle().setTranslationY(-actionbarSize);
            }

            getToolbar().animate()
                    .translationY(0)
                    .setDuration(ANIM_DURATION_TOOLBAR)
                    .setStartDelay(300);
            getLogoTitle().animate()
                    .translationY(0)
                    .setDuration(ANIM_DURATION_TOOLBAR)
                    .setStartDelay(400)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            startContentAnimation();
                        }
                    })
                    .start();

        }
    }

    private void startContentAnimation() {
        fabSearch.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_FAB)
                .start();
    }

}
