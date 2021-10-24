package com.eebv.android.dynamicfragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eebv.android.dynamicfragments.placeholder.PlaceholderContent.PlaceholderItem;
import com.eebv.android.dynamicfragments.databinding.FragmentCountriesBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CountriesRecyclerViewAdapter extends RecyclerView.Adapter<CountriesRecyclerViewAdapter.ViewHolder> {

    private final ItemClickListener mItemClickListener;
    private final List<PlaceholderItem> mValues;

    public CountriesRecyclerViewAdapter(List<PlaceholderItem> items, ItemClickListener itemClickListener) {
        mValues = items;
        mItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentCountriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).country);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //public final TextView mIdView;
        public final TextView mContentView;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentCountriesBinding binding) {
            super(binding.getRoot());
            //mIdView = binding.itemNumber;
            mContentView = binding.content;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            int position = getBindingAdapterPosition();
            mItemClickListener.onItemClick(mItem);
        }
    }

    public interface ItemClickListener {
        void onItemClick(PlaceholderItem mItem);
    }
}