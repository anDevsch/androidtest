package myapplication.sch.example.com.first;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionActivity extends AppCompatActivity {
    public static final int SHOW_RESPONSE = 0;
    private TextView tvShowContent;

    private Handler handler= new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg. what) {
                case SHOW_RESPONSE:
                    String result=(String) msg. obj;
                    tvShowContent.setText(result);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_httptest);

        tvShowContent=(TextView) findViewById(R.id.content);

    }

    public void httpUrlConnection_get(View view) {
        new Thread( new Runnable() {

            @Override
            public void run() {

                InputStreamReader in= null;
                HttpURLConnection httpUrlConnection= null;
                try {
                    URL url= new URL("http://www.baidu.com" );
                    httpUrlConnection = (HttpURLConnection) url.openConnection();
                    httpUrlConnection.setRequestMethod( "GET");
                    httpUrlConnection.setConnectTimeout(8000);
                    httpUrlConnection.setReadTimeout(8000);
                    httpUrlConnection.setDoInput(true);
                    httpUrlConnection.setDoOutput(true);
                    httpUrlConnection.setUseCaches(false);

                    httpUrlConnection.connect();
                    InputStream inputStream=httpUrlConnection.getInputStream();
                    in= new InputStreamReader(inputStream);
                    BufferedReader bf= new BufferedReader(in);
                    StringBuffer sb= new StringBuffer();
                    String inputLine= null;

                    while((inputLine=bf.readLine())!= null){
                        sb.append(inputLine);
                    }

                    Message message= new Message();
                    message. what= SHOW_RESPONSE;
                    message. obj=sb.toString();
                    handler.sendMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if(httpUrlConnection!= null){
                        httpUrlConnection.disconnect();
                    }
                }
            }
        }).start();
    }
}
