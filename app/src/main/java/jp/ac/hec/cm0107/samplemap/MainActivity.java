package jp.ac.hec.cm0107.samplemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private int pos = 0;
    Intent intent;
    Spinner spn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent  = new Intent(MainActivity.this, MapsActivity.class);
        spn = findViewById(R.id.spnMap);
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new mapStart());



        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(
                this,
                R.array.Array,
                android.R.layout.simple_spinner_dropdown_item
        );
        spn.setAdapter(adapter);


    }

    private class mapStart implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            pos = spn.getSelectedItemPosition();
            intent.putExtra("pos",pos);
            startActivity(intent);
        }
    }
}
