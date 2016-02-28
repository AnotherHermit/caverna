package ch.quazz.caverna.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    public void setupFragment(final PlayerScore playerScore, ArrayList<Item> items){
        setItems(items);
        setPlayerScore(playerScore);
    }

    public static PlayerScoreFragment newInstance(String title) {
        PlayerScoreFragment playerScoreFragment = new PlayerScoreFragment();
        Bundle args = new Bundle();
        args.putString("Title", title);
        playerScoreFragment.setArguments(args);
        return playerScoreFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.title = getArguments().getString("Title");
        super.onCreate(savedInstanceState);
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
        PlayerScoreAdapter adapter = new PlayerScoreAdapter(getActivity(), items, playerScore);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StoppableLinearLayoutManager(getActivity()));
    }


}
