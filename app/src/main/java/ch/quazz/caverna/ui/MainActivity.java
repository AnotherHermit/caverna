package ch.quazz.caverna.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import ch.quazz.caverna.data.CavernaDbHelper;
import ch.quazz.caverna.data.GamesTable;
import ch.quazz.caverna.R;
import ch.quazz.caverna.data.ScoreTable;
import ch.quazz.caverna.games.Game;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private CavernaDbHelper dbHelper;
    private MainAdapter mainAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (dbHelper == null) {
            dbHelper = new CavernaDbHelper(this);
        }

        ListView games = (ListView)findViewById(R.id.games_list);
        registerForContextMenu(games);
        games.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startGameActivity(id);
            }
        });

        setToolbar();

        setUpDrawer();
    }

    private void setUpDrawer() {
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment, drawerLayout, toolbar);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_main);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Game> games = GamesTable.getGames(dbHelper);
        Collections.reverse(games);
        mainAdapter = new MainAdapter(this);
        mainAdapter.setGames(games);

        ListView listView = (ListView)findViewById(R.id.games_list);
        listView.setAdapter(mainAdapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.main_add_score_pad:
                newGame();
                return true;

            default:
                return true;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case R.id.context_game_edit:
                startGameActivity(info.id);
                return true;

            case R.id.context_game_delete:
                ScoreTable.deleteScores(dbHelper, info.id);
                GamesTable.deleteGame(dbHelper, info.id);

                List<Game> games = GamesTable.getGames(dbHelper);
                Collections.reverse(games);
                mainAdapter.setGames(games);

                return true;

            default:
                return false;
        }
    }

    public void newGame() {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        long id = GamesTable.addGame(dbHelper, timestamp);
        startGameActivity(id);
    }

    private void startGameActivity(long gameId) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameActivity.ExtraGameId, gameId);
        startActivity(intent);
    }
}
