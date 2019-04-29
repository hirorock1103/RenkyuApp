package com.design_phantom.renkyuapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private ImageView _imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _character = BitmapFactory.decodeResource(getResources(), R.drawable.character);
        _imageView = findViewById(R.id.character);
        _imageView.setImageBitmap(_character);
        _btSubmit = findViewById(R.id.bt_get_holidays);

        _btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //連休情報を取得します
                AsyncTaskClass task = new AsyncTaskClass();
                task.execute("start");
                _btSubmit.setEnabled(false);
                Log.i("INFO", "処理開始");

            }
        });

    }

    class AsyncTaskClass extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground (String... params) {

            // バックグラウンドで行う処理

            Log.i("INFO", params[0]);

            Thread thread = new Thread();

            try {
                for(int i = 0; i < 5; i++){
                    thread.sleep(1000);
                    this.publishProgress(String.valueOf(i+1) + "/ 5");

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return "END";
        }

        @Override
        protected void onProgressUpdate(String... values) {

            Log.i("INFO",values[0]);

        }

        @Override
        protected void onPostExecute(String str) {
            // UIスレッドに反映する処理
            Log.i("INFO",str);
            _btSubmit.setEnabled(true);

        }
    }

}
