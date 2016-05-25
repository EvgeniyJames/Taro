package com.yevgeniyzamkovenko.taro.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yevgeniyzamkovenko.taro.R;
import com.yevgeniyzamkovenko.taro.utils.MiscUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeCardsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three_cards, container, false);

        ConfigImages(view);

        return view;
    }

    private void ConfigImages(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.innercontainer);
        int size = linearLayout.getChildCount();
        for (int i = 0; i < size; i++) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof ImageView) {
                childAt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MiscUtils.DEBUG(v.hashCode() + " ");
                    }
                });
            }
        }
    }

}
