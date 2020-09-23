package de.grafelhaft.textviewapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.grafelhaft.textviewvalidator.TextViewValidator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textInputEdit = findViewById(R.id.text_input_edit);
        TextViewValidator validator = new TextViewValidator(textInputEdit);
        validator.setErrorMessage("Input must be less than 8 chars!");
        validator.addRule((view, editable) -> editable.length() > 0);
        validator.addRule((view, editable) -> editable.length() < 8);

        TextView textView = findViewById(R.id.text_edit);
        TextViewValidator validator1 = new TextViewValidator(textView);
        validator1.setErrorMessage("Input must not be 'Test'!");
        validator1.addRule(((view, editable) -> !"Test".equals(editable.toString())));
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