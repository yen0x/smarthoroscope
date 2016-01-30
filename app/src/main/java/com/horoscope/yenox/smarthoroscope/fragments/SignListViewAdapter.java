package com.horoscope.yenox.smarthoroscope.fragments;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horoscope.yenox.smarthoroscope.R;
import com.horoscope.yenox.smarthoroscope.fragments.sign.SignContent;

/**
 * {@link RecyclerView.Adapter} that can display a
 * {@link com.horoscope.yenox.smarthoroscope.fragments.sign.SignContent.SignItem}
 * and makes a call to the specified
 * {@link SignListFragment.OnListFragmentInteractionListener}. TODO: Replace the
 * implementation with code for your data type.
 */
public class SignListViewAdapter extends RecyclerView.Adapter<SignListViewAdapter.ViewHolder> {

    private final List<SignContent.SignItem> mValues;
    private final SignListFragment.OnListFragmentInteractionListener mListener;

    public SignListViewAdapter(List<SignContent.SignItem> items, SignListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_sign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mSignNameView.setText(mValues.get(position).name);

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
        public final TextView mSignNameView;
        public SignContent.SignItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mSignNameView = (TextView) view.findViewById(R.id.sign_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSignNameView.getText() + "'";
        }
    }
}
