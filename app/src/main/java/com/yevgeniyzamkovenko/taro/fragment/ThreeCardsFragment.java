package com.yevgeniyzamkovenko.taro.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yevgeniyzamkovenko.taro.R;
import com.yevgeniyzamkovenko.taro.actor.Card;
import com.yevgeniyzamkovenko.taro.manager.CardManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeCardsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three_cards, container, false);

        CardManager.GetInstance().InitCards();

        ConfigImages(view);

        return view;
    }

    private void ConfigImages(final View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.innercontainer);
        int size = linearLayout.getChildCount();
        for (int i = 0; i < size; i++) {
            final View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof ImageView) {
                Card freeCard = CardManager.GetInstance().GetFreeCard();
                freeCard.setContext(getActivity());
                childAt.setOnClickListener(freeCard);
            }
        }
    }
}
