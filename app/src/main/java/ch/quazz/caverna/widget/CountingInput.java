package ch.quazz.caverna.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ch.quazz.caverna.R;

public class CountingInput extends LinearLayout {

    private int count = 0;
    private int min = 0;
    private int max = 0;
    private String label = "";

    private final TextView countText;
//    private final SeekBar countSlider;
//    private final NumberPicker numberPicker;

    private OnCountChangeListener listener;

    public CountingInput(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.token_item, this, true);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int padding = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics()));

        setOrientation(LinearLayout.HORIZONTAL);
        setPadding(0, padding, 0, padding);
        setLayoutParams(layoutParams);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CountingInput);

        label = attributes.getString(R.styleable.CountingInput_label);
        min = attributes.getInteger(R.styleable.CountingInput_min, 0);
        max = attributes.getInteger(R.styleable.CountingInput_max, 100);

        ImageView icon = (ImageView)findViewById(R.id.image_icon);
        icon.setImageDrawable(attributes.getDrawable(R.styleable.CountingInput_myIcon));

        attributes.recycle();

        count = min;

        countText = (TextView)findViewById(R.id.title_text);
//        numberPicker = (NumberPicker) findViewById(R.id.count_numberPicker);
//        countSlider = (SeekBar)findViewById(R.id.count_slider);

//        setupNumberPicker();
//        setupSeekbar();
    }

/*    private void setupNumberPicker() {
        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        numberPicker.setMinValueID(min);
        //Specify the maximum value/number of NumberPicker
        numberPicker.setMaxValueID(max);
        numberPicker.setValue(count);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        // NOTE: Setting this to false leads to strange behavior when trying to scroll away from min
        // or max values, not sure why
        numberPicker.setWrapSelectorWheel(true);

        //Set a value change listener for NumberPicker
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                count = newVal;
                updateText();
                if (listener != null) {
                    listener.onCountChanged(CountingInput.this, count, true);
                }
            }
        });
    }*/

    public void setCount(int count) {
        this.count = count;
//        numberPicker.setValue(this.count - min);
//        countSlider.setProgress(this.count - min);
        updateText();
    }

    public int getCount() {
        return count;
    }

    public interface OnCountChangeListener {
        void onCountChanged(CountingInput input, int count, boolean fromUser);
    }

    public void setOnCountChangeListener(OnCountChangeListener listener) {
        this.listener = listener;
    }
/*
    private void setupSeekbar() {
        countSlider.setMax(max - min);
        countSlider.setProgress(count - min);
        updateText();

        countSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                count = min + progress;
                updateText();
                if (listener != null) {
                    listener.onCountChanged(CountingInput.this, count, fromUser);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
*/

    private void updateText() {
        countText.setText(label + ": " + Integer.toString(count));
    }
}
