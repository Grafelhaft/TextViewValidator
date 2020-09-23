package de.grafelhaft.textviewvalidator;

/**
 * Created by @author Markus Graf (Grafelhaft) on 23.09.2020
 */
public class ValidationError implements IValidationError {

    private String errorMsg;

    public ValidationError(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
