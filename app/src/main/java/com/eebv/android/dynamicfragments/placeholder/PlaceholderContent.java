package com.eebv.android.dynamicfragments.placeholder;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.eebv.android.dynamicfragments.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public final List<PlaceholderItem> ITEMS = new ArrayList<PlaceholderItem>();
    public static PlaceholderItem DEFAULT;


    /*public PlaceholderContent(List<String[]> countries) {
        for(int i=0; i<countries.size(); i++) {
            String [] country = countries.get(i);
            PlaceholderItem item = new PlaceholderItem(i+"", country[0], country[1], country[2]);
            ITEMS.add(item);
        }

    }*/

    public PlaceholderContent(Resources resources, String packageName) {
        String[] countries = resources.getStringArray(R.array.countries);
        for(int i=0; i< countries.length; i++) {
            ITEMS.add(getItem(resources, packageName, countries[i], i));
        }
    }
    private static PlaceholderItem getItem(Resources resources, String packageName, String country, int id) {
        String [] countryDetail = resources.getStringArray(resources.getIdentifier(country, "array", packageName));
        PlaceholderItem item = new PlaceholderItem(id+"", countryDetail[0], countryDetail[1], countryDetail[2]);
        DEFAULT = item;
        return item;
    }

    public List<PlaceholderItem> getItems() {
        return ITEMS;
    }

    public static PlaceholderItem getDefaultItem(Resources resources, String packageName) {
        int defaultItem = 0;
        String country = resources.getStringArray(R.array.countries)[defaultItem];
        return getItem(resources, packageName, country, defaultItem);
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem implements Parcelable {
        public final String id;
        public final String country;
        public final String picture;
        public final String details;

        public PlaceholderItem(String id, String country, String picture, String details) {
            this.id = id;
            this.country = country;
            this.picture = picture;
            this.details = details;
        }

        protected PlaceholderItem(Parcel in) {
            id = in.readString();
            country = in.readString();
            picture = in.readString();
            details = in.readString();
        }

        public static final Creator<PlaceholderItem> CREATOR = new Creator<PlaceholderItem>() {
            @Override
            public PlaceholderItem createFromParcel(Parcel in) {
                return new PlaceholderItem(in);
            }

            @Override
            public PlaceholderItem[] newArray(int size) {
                return new PlaceholderItem[size];
            }
        };

        @Override
        public String toString() {
            return ""; // TODO
        }

        @Override
        public int describeContents() {
            return 0; // TODO
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(id);
            parcel.writeString(country);
            parcel.writeString(picture);
            parcel.writeString(details);
        }
    }
}