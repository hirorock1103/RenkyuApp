package com.design_phantom.renkyuapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * this app
     * Async task
     * Image pretty character
     */
    private Bitmap _character;
    private Button _btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _character = BitmapFactory.decodeResource(getResources(), R.drawable.character);
        ImageView imageView = findViewById(R.id.character);
        imageView.setImageBitmap(_character);

        ConstraintLayout layout = findViewById(R.id.layout);

        Button bt = new Button(this);
        bt.setText("連休取得！");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //連休情報を取得します




            }
        });

        layout.addView(bt);




    }
}
