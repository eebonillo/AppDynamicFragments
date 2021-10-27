package com.eebv.android.dynamicfragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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

    private void replaceCountryDescriptionFragment(int fragmentContainerId, PlaceholderContent.PlaceholderItem item){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        CountryDetail countryDescriptionFragment=CountryDetail.newInstance(item);

        fragmentTransaction.replace(fragmentContainerId,countryDescriptionFragment);
        fragmentTransaction.addToBackStack(item == null ? "detail" : "list");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStackImmediate();
        customizeActionBar();
        return super.onSupportNavigateUp();
    }

    public void customizeActionBar() {
        final ActionBar supportActionBar = getSupportActionBar();

        int popBackStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if(findViewById(R.id.fragmentContainerView) != null && popBackStackEntryCount == 0) {
            supportActionBar.setTitle(R.string.select_country);
        } else if(country != null){
            supportActionBar.setTitle(country.country);
        }
        supportActionBar.setDisplayHomeAsUpEnabled(popBackStackEntryCount != 0);
    }

    @Override
    public void onItemClick(PlaceholderContent.PlaceholderItem item) {
        if(findViewById(R.id.fragmentContainerView) != null){
            replaceCountryDescriptionFragment(R.id.fragmentContainerView, item);
        }else {
            replaceCountryDescriptionFragment(R.id.fragmentContainerViewDetail,item);
        }
        //country = item;
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CountryDetail.ITEM, country);
    }

    private void updateFragmentContent(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            this.country = savedInstanceState.getParcelable(CountryDetail.ITEM);
            if (country != null) {
                Fragment currentInContainerView = fragmentManager.findFragmentById(R.id.fragmentContainerView);
                if (findViewById(R.id.fragmentContainerView) != null ) {
                    fragmentManager.popBackStackImmediate("list", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    replaceCountryDescriptionFragment(R.id.fragmentContainerView, country);
                } else {
                    CountryDetail currentDetail = (CountryDetail) fragmentManager.findFragmentById(R.id.fragmentContainerViewDetail);
                    currentDetail.setmCountry(country);
                }
            }
        }
        customizeActionBar();
    }

    public void setCountry(PlaceholderContent.PlaceholderItem country) {
        this.country = country;
    }
}