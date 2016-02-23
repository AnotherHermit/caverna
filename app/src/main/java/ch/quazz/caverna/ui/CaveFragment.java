package ch.quazz.caverna.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.quazz.caverna.R;
import ch.quazz.caverna.score.Item;
import ch.quazz.caverna.score.Tile;

public class CaveFragment extends PlayerScoreFragment {

    public CaveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cave, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.cave_recycler);
        ItemAdapter adapter = new ItemAdapter(getActivity(), Item.getCaveItems(), playerScore);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
