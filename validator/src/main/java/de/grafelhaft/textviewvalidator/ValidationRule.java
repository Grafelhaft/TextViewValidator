package de.grafelhaft.textviewvalidator;

import android.text.Editable;
import android.widget.TextView;

public interface ValidationRule {
    boolean onTextChanged(TextView view, Editable editable);
}
