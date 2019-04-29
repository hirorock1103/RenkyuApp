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
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * this app
     * Async task
     * Image pretty character
     * 20190430 api連携部分参照　https://qiita.com/hkusu/items/8572d768243fe7e7ed88
     */
    private static final String API_URL = "https://holidays-jp.github.io/api/v1/2019/datetime.json";
    private Bitmap _character;
    private Button _btSubmit;
    private ImageView _imageView;
    private TextView _tx_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _character = BitmapFactory.decodeResource(getResources(), R.drawable.character);
        _imageView = findViewById(R.id.character);
        _imageView.setImageBitmap(_character);
        _btSubmit = findViewById(R.id.bt_get_holidays);
        _tx_progress = findViewById(R.id.tx_progress);

        _btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //連休情報を取得します
                AsyncTaskClass task = new AsyncTaskClass();
                task.execute(API_URL);
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

            StringBuilder sb = new StringBuilder();

            try {

                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line = "";
                while ((line = reader.readLine()) != null){
                    sb.append(line);
                }
                is.close();

                for(int i = 0; i < 5; i++){
                    thread.sleep(1000);
                    this.publishProgress(String.valueOf(i));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return sb.toString();
        }

        @Override
        protected void onProgressUpdate(String... values) {

            Log.i("INFO",values[0]);
            //_tx_progress.setText(values[0]);
            _tx_progress.setText(values[0]);


        }

        @Override
        protected void onPostExecute(String str) {
            // UIスレッドに反映する処理
            Log.i("INFO",str);
            _btSubmit.setEnabled(true);

        }
    }

}
