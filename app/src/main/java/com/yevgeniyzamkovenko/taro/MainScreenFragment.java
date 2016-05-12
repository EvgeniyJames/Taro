package com.yevgeniyzamkovenko.taro;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainScreenFragment extends Fragment {

    private Fragment m_threeCard;
    private Fragment m_CeltCross;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ConfigFragments();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        Button btnThreeCards = (Button) view.findViewById(R.id.btn_card_type_three_cards);
        btnThreeCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container, m_threeCard);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void ConfigFragments() {
        m_CeltCross = new CeltCrossFragment();
        m_threeCard = new ThreeCardsFragment();
    }
}
