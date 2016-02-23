package ch.quazz.caverna.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
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

    class CountViewHolder extends RecyclerView.ViewHolder implements HorizontalPicker.OnItemSelected {
        TextView title;
        ImageView icon;
        //        NumberPicker numberPicker;
        HorizontalPicker horizontalPicker;
        int position;
        TokenItem current;
        final PlayerScore playerScore;

        ArrayList<String> valueRange;

        public CountViewHolder(View itemView, final PlayerScore playerScore) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.count_text);
            icon = (ImageView) itemView.findViewById(R.id.count_icon);
            horizontalPicker = (HorizontalPicker) itemView.findViewById(R.id.count_horizontalPicker);
            this.playerScore = playerScore;
        }

        public void setData(TokenItem current, int position) {
            this.title.setText(current.getTitleID());
            this.icon.setImageResource(current.getImageID());
            this.position = position;
            this.current = current;

            if(valueRange == null) {
                this.valueRange = new ArrayList<>();
                Integer min = horizontalPicker.getResources().getInteger(current.getMinValue());
                Integer max = horizontalPicker.getResources().getInteger(current.getMaxValue());

                for (int i = min; i <= max; i++) {
                    valueRange.add(Integer.toString(i));
                }
                horizontalPicker.setValues(valueRange.toArray(new CharSequence[valueRange.size()]));

            }
            Integer currentValue = playerScore.getCount(current.getToken());
            horizontalPicker.setSelectedItem(valueRange.indexOf(currentValue.toString()));
        }

        public void setListeners() {
            this.horizontalPicker.setOnItemSelectedListener(CountViewHolder.this);
        }

        @Override
        public void onItemSelected(int index) {
            playerScore.setCount(current.getToken(), Integer.parseInt(valueRange.get(index)));
        }
    }
}
