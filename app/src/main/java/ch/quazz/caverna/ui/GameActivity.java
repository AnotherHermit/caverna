package ch.quazz.caverna.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.quazz.caverna.R;
import ch.quazz.caverna.data.CavernaDbHelper;
import ch.quazz.caverna.data.GamesTable;
import ch.quazz.caverna.data.ScoreTable;
import ch.quazz.caverna.score.ScoreSheet;

public class GameActivity extends Activity implements Toolbar.OnMenuItemClickListener {
    final static String ExtraGameId = "ch.quazz.caverna.GameId";

    private CavernaDbHelper dbHelper;
    private GameAdapter scoringPadAdapter;
    private long gameId;
    Toolbar toolbar;

    private static final class Row {
        final ScoreSheet.Category category;
        final String name;
        final int colorId;
        TableRow view;

        Row(final ScoreSheet.Category category, final String name, final int colorId) {
            this.category = category;
            this.name = name;
            this.colorId = colorId;
            this.view = null;
        }
    }
    private static final List<Row> rows = new ArrayList<Row>() {
        {
            add(new Row(ScoreSheet.Category.Animals, "Animals", R.color.listBackgroundPrimary));
            add(new Row(ScoreSheet.Category.MissingFarmAnimal, "Missing animal", R.color.listBackgroundSecondary));
            add(new Row(ScoreSheet.Category.Grain, "Grain", R.color.listBackgroundPrimary));
            add(new Row(ScoreSheet.Category.Vegetable, "Vegetable", R.color.listBackgroundSecondary));
            add(new Row(ScoreSheet.Category.Ruby, "Ruby", R.color.listBackgroundPrimary));
            add(new Row(ScoreSheet.Category.Dwarf, "Dwarf", R.color.listBackgroundSecondary));
            add(new Row(ScoreSheet.Category.UnusedSpace, "Unused space", R.color.listBackgroundPrimary));
            add(new Row(ScoreSheet.Category.Tiles, "Tiles", R.color.listBackgroundSecondary));
            add(new Row(ScoreSheet.Category.Parlors, "Parlors", R.color.listBackgroundPrimary));
            add(new Row(ScoreSheet.Category.Storages, "Storages", R.color.listBackgroundSecondary));
            add(new Row(ScoreSheet.Category.Chambers, "Chambers", R.color.listBackgroundPrimary));
            add(new Row(ScoreSheet.Category.Assets, "Gold, Begging", R.color.listBackgroundSecondary));
            add(new Row(ScoreSheet.Category.Total, "Total", R.color.listBackgroundTitle));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        gameId = intent.getLongExtra(ExtraGameId, 0);

        if (dbHelper == null) {
            dbHelper = new CavernaDbHelper(this);
        }

        ListView players = (ListView)findViewById(R.id.game_player_list);
        registerForContextMenu(players);
        players.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GameActivity.this, PlayerScoreActivity.class);
                intent.putExtra(PlayerScoreActivity.ExtraScoreId, id);
                startActivity(intent);
            }
        });

        setToolbar();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_game);
        toolbar.inflateMenu(R.menu.game);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.game_add_player:
                if (ScoreTable.numberOfPlayers(dbHelper, gameId) < 7) {
                    Intent intent = new Intent(this, PlayersActivity.class);
                    intent.putExtra(GameActivity.ExtraGameId, gameId);
                    startActivity(intent);
                }
                return true;

            case R.id.game_delete_game:
                ScoreTable.deleteScores(dbHelper, gameId);
                GamesTable.deleteGame(dbHelper, gameId);
                finish();
                return true;

            default:
                return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<ScoreSheet> scoringPad = ScoreTable.getScoringPad(dbHelper, gameId);

        createScoringPad(scoringPad);

        scoringPadAdapter = new GameAdapter(this);
        scoringPadAdapter.setScoringPad(scoringPad);

        ListView listView = (ListView)findViewById(R.id.game_player_list);
        listView.setAdapter(scoringPadAdapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_player_score, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case R.id.context_player_score_edit:
                Intent intent = new Intent(this, PlayerScoreActivity.class);
                intent.putExtra(PlayerScoreActivity.ExtraScoreId, info.id);
                startActivity(intent);
                return true;

            case R.id.context_player_score_delete:

                ScoreTable.deleteScore(dbHelper, info.id);

                List<ScoreSheet> scoringPad = ScoreTable.getScoringPad(dbHelper, gameId);
                createScoringPad(scoringPad);
                scoringPadAdapter.setScoringPad(scoringPad);

                return true;

            default:
                return false;
        }
    }

    private void createScoringPad(List<ScoreSheet> scoringPad) {

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

        TableLayout table = (TableLayout)findViewById(R.id.game_scoring_pad);
        table.removeAllViews();

        // add rows
        TableRow players = new TableRow(this);
        players.setBackgroundResource(R.color.listBackgroundTitle);
        TextView title = new TextView(this);
        title.setText(R.string.player);
        players.addView(title);

        table.addView(players);

        for (Row row : rows) {
            row.view = new TableRow(this);
            row.view.setBackgroundResource(row.colorId);
            title = new TextView(this);
            title.setText(row.name);
            row.view.addView(title);
            table.addView(row.view);
        }

        // add points
        for (ScoreSheet sheet : scoringPad) {

            TextView player = new TextView(this);
            player.setText(String.valueOf(sheet.player));
            player.setGravity(Gravity.CENTER);
            player.setPadding(padding, 0, padding, 0);
            players.addView(player);

            for (Row row : rows) {
                TextView points = new TextView(this);
                points.setGravity(Gravity.RIGHT);
                points.setText(String.valueOf(sheet.score(row.category)));
                points.setPadding(padding, 0, padding, 0);
                if (row.category == ScoreSheet.Category.Total) {
                    points.setTypeface(null, Typeface.BOLD);
                }
                row.view.addView(points);
            }
        }
    }

}
