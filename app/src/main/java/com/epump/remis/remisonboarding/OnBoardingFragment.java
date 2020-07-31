package com.epump.remis.remisonboarding;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class OnBoardingFragment extends Fragment {
    private TextView animationTitle, animationSubtitle;
    private ImageView animationImage;
    OnboardingDetails mOnboardingDetails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mOnboardingDetails = bundle.getParcelable("details");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboading_slides, container, false);
        animationImage = view.findViewById(R.id.animation_image);
        animationTitle = view.findViewById(R.id.animation_title);
        animationSubtitle = view.findViewById(R.id.animation_subtitle);
        setDetails();

        return view;

    }

    private void setDetails() {
        Glide.with(this).load(mOnboardingDetails.getDrawable()).into(animationImage);
        animationTitle.setText(mOnboardingDetails.getTitle());
        animationSubtitle.setText(mOnboardingDetails.getSubtitle());
    }
}
