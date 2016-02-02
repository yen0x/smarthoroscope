package com.horoscope.yenox.smarthoroscope.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horoscope.yenox.smarthoroscope.R;
import com.horoscope.yenox.smarthoroscope.fragments.CategoryFragment;
import com.horoscope.yenox.smarthoroscope.models.ui.CategoryContent;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a
 * {@link CategoryContent.CategoryItem}
 * and makes a call to the specified
 * {@link CategoryFragment.OnListFragmentInteractionListener}.
 */
public class CategoryListViewAdapter extends RecyclerView.Adapter<CategoryListViewAdapter.ViewHolder> {

    private final List<CategoryContent.CategoryItem> mValues;
    private final CategoryFragment.OnListFragmentInteractionListener mListener;

    public CategoryListViewAdapter(List<CategoryContent.CategoryItem> items, CategoryFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_horoscope_category_attr, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mAttrNameView.setText(mValues.get(position).name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if
                    // the
                    // fragment is attached to one) that an item has been
                    // selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAttrNameView;
        public CategoryContent.CategoryItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAttrNameView = (TextView) view.findViewById(R.id.attribute_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAttrNameView.getText() + "'";
        }
    }
}
