package com.yevgeniyzamkovenko.taro.actor;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.yevgeniyzamkovenko.taro.fragment.CardInfoDialog;

/**
 * User: EvgeniyJames
 * Date: 26.05.2016
 */
public class Card implements View.OnClickListener{

    private int m_id;
    private String m_description;
    private Drawable m_image;
    private String m_name;

    private boolean m_wasPressed;
    private Activity m_context;

    public Card(int id, String description, Drawable image, String name) {
        m_id = id;
        m_description = description;
        m_image = image;
        m_name = name;
    }

    public int getId() {
        return m_id;
    }

    public String getDescription() {
        return m_description;
    }

    public String getName() {
        return m_name;
    }

    public Drawable getImage() {
        return m_image;
    }

    @Override
    public void onClick(View v) {
        if (!m_wasPressed) {
            m_wasPressed = true;
            ((ImageView) v).setImageDrawable(getImage());
        } else {
            CardInfoDialog fragment = new CardInfoDialog();
            fragment.SetCard(this);
            fragment.show(m_context.getFragmentManager(), "cardInfo");
        }
    }

    public void setContext(Activity context) {
        m_context = context;
    }
}
