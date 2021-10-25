package com.eebv.android.dynamicfragments;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eebv.android.dynamicfragments.placeholder.PlaceholderContent;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ITEM = "item";

    // TODO: Rename and change types of parameters
    private PlaceholderContent.PlaceholderItem mCountry;

    public void setmCountry(PlaceholderContent.PlaceholderItem mCountry) {
        this.mCountry = mCountry;
    }

    public CountryDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param item country.
     * @return A new instance of fragment CountryDetail.
     */
    public static CountryDetail newInstance(PlaceholderContent.PlaceholderItem item) {
        CountryDetail fragment = new CountryDetail();

        if(item != null){
            Bundle args = new Bundle();
            args.putParcelable(ITEM, item);
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCountry = getArguments().getParcelable(ITEM);
        } else
            mCountry = PlaceholderContent.getDefaultItem(getResources(), getContext().getPackageName());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_country_detail, container, false);

        this.customizeActionBar();

        ImageView iv = view.findViewById(R.id.countryPicture);
        iv.setImageResource(getImageId(mCountry.picture));

        TextView tv = view.findViewById(R.id.countryDescription);
        tv.setText(mCountry.details);

        return view;
    }

    private void customizeActionBar() {
        final ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        supportActionBar.setTitle(mCountry.country);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        //supportActionBar.setDisplayShowHomeEnabled(true);
    }

    private int getImageId(String imagePath) {
        String imageName = imagePath.substring(imagePath.lastIndexOf("/")+1, imagePath.lastIndexOf("."));
        return getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());
    }
}