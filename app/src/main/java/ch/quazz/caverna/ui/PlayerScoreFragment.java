package ch.quazz.caverna.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ch.quazz.caverna.R;
import ch.quazz.caverna.score.Item;
import ch.quazz.caverna.score.PlayerScore;
import ch.quazz.caverna.widget.StoppableLinearLayoutManager;
import ch.quazz.caverna.widget.StoppableRecyclerView;

public class PlayerScoreFragment extends Fragment {

    private PlayerScore playerScore;
    private ArrayList<Item> items;
    private String title;

    void setPlayerScore(final PlayerScore playerScore) {
        this.playerScore = playerScore;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setupFragment(final PlayerScore playerScore, ArrayList<Item> items){
        setItems(items);
        setPlayerScore(playerScore);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player_score, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        StoppableRecyclerView recyclerView = (StoppableRecyclerView) getView().findViewById(R.id.recycler);
        ItemAdapter adapter = new ItemAdapter(getActivity(), items, playerScore);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StoppableLinearLayoutManager(getActivity()));
    }


}
