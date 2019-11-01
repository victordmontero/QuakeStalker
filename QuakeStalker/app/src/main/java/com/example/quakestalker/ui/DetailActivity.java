package com.example.quakestalker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quakestalker.R;
import com.example.quakestalker.models.Properties;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Properties properties = getIntent().getParcelableExtra("property");

        ((TextView)findViewById(R.id.magnitude_detail))
                .setText(properties.getMag().toString());

        ((TextView)findViewById(R.id.place_detail)).setText(properties.getPlace());

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd/MM/yy");
        String date = dateFormat.format(new Date(properties.getTime()));

        ((TextView)findViewById(R.id.date_detail)).setText(date);

    }
}
