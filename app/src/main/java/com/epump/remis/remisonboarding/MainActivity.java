package com.epump.remis.remisonboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mOnBoardingScreens;
    private OnBoardingViewPagerAdapter mOnBoardingViewPagerAdapter;
    private LinearLayout mTabIndicators;
    private CountDownTimer mCountDownTimer;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOnBoardingScreens = findViewById(R.id.animation_container);
        mTabIndicators = findViewById(R.id.layout_onboarding_indicator);
        initViewPager();
        initTabIndicators();
        setCurrentIndicator(0);

        mOnBoardingScreens.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mCountDownTimer.cancel();
                setUpCountdown();
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        mOnBoardingScreens.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float position) {
                Log.d(TAG, "transformPage: called");
                if(position <= -1.0F || position >= 1.0F) {
                    view.setTranslationX(view.getWidth() * position);
                    view.setAlpha(0.0F);

                } else if( position == 0.0F ) {
                    view.setTranslationX(view.getWidth() * position);
                    view.setAlpha(1.0F);
                } else {
                    // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                    view.setTranslationX(view.getWidth() * -position);
                    view.setAlpha(1.0F - Math.abs(position));

                }

            }
        });
        setUpCountdown();

    }

    private void setUpCountdown() {
        mCountDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(mOnBoardingScreens.getCurrentItem() == 2){
                    mOnBoardingScreens.scheduleLayoutAnimation();
                    mOnBoardingScreens.setCurrentItem(0,true);
                }else{
                    mOnBoardingScreens.setCurrentItem(mOnBoardingScreens.getCurrentItem()+1,true);
                }
                setUpCountdown();
            }
        }.start();
    }

    private void initTabIndicators() {
        ImageView[] indicators = new ImageView[mOnBoardingViewPagerAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i = 0;i<indicators.length;i++){
            indicators[i] =  new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.tab_selected_default));
            indicators[i].setLayoutParams(layoutParams);
            mTabIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int index){
        int childCount = mTabIndicators.getChildCount();
        for(int i =0;i<childCount;i++){
            ImageView imageView = (ImageView) mTabIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.tab_indicator_selected));

            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.tab_selected_default ));

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCountDownTimer != null){
            mCountDownTimer.cancel();
        }
    }

    private void initViewPager(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        for(OnboardingDetails onboardingDetails : OnboardingDetails.getDetails()){
            Bundle bundle = new Bundle();
            bundle.putParcelable("details",onboardingDetails);
            OnBoardingFragment onBoardingFragment = new OnBoardingFragment();
            onBoardingFragment.setArguments(bundle);
            fragments.add(onBoardingFragment);

        }
        mOnBoardingViewPagerAdapter = new OnBoardingViewPagerAdapter(getSupportFragmentManager(), new Lifecycle() {
            @Override
            public void addObserver(@NonNull LifecycleObserver observer) {

            }

            @Override
            public void removeObserver(@NonNull LifecycleObserver observer) {

            }

            @NonNull
            @Override
            public State getCurrentState() {
                return null;
            }
        }, fragments);
        mOnBoardingScreens.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mOnBoardingScreens.setAdapter(mOnBoardingViewPagerAdapter);

    }

    public void loginUser(View view) {
    }

    public void signupUser(View view) {
    }
}
