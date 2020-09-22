package de.grafelhaft.textviewvalidator;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewParent;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class TextViewValidator implements TextWatcher {

    private static final String ERROR_MSG = "Invalid";

    private TextView view;
    private TextInputLayout textInputLayout;
    private String errorMessage;
    private boolean valid = true;

    private List<ValidationRule> rules = new ArrayList<>();
    private List<OnTextValidListener> listeners = new ArrayList<>();

    public TextViewValidator(TextView view) {
        this(view, ERROR_MSG);
    }

    public TextViewValidator(TextView view, int errorMessageRes) {
        this(view, view.getContext().getString(errorMessageRes));
    }

    public TextViewValidator(TextView view, String errorMessage) {
        this.view = view;
        this.view.addTextChangedListener(this);
        this.errorMessage = errorMessage;

        ViewParent parent = view.getParent().getParent();
        this.textInputLayout = parent instanceof TextInputLayout ? (TextInputLayout) parent : null;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        this.valid = this.rules.stream()
                .map(r -> r.onTextChanged(view, editable))
                .reduce(true, (a, b) -> a && b);

        if (this.textInputLayout != null) {
            this.textInputLayout.setError(this.valid ? null : errorMessage);
        } else {
            this.view.setError(this.valid ? null : errorMessage);
        }

        for (OnTextValidListener l : listeners) {
            l.onTextValid(editable.toString(), valid);
        }
    }

    public boolean isValid() {
        return valid;
    }

    public void addRule(ValidationRule rule) {
        this.rules.add(rule);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public TextViewValidator addOnTextChangedListener(OnTextValidListener l) {
        this.listeners.add(l);
        return this;
    }

    public TextViewValidator clearOnTextChangedListener() {
        this.listeners.clear();
        return this;
    }

    public interface OnTextValidListener {
        void onTextValid(String s, boolean valid);
    }
}
