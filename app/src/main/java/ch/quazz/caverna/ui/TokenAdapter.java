package ch.quazz.caverna.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;

import ch.quazz.caverna.R;
import ch.quazz.caverna.score.PlayerScore;
import ch.quazz.caverna.score.TokenItem;

class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.CountViewHolder> {

    private List<TokenItem> mData;
    private LayoutInflater mInflater;
    final private PlayerScore playerScore;

    public TokenAdapter(Context context, List<TokenItem> mData, final PlayerScore playerScore) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        this.playerScore = playerScore;
    }

    @Override
    public CountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.counting_input, parent, false);
        CountViewHolder holder = new CountViewHolder(view, playerScore);
        return holder;
    }

    @Override
    public void onBindViewHolder(CountViewHolder holder, int position) {
        TokenItem current = mData.get(position);
        holder.setData(current, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CountViewHolder extends RecyclerView.ViewHolder implements NumberPicker.OnValueChangeListener {
        TextView title;
        ImageView icon;
        NumberPicker numberPicker;
        int position;
        TokenItem current;
        final PlayerScore playerScore;

        public CountViewHolder(View itemView, final PlayerScore playerScore) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.count_text);
            icon = (ImageView) itemView.findViewById(R.id.count_icon);
            numberPicker = (NumberPicker) itemView.findViewById(R.id.count_numberPicker);
            this.playerScore = playerScore;
        }

        public void setData(TokenItem current, int position) {
            this.title.setText(current.getTitleID());
            this.icon.setImageResource(current.getImageID());
            this.position = position;
            this.current = current;

            // Set up number picker min and max values
            this.numberPicker.setMinValue(current.getMinValue());
            this.numberPicker.setMaxValue(current.getMaxValue());
            this.numberPicker.setValue(playerScore.getCount(current.getToken()));

            //Gets whether the selector wheel wraps when reaching the min/max value.
            // NOTE: Setting this to false leads to strange behavior when trying to scroll away from min
            // or max values, not sure why
            this.numberPicker.setWrapSelectorWheel(true);
        }

        public void setListeners() {
            this.numberPicker.setOnValueChangedListener(CountViewHolder.this);
        }

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//            picker.setValue(newVal);
            playerScore.setCount(current.getToken(), newVal);
        }
    }


    /*
        final static class Item {
        final int id;
        final Token item;

        Item(int id, Token item) {
            this.id = id;
            this.item = item;
        }
    }

    private final Item items[];

    TokenAdapter(final Item items[]) {
        this.items = items;
    }

    void setup(final PlayerScore playerScore, Activity activity) {

        CountingInput.OnCountChangeListener listener = new CountingInput.OnCountChangeListener() {
            @Override
            public void onCountChanged(CountingInput input, int count, boolean fromUser) {
                if (fromUser) {
                    playerScore.setCount((Token)input.getTag(), count);
                }
            }
        };

        for (final Item item : items) {
            CountingInput countingInput = (CountingInput)activity.findViewById(item.id);
            countingInput.setTag(item.item);
            countingInput.setCount(playerScore.getCount(item.item));
            countingInput.setOnCountChangeListener(listener);
        }
    }*/
}
