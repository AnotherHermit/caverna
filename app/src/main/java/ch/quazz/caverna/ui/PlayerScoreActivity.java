package ch.quazz.caverna.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ch.quazz.caverna.data.CavernaDbHelper;
import ch.quazz.caverna.data.ScoreTable;
import ch.quazz.caverna.score.Item;
import ch.quazz.caverna.score.PlayerScore;
import ch.quazz.caverna.R;
import ch.quazz.caverna.widget.StoppableViewPager;

public class PlayerScoreActivity extends FragmentActivity {

    public final static String ExtraScoreId = "ch.quazz.caverna.ScoreId";

    private CavernaDbHelper dbHelper;
    private PlayerScore playerScore;

    private long scoreId;

    PlayerScorePagerAdapter pagerAdapter;
    StoppableViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_score);

        Intent intent = getIntent();
        scoreId = intent.getLongExtra(PlayerScoreActivity.ExtraScoreId, 0);

        if (dbHelper == null) {
            dbHelper = new CavernaDbHelper(this);
        }

        viewPager = (StoppableViewPager) findViewById(R.id.pager);
        pagerAdapter = new PlayerScorePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragments();
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
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

        pagerAdapter.setupFragments();

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
        private ArrayList<Fragment> fragments = new ArrayList<>();

        public PlayerScorePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragments() {
            fragments.add(new PlayerScoreFragment());
            ((PlayerScoreFragment)fragments.get(0)).setTitle(getString(R.string.wealth_tab));
            fragments.add(new PlayerScoreFragment());
            ((PlayerScoreFragment)fragments.get(1)).setTitle(getString(R.string.family_tab));
            fragments.add(new PlayerScoreFragment());
            ((PlayerScoreFragment)fragments.get(2)).setTitle(getString(R.string.cave_tab));
            fragments.add(new PlayerScoreFragment());
            ((PlayerScoreFragment)fragments.get(3)).setTitle(getString(R.string.bonus_tab));
        }

        public void setupFragments() {
            ((PlayerScoreFragment)fragments.get(0)).setupFragment(playerScore, Item.getWealthItems());
            ((PlayerScoreFragment)fragments.get(1)).setupFragment(playerScore, Item.getFamilyItems());
            ((PlayerScoreFragment)fragments.get(2)).setupFragment(playerScore, Item.getCaveItems());
            ((PlayerScoreFragment)fragments.get(3)).setupFragment(playerScore, Item.getBonusItems());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ((PlayerScoreFragment)fragments.get(position)).getTitle();
        }
    }
}
