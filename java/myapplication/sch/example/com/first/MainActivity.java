package myapplication.sch.example.com.first;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void setNotify(View view){
        Toast toast=new Toast(this);
        toast.makeText(this,"setNotify",Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this,SensorActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,0);

        Notification.Builder b =new Notification.Builder(this)
                //Ticker是状态栏显示的提示
                .setTicker("简单Notification")
                //第一行内容  通常作为通知栏标题
                .setContentTitle("Sensor")
                //第二行内容 通常是通知正文
                .setContentText("you are got a notify!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
                //.addAction(android.R.drawable.ic_menu_gallery,"Open",pendingIntent);

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(123456,b.build());

    }
    public void clearNotify(View view){
        Toast toast=new Toast(this);
        toast.makeText(this,"clear Notify",Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(123456);
    }
    public void button1(View view){
        Intent intent = new Intent(this,
                SensorActivity.class);
        startActivity(intent);
    }
    public void button2(View view){
        Intent intent = new Intent(this,
                BrowserActivity.class);
        startActivity(intent);
    }
    public void button3(View view){
        setNotify(view);
    }
    public void button4(View view){
        //clearNotify(view);
        Intent intent = new Intent(this,
                ListViewActivity.class);
        startActivity(intent);
    }
}
