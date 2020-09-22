# TextViewValidator
Simple way to add different validators to your Android TextView

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
TextViewValidator validator = new TextViewValidator(textInputEdit);
validator.setErrorMessage("Input must be less than 8 chars!");
validator.addRule((view, editable) -> editable.length() > 0);
validator.addRule((view, editable) -> editable.length() < 8);

TextView textView = findViewById(R.id.text_edit);
TextViewValidator validator1 = new TextViewValidator(textView);
validator1.setErrorMessage("Input must not be 'Test'!");
validator1.addRule(((view, editable) -> !"Test".equals(editable.toString())));
```

![alt text](https://github.com/Grafelhaft/TextViewValidator/blob/master/device-2020-09-22-223902.png?raw=true "Screenshot")