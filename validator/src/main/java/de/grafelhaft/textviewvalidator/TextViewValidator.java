package de.grafelhaft.textviewvalidator;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by @author Markus Graf (Grafelhaft) on 23.09.2020
 */
public class TextViewValidator implements TextWatcher, View.OnFocusChangeListener {

    private TextView textView;
    private TextInputLayout textInputLayout;

    private boolean isValid;
    private List<IValidator> validators = new ArrayList<>();
    private List<OnTextViewValidListener> onTextViewValidListeners = new ArrayList<>();
    private List<OnTextViewChangedListener> onTextViewChangedListeners = new ArrayList<>();

    public TextViewValidator(TextView textView) {
        this.textView = textView;
        this.textView.addTextChangedListener(this);
        this.textView.setOnFocusChangeListener(this);

        ViewParent parent = textView.getParent().getParent();
        this.textInputLayout = parent instanceof TextInputLayout ? (TextInputLayout) parent : null;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public TextViewValidator addValidator(IValidator validator) {
        this.validators.add(validator);
        return this;
    }

    public TextViewValidator removeValidator(IValidator validator) {
        this.validators.remove(validator);
        return this;
    }

    public TextViewValidator clearValidators() {
        this.validators.clear();
        return this;
    }

    public TextViewValidator addValidListener(OnTextViewValidListener listener) {
        this.onTextViewValidListeners.add(listener);
        return this;
    }

    public TextViewValidator removeValidListener(OnTextViewValidListener listener) {
        this.onTextViewValidListeners.remove(listener);
        return this;
    }

    public TextViewValidator clearValidListener() {
        this.onTextViewValidListeners.clear();
        return this;
    }

    public TextViewValidator addChangeListener(OnTextViewChangedListener listener) {
        this.onTextViewChangedListeners.add(listener);
        return this;
    }

    public TextViewValidator removeChangeListener(OnTextViewChangedListener listener) {
        this.onTextViewChangedListeners.remove(listener);
        return this;
    }

    public TextViewValidator clearChangeListener() {
        this.onTextViewChangedListeners.clear();
        return this;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String errorMsg = this.validators.stream()
                .map(v -> v.onTextChanged(textView, editable.toString()))
                .filter(Objects::nonNull)
                .map(IValidationError::getErrorMsg)
                .findFirst().orElse(null);

        boolean isValid = errorMsg == null;

        if (this.textInputLayout != null) {
            this.textInputLayout.setError(isValid ? null : errorMsg);
        } else {
            this.textView.setError(isValid ? null : errorMsg);
        }

        if (this.isValid != isValid) {
            for (OnTextViewValidListener l : onTextViewValidListeners) {
                l.onValid(this.textView, editable.toString(), isValid);
            }
        }

        this.isValid = isValid;

        for (OnTextViewChangedListener l : onTextViewChangedListeners) {
            l.onTextChanged(editable.toString());
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.afterTextChanged(((TextView) v).getEditableText());
    }
}
