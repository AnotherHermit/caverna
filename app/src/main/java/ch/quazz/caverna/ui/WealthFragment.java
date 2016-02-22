package ch.quazz.caverna.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.quazz.caverna.score.Token;
import ch.quazz.caverna.R;
import ch.quazz.caverna.score.TokenItem;

public class WealthFragment extends PlayerScoreFragment {
/*
    private static final TokenAdapter.Item wealthItems[] = {
        new TokenAdapter.Item(R.id.dogs, Token.Dogs),
        new TokenAdapter.Item(R.id.sheep, Token.Sheep),
        new TokenAdapter.Item(R.id.donkeys, Token.Donkeys),
        new TokenAdapter.Item(R.id.boars, Token.Boars),
        new TokenAdapter.Item(R.id.cattle, Token.Cattle),

        new TokenAdapter.Item(R.id.grain, Token.Grains),
        new TokenAdapter.Item(R.id.vegetable, Token.Vegetables),

        new TokenAdapter.Item(R.id.rubies, Token.Rubies),
        new TokenAdapter.Item(R.id.gold, Token.Gold),
        new TokenAdapter.Item(R.id.begging_markers, Token.BeggingMarkers),

        new TokenAdapter.Item(R.id.small_pastures, Token.SmallPastures),
        new TokenAdapter.Item(R.id.large_pastures, Token.LargePastures),
        new TokenAdapter.Item(R.id.ore_mines, Token.OreMines),
        new TokenAdapter.Item(R.id.ruby_mines, Token.RubyMines),
        new TokenAdapter.Item(R.id.unused_tiles, Token.UnusedSpace)
    };*/
//    private final TokenAdapter wealthController;

    public WealthFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wealth, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.wealth_recycler);
        TokenAdapter adapter = new TokenAdapter(getActivity(), TokenItem.getWealthItems(), playerScore);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        wealthController.setup(playerScore, getActivity());
    }
}
