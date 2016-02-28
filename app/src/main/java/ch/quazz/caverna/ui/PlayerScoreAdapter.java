package ch.quazz.caverna.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.List;

import ch.quazz.caverna.R;
import ch.quazz.caverna.score.Item;
import ch.quazz.caverna.score.PlayerScore;
import ch.quazz.caverna.score.TileItem;
import ch.quazz.caverna.score.TokenItem;
import ch.quazz.caverna.widget.StoppableWrapper;

class PlayerScoreAdapter extends RecyclerView.Adapter<PlayerScoreAdapter.CountViewHolder> {

    private static final String TAG = PlayerScoreAdapter.class.getSimpleName();

    private static final int TOKEN_ROW = 0;
    private static final int TILE_ROW = 1;
    private static final int SEPARATOR_ROW = 2;

    private LayoutInflater mInflater;
    private List<Item> mData;
    private PlayerScore playerScore;

    public PlayerScoreAdapter(Context context, List<Item> mData, final PlayerScore playerScore) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.playerScore = playerScore;
    }

    @Override
    public CountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");

        switch (viewType) {
            case TOKEN_ROW:
                View tokenView = mInflater.inflate(R.layout.token_item, parent, false);
                return new CountTokenViewHolder(tokenView, playerScore);

            case TILE_ROW:
                View tileView = mInflater.inflate(R.layout.tile_item, parent, false);
                return new CountTileViewHolder(tileView, playerScore);

            case SEPARATOR_ROW:
            default:
                View separatorView = mInflater.inflate(R.layout.separator, parent, false);
                return new CountViewHolder(separatorView, playerScore);
        }
    }

    @Override
    public void onBindViewHolder(CountViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder " + position);
        Item current = mData.get(position);

        switch (holder.getItemViewType()) {
            case TOKEN_ROW:
                CountTokenViewHolder tokenHolder = (CountTokenViewHolder) holder;
                tokenHolder.setData(current, position);
                break;

            case TILE_ROW:
                CountTileViewHolder tileHolder = (CountTileViewHolder) holder;
                tileHolder.setData(current, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof TokenItem) {
            return TOKEN_ROW;
        } else if (mData.get(position) instanceof TileItem){
            return TILE_ROW;
        } else {
            return SEPARATOR_ROW;
        }
    }

    class CountViewHolder extends RecyclerView.ViewHolder  {
        TextView title;
        ImageView icon;

        int position;
        final PlayerScore playerScore;

        public CountViewHolder(View itemView, final PlayerScore playerScore) {
            super(itemView);
            this.playerScore = playerScore;
        }

        public void setData(Item current, int position) {
            this.title = (TextView) itemView.findViewById(R.id.title_text);
            this.icon = (ImageView) itemView.findViewById(R.id.image_icon);
            this.title.setText(current.getTitleID());
            this.icon.setImageResource(current.getImageID());
            this.position = position;
        }
    }

    class CountTokenViewHolder extends CountViewHolder implements HorizontalPicker.OnItemSelected{
        HorizontalPicker horizontalPicker;
        ArrayList<String> valueRange;
        TokenItem current;

        public CountTokenViewHolder(View itemView, final PlayerScore playerScore) {
            super(itemView, playerScore);
        }

        @Override
        public void setData(Item current, int position) {
            super.setData(current, position);
            this.current = (TokenItem) current;
            horizontalPicker = (HorizontalPicker) itemView.findViewById(R.id.token_horizontalPicker);

            valueRange = new ArrayList<>();
            Integer min = horizontalPicker.getResources().getInteger(this.current.getMinValueID());
            Integer max = horizontalPicker.getResources().getInteger(this.current.getMaxValueID());

            for (int i = min; i <= max; i++) {
                valueRange.add(Integer.toString(i));
            }

            Integer currentValue = playerScore.getCount(this.current.getToken());

            Log.i(TAG, "onBindViewHolder, setData value: " + currentValue);

            horizontalPicker.setOnItemSelectedListener(null);
            horizontalPicker.setValues(valueRange.toArray(new CharSequence[valueRange.size()]));
            horizontalPicker.setSelectedItem(valueRange.indexOf(currentValue.toString()));
            horizontalPicker.setSideItems(1);
            horizontalPicker.setOnItemSelectedListener(CountTokenViewHolder.this);
            ((StoppableWrapper)horizontalPicker.getParent()).setScrollable(true);

        }

        @Override
        public void onItemSelected(int index) {
            Log.i(TAG, "onItemSelected: " + position + "   Size: " + mData.size());
            playerScore.setCount(current.getToken(), Integer.parseInt(valueRange.get(index)));
            ((StoppableWrapper)horizontalPicker.getParent()).reenableParentScroll();
        }
    }

    class CountTileViewHolder extends CountViewHolder implements CompoundButton.OnCheckedChangeListener {
        TileItem current;
        CheckBox checkBox;

        public CountTileViewHolder(View itemView, final PlayerScore playerScore) {
            super(itemView, playerScore);
        }

        @Override
        public void setData(Item current, int position) {
            super.setData(current, position);
            this.current = (TileItem) current;
            checkBox = (CheckBox) itemView.findViewById(R.id.tile_check);

            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(playerScore.has(this.current.getTile()));
            checkBox.setOnCheckedChangeListener(CountTileViewHolder.this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(TAG, "onCheckedChanged: " + position + "   Size: " + mData.size());
            if (isChecked) {
                playerScore.set(current.getTile());
            } else {
                playerScore.clear(current.getTile());
            }
        }
    }
}
