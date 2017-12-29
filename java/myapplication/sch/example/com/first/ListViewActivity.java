package myapplication.sch.example.com.first;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    public static final int SHOW_RESPONSE = 0;
    private static final String PATH = "http://47.95.204.255:8081/articles/";
    private ListView listView;
    RefreshableView refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#3A5FCD"));
        //mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        refreshableView  = (RefreshableView) findViewById(R.id.refreshable_view);
        listView = (ListView) findViewById(R.id.list);
        getData();

        refreshableView .setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getData();
                refreshableView .finishRefreshing();
            }
        }, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.Menu1:
                new AlertDialog.Builder(this).setTitle("不允许设置，哈哈哈").setIcon(0).show();
                break;
            case R.id.Menu3:
                Toast toast=new Toast(this);
                toast.makeText(this,"不接受反馈",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Menu4:
                Toast w=new Toast(this);
                w.makeText(this,"refreshing",Toast.LENGTH_SHORT).show();
                getData();
                Toast ee=new Toast(this);
                ee.makeText(this,"refresh success",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Menu2:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                //ActivityManager am = (ActivityManager)getSystemService (Context.ACTIVITY_SERVICE);
                //am.restartPackage(getPackageName());
                break;
        }
        return true;
    }

    public void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn= null;
                try {
                    conn = (HttpURLConnection) new URL(PATH).openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        InputStream is = conn.getInputStream();
                        String content = GetJson.readStream(is);
                        Message msg = new Message();
                        msg.obj = content;
                        msg.what = SHOW_RESPONSE;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if(conn!= null){
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
    public void setData(String data){
        List<Article> articles = new ArrayList<Article>();
        //System.out.println(data);
        //String string="{dd:[{ss:1},{ss:2},{ss:3}]}";
        try {
            //JSONObject json = JSONObject(string);
            JSONArray results = new JSONArray(data);
            JSONObject resul = results.getJSONObject(0);
            JSONArray dat = resul.getJSONArray("data");
            for (int i = 0; i < dat.length(); i++) {
                JSONObject result = dat.getJSONObject(i);
                Article art = new Article();
                art.setTitle(result.getString("title"));
                art.setContent(result.getString("content"));
                art.setPub_date(result.getString("pub_date"));
                //System.err.println(result.getString("title"));
                articles.add(art);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArticleAdapter adapter = new ArticleAdapter(this,articles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article a = new Article();
                a.setTitle(((TextView)view.findViewById(R.id.title)).getText().toString());
                a.setContent(((TextView)view.findViewById(R.id.mess)).getText().toString());
                a.setPub_date(((TextView)view.findViewById(R.id.time)).getText().toString());
                changeActive(a,position);
            }
        });
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg. what) {
                case SHOW_RESPONSE:
                    setData(msg.obj.toString());
                    break;
            }
        }
    };
    public static class GetJson {
        public static String readStream(InputStream is) throws Exception{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer))!=-1){
                baos.write(buffer, 0, len);
            }
            is.close();
            String content = new String(baos.toByteArray(), "gbk");
            return content;
        }
    }
    public void changeActive(Article a,int position){
        Intent intent = new Intent(ListViewActivity.this,
                ArticleActivity.class);
        intent.putExtra("title",a.getTitle());
        //System.out.println(a.getTitle());
        intent.putExtra("content",a.getContent());
        intent.putExtra("time",a.getPub_date());
        intent.putExtra("position",""+position);
        ListViewActivity.this.startActivity(intent);
    }
    //------------传感器
    @Override
    protected void onResume() {
        super.onResume();
        /*
        if(mSensorManager != null) {
            // 为重力传感器注册监听器
            mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_GAME);
        }
        */
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mSensorManager != null){
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == null){
            return;
        }
        float[] values = event.values;
        // 获取传感器类型
        int type = event.sensor.getType();
        StringBuilder sb;
        switch (type){
            case Sensor.TYPE_GRAVITY:
                if(values[0]<1&&values[2]<1)
                {
                    Intent intent = new Intent(this,
                            MainActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    // 当传感器精度发生改变时回调该方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
