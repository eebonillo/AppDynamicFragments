package com.eebv.android.dynamicfragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.eebv.android.dynamicfragments.placeholder.PlaceholderContent;

public class MainActivity extends AppCompatActivity implements CountriesRecyclerViewAdapter.ItemClickListener{

    FragmentManager fragmentManager;
    PlaceholderContent.PlaceholderItem country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        updateFragmentContent(savedInstanceState);
        Log.i("pruebas", "onCreate: " + fragmentManager);
    }

    private void replaceCountriesFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CountriesFragment countryListFragment=CountriesFragment.newInstance();
        fragmentTransaction.replace(R.id.fragmentContainerView,countryListFragment);
        fragmentTransaction.commit();
    }

    private void replaceCountryDescriptionFragment(int fragmentContainerId, PlaceholderContent.PlaceholderItem item){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        CountryDetail countryDescriptionFragment=CountryDetail.newInstance(item);

        fragmentTransaction.replace(fragmentContainerId,countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStackImmediate();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onItemClick(PlaceholderContent.PlaceholderItem item) {
        if(findViewById(R.id.fragmentContainerView) != null){
            replaceCountryDescriptionFragment(R.id.fragmentContainerView, item);
        }else {
            replaceCountryDescriptionFragment(R.id.fragmentContainerViewDetail,item);
        }
        if(item != null) {
            this.country = item;
        }
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CountryDetail.ITEM, country);
    }

    /*
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateFragmentContent(savedInstanceState);
    }*/

    private void updateFragmentContent(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            this.country = savedInstanceState.getParcelable(CountryDetail.ITEM);
            if (country != null) {
                Fragment currentInContainerView = fragmentManager.findFragmentById(R.id.fragmentContainerView);
                if (findViewById(R.id.fragmentContainerView) != null ) {
                    if(currentInContainerView != null && currentInContainerView instanceof CountryDetail)
                        ((CountryDetail) currentInContainerView).setmCountry(country);
                    else
                        replaceCountryDescriptionFragment(R.id.fragmentContainerView, country);
                } else {
                    CountryDetail currentDetail = (CountryDetail) fragmentManager.findFragmentById(R.id.fragmentContainerViewDetail);
                    currentDetail.setmCountry(country);
                }
            }
        }
    }
}