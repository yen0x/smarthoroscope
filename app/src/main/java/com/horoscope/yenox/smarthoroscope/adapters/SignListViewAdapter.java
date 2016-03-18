package com.horoscope.yenox.smarthoroscope.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.horoscope.yenox.smarthoroscope.R;
import com.horoscope.yenox.smarthoroscope.fragments.SignListFragment;
import com.horoscope.yenox.smarthoroscope.models.ui.SignContent;

import java.util.Arrays;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a
 * {@link SignContent.SignItem}
 * and makes a call to the specified
 * {@link SignListFragment.OnListFragmentInteractionListener}. TODO: Replace the
 * implementation with code for your data type.
 */
public class SignListViewAdapter extends RecyclerView.Adapter<SignListViewAdapter.ViewHolder> {

    private final List<SignContent.SignItem> mValues;
    private final SignListFragment.OnListFragmentInteractionListener mListener;
    private final static String[] redSigns = {"Cancer", "Capricorne", "Poisson", "Scorpion", "Taureau", "Vierge"};

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
        String name = mValues.get(position).name;
        holder.mSignNameView.setText(name);
        Context context = holder.mSignImageView.getContext();
        int drawableId = context.getResources().getIdentifier(name.replace("é", "e").toLowerCase(), "drawable", context.getPackageName());
        holder.mSignImageView.setImageDrawable(context.getResources().getDrawable(drawableId));

        /*if(!Arrays.asList(redSigns).contains(name)) {
            holder.mSignNameView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            holder.mSignNameView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }*/

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
        public final ImageView mSignImageView;
        public SignContent.SignItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mSignNameView = (TextView) view.findViewById(R.id.sign_name);
            mSignImageView = (ImageView) view.findViewById(R.id.sign_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSignNameView.getText() + "'";
        }
    }
}
