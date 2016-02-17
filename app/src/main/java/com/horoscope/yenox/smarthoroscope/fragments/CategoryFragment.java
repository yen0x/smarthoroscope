package com.horoscope.yenox.smarthoroscope.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.horoscope.yenox.smarthoroscope.R;
import com.horoscope.yenox.smarthoroscope.adapters.CategoryListViewAdapter;
import com.horoscope.yenox.smarthoroscope.models.Category;
import com.horoscope.yenox.smarthoroscope.models.ui.CategoryContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yenox on 1/31/16.
 */
public class CategoryFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_CATEGORY = "category";
    private static final String ARG_ATTR_NAME_LIST = "attrNameList";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public CategoryFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CategoryFragment newInstance(Category category) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_CATEGORY, category.getName());
        ArrayList<String> nameList = new ArrayList<>();
        for(Map.Entry<String, Integer> attr : category.getAttributes().entrySet()) {
            nameList.add(attr.getKey());
            args.putInt(attr.getKey(), attr.getValue());
        }
        args.putStringArrayList(ARG_ATTR_NAME_LIST, nameList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horoscope_list, container, false);
        // Set the adapter
        if (rootView instanceof RecyclerView) {
            Context context = rootView.getContext();
            RecyclerView recyclerView = (RecyclerView) rootView;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            ArrayList<String> nameList = getArguments().getStringArrayList(ARG_ATTR_NAME_LIST);
            List<CategoryContent.CategoryItem> categoryItems = new ArrayList<>();
            for (String attr : nameList) {
                categoryItems.add(new CategoryContent.CategoryItem(attr, getArguments().getInt(attr)));
            }
            recyclerView.setAdapter(new CategoryListViewAdapter(categoryItems, mListener));
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(CategoryContent.CategoryItem item);
    }
}
