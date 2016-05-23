package com.kulomady.tokopedia.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.kulomady.tokopedia.R;
import com.kulomady.tokopedia.view.utils.ViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseDrawerActivity {

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    @BindView(R.id.fabSearch)
    FloatingActionButton fabSearch;
    @BindView(R.id.content)
    CoordinatorLayout clContent;

    private boolean pendingIntroAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    @OnClick(R.id.fabSearch)
    void fabClicked() {
        Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show();
    }

    private void startIntroAnimation() {
        fabSearch.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        int actionbarSize = ViewUtils.dpToPx(56);
        if(getToolbar()!=null) {
            getToolbar().setTranslationY(-actionbarSize);

            if(getTokopediaLogoTextView()!=null) {
                getTokopediaLogoTextView().setTranslationY(-actionbarSize);
            }

            getCartMenuItem().getActionView().setTranslationY(-actionbarSize);

            getToolbar().animate()
                    .translationY(0)
                    .setDuration(ANIM_DURATION_TOOLBAR)
                    .setStartDelay(300);
            getTokopediaLogoTextView().animate()
                    .translationY(0)
                    .setDuration(ANIM_DURATION_TOOLBAR)
                    .setStartDelay(400);
            getCartMenuItem().getActionView().animate()
                    .translationY(0)
                    .setDuration(ANIM_DURATION_TOOLBAR)
                    .setStartDelay(500)
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
