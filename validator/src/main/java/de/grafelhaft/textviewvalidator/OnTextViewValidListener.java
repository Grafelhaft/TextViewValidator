package de.grafelhaft.textviewvalidator;

import android.widget.TextView;

/**
 * Created by @author Markus Graf (Grafelhaft) on 23.09.2020
 */
public interface OnTextViewValidListener {
    void onValid(TextView v, String s, boolean isValid);
}
