package de.grafelhaft.textviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.grafelhaft.textviewvalidator.TextViewValidator;
import de.grafelhaft.textviewvalidator.ValidationError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textInputEdit = findViewById(R.id.text_input_edit);
        TextViewValidator validator = new TextViewValidator(textInputEdit)
                .addValidator(((view, editable) -> editable.length() > 0 ? null : new ValidationError("Must be greater than 0")))
                .addValidator(((view, editable) -> editable.length() < 8 ? null : new ValidationError("Must be smaller than 8")))
                .addValidListener((v, s, isValid) -> Log.d("Validation changed", Boolean.toString(isValid)));

        TextView textView = findViewById(R.id.text_edit);
        TextViewValidator validator2 = new TextViewValidator(textView)
                .addValidator(((view, editable) -> !"Error".equals(editable) ? null : new ValidationError("Must not be 'Error'")))
                .addValidListener((v, s, isValid) -> Log.d("Validation changed", Boolean.toString(isValid)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}