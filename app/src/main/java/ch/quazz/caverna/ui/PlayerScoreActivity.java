package ch.quazz.caverna.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;

import ch.quazz.caverna.data.CavernaDbHelper;
import ch.quazz.caverna.data.ScoreTable;
import ch.quazz.caverna.score.Item;
import ch.quazz.caverna.score.PlayerScore;
import ch.quazz.caverna.R;
import ch.quazz.caverna.widget.StoppableViewPager;

public class PlayerScoreActivity extends FragmentActivity {

    public final static String ExtraScoreId = "ch.quazz.caverna.ScoreId";

    private static final String Wealth = "wealth";
    private static final String Family = "family";
    private static final String Cave = "cave";
    private static final String Bonus = "bonus";

    private PlayerScoreFragment wealth;
    private PlayerScoreFragment family;
    private PlayerScoreFragment cave;
    private PlayerScoreFragment bonus;

    private CavernaDbHelper dbHelper;
    private PlayerScore playerScore;

    private long scoreId;

    PlayerScorePagerAdapter pagerAdapter;
    StoppableViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_score);

        Intent intent = getIntent();
        scoreId = intent.getLongExtra(PlayerScoreActivity.ExtraScoreId, 0);

        if (dbHelper == null) {
            dbHelper = new CavernaDbHelper(this);
        }

        pagerAdapter = new PlayerScorePagerAdapter(getSupportFragmentManager());
        viewPager = (StoppableViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

//        setupTabFragments();
        //setupActionBarTabs();
    }

    @Override
    protected void onPause() {
        super.onPause();

        ScoreTable.setScore(dbHelper, playerScore, scoreId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        playerScore = ScoreTable.getScore(dbHelper, scoreId);
        playerScore.addOnScoreChangeListener(new PlayerScore.OnScoreChangeListener() {
            @Override
            public void onScoreChanged() {
                updateScore();
            }
        });

/*        wealth.setPlayerScore(playerScore);
        wealth.setItems(Item.getWealthItems());
        family.setPlayerScore(playerScore);
        family.setItems(Item.getFamilyItems());
        cave.setPlayerScore(playerScore);
        cave.setItems(Item.getCaveItems());
        bonus.setPlayerScore(playerScore);
        bonus.setItems(Item.getBonusItems());*/

        updateScore();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getActionBar() != null) {
            outState.putInt("navigation_index", getActionBar().getSelectedNavigationIndex());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (getActionBar() != null) {
            getActionBar().setSelectedNavigationItem(savedInstanceState.getInt("navigation_index"));
        }
    }

    private void updateScore() {
        String score = String.valueOf(playerScore.score());
        setTitle(getString(R.string.score) + ": " + score);
    }
/*

    private void setupActionBarTabs() {
        ActionBar actionBar = getActionSupportBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            actionBar.addTab(actionBar.newTab()
                    .setText(R.string.wealth_tab)
                    .setTabListener(new TabListener(R.id.player_score_fragment, wealth, Wealth)));

            actionBar.addTab(actionBar.newTab()
                    .setText(R.string.family_tab)
                    .setTabListener(new TabListener(R.id.player_score_fragment, family, Family)));

            actionBar.addTab(actionBar.newTab()
                    .setText(R.string.cave_tab)
                    .setTabListener(new TabListener(R.id.player_score_fragment, cave, Cave)));

            actionBar.addTab(actionBar.newTab()
                    .setText(R.string.bonus_tab)
                    .setTabListener(new TabListener(R.id.player_score_fragment, bonus, Bonus)));
        }
    }
*/

    private void setupTabFragments() {


        if (getSupportFragmentManager() != null) {
            wealth = (PlayerScoreFragment)getSupportFragmentManager().findFragmentByTag(Wealth);
            family = (PlayerScoreFragment)getSupportFragmentManager().findFragmentByTag(Family);
            cave = (PlayerScoreFragment)getSupportFragmentManager().findFragmentByTag(Cave);
            bonus = (PlayerScoreFragment)getSupportFragmentManager().findFragmentByTag(Bonus);
        }

        if (wealth == null) {
            wealth = new PlayerScoreFragment();
        }
        if (family == null) {
            family = new PlayerScoreFragment();
        }
        if (cave == null) {
            cave = new PlayerScoreFragment();
        }
        if (bonus == null) {
            bonus = new PlayerScoreFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.player_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.player_score_ok:
                finish();
                return true;

            case R.id.player_score_discard:
                ScoreTable.deleteScore(dbHelper, scoreId);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class PlayerScorePagerAdapter extends FragmentPagerAdapter {

        private static final int NUM_TABS = 4;
        private static final int WEALTH_FRAGMENT = 0;
        private static final int FAMILY_FRAGMENT = 1;
        private static final int CAVE_FRAGMENT = 2;
        private static final int BONUS_FRAGMENT = 3;

        public PlayerScorePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            PlayerScoreFragment playerScoreFragment = new PlayerScoreFragment();
            switch (position) {
                case WEALTH_FRAGMENT:
                    playerScoreFragment.setPlayerScore(playerScore);
                    playerScoreFragment.setItems(Item.getWealthItems());
                    break;
                case FAMILY_FRAGMENT:
                    playerScoreFragment.setPlayerScore(playerScore);
                    playerScoreFragment.setItems(Item.getFamilyItems());
                    break;
                case CAVE_FRAGMENT:
                    playerScoreFragment.setPlayerScore(playerScore);
                    playerScoreFragment.setItems(Item.getCaveItems());
                    break;
                case BONUS_FRAGMENT:
                    playerScoreFragment.setPlayerScore(playerScore);
                    playerScoreFragment.setItems(Item.getBonusItems());
                    break;
            }
            return playerScoreFragment;
        }

        @Override
        public int getCount() {
            return NUM_TABS;
        }
    }
}
