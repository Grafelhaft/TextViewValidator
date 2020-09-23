# TextViewValidator
Simple way to add different validators to your Android TextView or EditText.
Also supports Android's material TextInputLayout and TextInputEditText.

```xml
<com.google.android.material.textfield.TextInputLayout
            android:id="@+id/text_input_layout"
            style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="TextInputLayout"
            app:errorEnabled="true"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toEndOf="@+id/icon"
            app:layout_constraintTop_toTopOf="parent">

            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/text_input_edit"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />

</com.google.android.material.textfield.TextInputLayout>

<EditText
    android:id="@+id/text_edit"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

```java
TextView textInputEdit = findViewById(R.id.text_input_edit);
TextViewValidator validator = new TextViewValidator(textInputEdit)
        .addValidator(((view, editable) -> editable.length() > 0 ? null : new ValidationError("Must be greater than 0")))
        .addValidator(((view, editable) -> editable.length() < 8 ? null : new ValidationError("Must be smaller than 8")))
        .addListener((v, isValid) -> Log.d("Validation changed", Boolean.toString(isValid)));

TextView textView = findViewById(R.id.text_edit);
TextViewValidator validator2 = new TextViewValidator(textView)
        .addValidator(((view, editable) -> !"Error".equals(editable) ? null : new ValidationError("Must not be 'Error'")))
        .addListener((v, isValid) -> Log.d("Validation changed", Boolean.toString(isValid)));
```

![alt text](https://github.com/Grafelhaft/TextViewValidator/blob/master/screenshot.png?raw=true "Screenshot")