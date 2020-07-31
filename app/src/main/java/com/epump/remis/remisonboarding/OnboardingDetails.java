package com.epump.remis.remisonboarding;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class OnboardingDetails implements Parcelable {

    private String title;
    private String subtitle;
    private int drawable;


    public static ArrayList<OnboardingDetails> getDetails() {
        ArrayList<OnboardingDetails> details = new ArrayList<>();
        OnboardingDetails onboardingdetailsone = new OnboardingDetails("Savings","Get Discounts on Every Fueling Transactions. Plus, buy airtime, pay bills, make transfers… all from your app",R.drawable.animation_one);
        details.add(onboardingdetailsone);
        OnboardingDetails onboardingdetailstwo = new OnboardingDetails("Reward","Sign up now and get a N250 Registration Bonus",R.drawable.animation_two);
        details.add(onboardingdetailstwo);
        OnboardingDetails onboardingdetailsthree = new OnboardingDetails("Refer Your Friends","Get additional bonus when you refer a friend today.",R.drawable.animation_three);
        details.add(onboardingdetailsthree);

        return details;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public OnboardingDetails(String title, String subtitle, int drawable) {
        this.title = title;
        this.subtitle = subtitle;
        this.drawable = drawable;
    }

    protected OnboardingDetails(Parcel in) {
        title = in.readString();
        subtitle = in.readString();
        drawable = in.readInt();
    }

    public static final Creator<OnboardingDetails> CREATOR = new Creator<OnboardingDetails>() {
        @Override
        public OnboardingDetails createFromParcel(Parcel in) {
            return new OnboardingDetails(in);
        }

        @Override
        public OnboardingDetails[] newArray(int size) {
            return new OnboardingDetails[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeInt(drawable);
    }
}
