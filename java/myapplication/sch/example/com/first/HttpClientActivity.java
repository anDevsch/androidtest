package myapplication.sch.example.com.first;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/*
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
*/


public class HttpClientActivity extends AppCompatActivity{
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

        tvShowContent = (TextView)findViewById(R.id.content);

    }
/*
    public void httpUrlConnection_get(View view) {
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    String resultData = "404";
                    HttpGet httpGet= new HttpGet("http://www.baidu.com" );
                    HttpClient httpClient= new DefaultHttpClient();
                    HttpResponse httpResponse=httpClient.execute(httpGet);
                    if(httpResponse.getStatusLine().getStatusCode()== HttpStatus. SC_OK){
                        resultData= EntityUtils. toString(httpResponse.getEntity(),"utf-8");
                        Message message= new Message();
                        message. what= SHOW_RESPONSE;
                        message. obj= resultData;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    */
}
