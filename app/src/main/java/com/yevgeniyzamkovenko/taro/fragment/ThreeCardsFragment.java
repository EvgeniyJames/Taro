package com.yevgeniyzamkovenko.taro.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yevgeniyzamkovenko.taro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeCardsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three_cards, container, false);
        return view;
    }

}
