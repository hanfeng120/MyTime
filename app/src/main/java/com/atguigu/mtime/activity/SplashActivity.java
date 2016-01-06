package com.atguigu.mtime.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;

import com.atguigu.mtime.R;

public class SplashActivity extends AppCompatActivity {
    private RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);


        AnimationSet set = new AnimationSet(false);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);


        set.addAnimation(alphaAnimation);
        rlRoot.startAnimation(set);
        /**
         * 监听动画播放
         */
           set.setAnimationListener(new Animation.AnimationListener() {
               @Override
               public void onAnimationStart(Animation animation) {

               }

               @Override
               public void onAnimationEnd(Animation animation) {

                   startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                   finish();
               }

               @Override
               public void onAnimationRepeat(Animation animation) {

               }
           });


    }
}
