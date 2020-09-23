package de.grafelhaft.textviewvalidator;

import android.text.Editable;
import android.widget.TextView;

/**
 * Created by @author Markus Graf (Grafelhaft) on 23.09.2020
 */
public interface IValidator {
    IValidationError onTextChanged(TextView view, String s);
}
