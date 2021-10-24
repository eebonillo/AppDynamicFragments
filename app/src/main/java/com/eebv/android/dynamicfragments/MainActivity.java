package com.eebv.android.dynamicfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.eebv.android.dynamicfragments.placeholder.PlaceholderContent;

public class MainActivity extends AppCompatActivity implements CountriesRecyclerViewAdapter.ItemClickListener{

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragmentContainerViewDetail) == null){
            if(savedInstanceState==null){
                addCountriesFragment();
            }else{
                addCountryDescriptionFragment(R.id.fragmentContainerView, savedInstanceState.getParcelable(CountryDetail.ITEM));
            }
        }else {
            addCountriesFragment();
            if(savedInstanceState==null){
                addCountryDescriptionFragment(R.id.fragmentContainerViewDetail, null);
            }else{
                addCountryDescriptionFragment(R.id.fragmentContainerViewDetail,savedInstanceState.getParcelable(CountryDetail.ITEM));
            }
        }

    }

    private void addCountriesFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CountriesFragment countryListFragment=CountriesFragment.newInstance();
        fragmentTransaction.replace(R.id.fragmentContainerView,countryListFragment);
        fragmentTransaction.commit();
    }

    private void addCountryDescriptionFragment(int fragmentContainerId, PlaceholderContent.PlaceholderItem item){
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
        if(findViewById(R.id.fragmentContainerViewDetail) == null){
            addCountryDescriptionFragment(R.id.fragmentContainerView, item);
        }else {
            addCountryDescriptionFragment(R.id.fragmentContainerViewDetail,item);
        }
    }
}