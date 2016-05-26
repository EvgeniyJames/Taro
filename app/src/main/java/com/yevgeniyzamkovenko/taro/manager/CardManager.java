package com.yevgeniyzamkovenko.taro.manager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.yevgeniyzamkovenko.taro.R;
import com.yevgeniyzamkovenko.taro.actor.Card;
import com.yevgeniyzamkovenko.taro.utils.MiscUtils;

import java.util.ArrayList;

/**
 * User: EvgeniyJames
 * Date: 26.05.2016
 */
public class CardManager {

    private static CardManager s_instance;

    private Context m_context;

    private ArrayList<Card> m_cards;

    public static CardManager GetInstance() {
        if (s_instance == null) {
            CreateInstance();
        }
        return s_instance;
    }

    public void Init(Context context) {
        m_context = context;

        InitCards();
    }

    public void InitCards() {

        if (m_cards == null) {
            m_cards = new ArrayList<>();
        }

        ResetCards();

        AddCard(0, m_context.getString(R.string.card_fool_desc), ContextCompat
                .getDrawable(m_context, R.drawable.card_fool), m_context.getString(R.string.txt_fool_name));
        AddCard((1), m_context.getString(R.string.card_bonifacio_desc), ContextCompat
                .getDrawable(m_context, R.drawable.card_bonifacio_bembo), m_context.getString(R.string.txt_bonifacio_name));
        AddCard((2), m_context.getString(R.string.card_magician_desc), ContextCompat
                .getDrawable(m_context, R.drawable.card_magician_visconti), m_context.getString(R.string.txt_magician_name));
    }

    private void AddCard(int id, String desc, Drawable image, String name) {
        m_cards.add(new Card(id, desc, image, name));
    }

    private void ResetCards() {
        m_cards.clear();
    }

    private static void CreateInstance() {
        s_instance = new CardManager();
    }

    public Card GetFreeCard() {
        int size = m_cards.size();
        int index = MiscUtils.Rand(0, size);
        Card card = m_cards.get(index);
        m_cards.remove(index);
        return card;
    }

}
