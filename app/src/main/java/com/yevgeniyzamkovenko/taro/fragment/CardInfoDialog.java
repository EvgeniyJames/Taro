package com.yevgeniyzamkovenko.taro.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yevgeniyzamkovenko.taro.R;
import com.yevgeniyzamkovenko.taro.actor.Card;

/**
 * User: EvgeniyJames
 * Date: 26.05.2016
 */
public class CardInfoDialog extends DialogFragment{

    private Card m_card;

    public void SetCard(Card card) {
        m_card = card;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_card_info, container, false);

        getDialog().setTitle(m_card.getName());

        TextView desc = (TextView) view.findViewById(R.id.txt_card_description);
        desc.setText(m_card.getDescription());

        return view;
    }
}
