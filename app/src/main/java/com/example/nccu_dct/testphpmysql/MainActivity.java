package com.example.nccu_dct.testphpmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("LOG", "onCreate");
        button_get_record = (Button)findViewById(R.id.get_record);
        button_get_record.setOnClickListener(getDBRecord);
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
    private Button button_get_record;

    private void findViews() {

    }

    private void setListeners() {

    }

    private Button.OnClickListener getDBRecord = new Button.OnClickListener() {
        public void onClick(View v) {
            Thread thread = new Thread(mutiThread);
            thread.start();

        }
    };
    private Runnable mutiThread = new Runnable(){
        public void run() {
            // 運行網路連線的程式
            Log.e("LOGE","Start.");
            TableLayout user_list = (TableLayout)findViewById(R.id.user_list);
            user_list.setStretchAllColumns(true);
            TableLayout.LayoutParams row_layout = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            TableRow.LayoutParams view_layout = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            try {
                String result = DBConnector.executeQuery("SELECT * FROM user");
                Log.e("LOGE","result");

                /*
                    SQL 結果有多筆資料時使用JSONArray
                    只有一筆資料時直接建立JSONObject物件
                    JSONObject jsonData = new JSONObject(result);
                */
                JSONArray jsonArray = new JSONArray(result);
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    /*TableRow tr = new TableRow(MainActivity.this);
                    tr.setLayoutParams(row_layout);
                    tr.setGravity(Gravity.CENTER_HORIZONTAL);

                    TextView user_acc = new TextView(MainActivity.this);
                    user_acc.setText(jsonData.getString("account"));
                    user_acc.setLayoutParams(view_layout);

                    TextView user_pwd = new TextView(MainActivity.this);
                    user_pwd.setText(jsonData.getString("pwd"));
                    user_pwd.setLayoutParams(view_layout);

                    tr.addView(user_acc);
                    tr.addView(user_pwd);
                    user_list.addView(tr);*/
                    Log.e("account", jsonData.getString("account"));
                    Log.e("account", jsonData.getString("pwd"));

                }
            } catch(Exception e) {
                Log.e("log_tag", e.toString());
            }
        }
    };
}

