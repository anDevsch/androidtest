package myapplication.sch.example.com.first;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        //状态栏
        StatusBarCompat.setStatusBarColor(this,Color.parseColor("#3A5FCD"));
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String content=intent.getStringExtra("content");
        String time=intent.getStringExtra("time");
        String position=intent.getStringExtra("position");
        TextView ttt = (TextView)findViewById(R.id.ttt);
        TextView iii = (TextView)findViewById(R.id.iii);
        TextView nnn = (TextView)findViewById(R.id.nnn);
        nnn.setMovementMethod(new ScrollingMovementMethod());
        ttt.setText(title);
        iii.setText(time);
        nnn.setText(content);
        //getActionBar().setDisplayHomeAsUpEnabled(false);

        try{
            int p = Integer.parseInt(position.trim());
            nnn.setBackgroundColor(Color.argb(0, 0, 255, 0));
            switch(p%9){
                default:
                    //nnn.setBackgroundResource(R.drawable.er);
                    break;
            }
        }catch(Exception e){}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.alertdialog_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
            case R.id.Menu111:
                //new AlertDialog.Builder(this).setTitle("刷新").setIcon(0).show();
                Toast we=new Toast(this);
                we.makeText(this,"刷新",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Menu222:
                //Toast toast=new Toast(this);
                //toast.makeText(this,"返回",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return true;
    }
    private void showAlertDialog(String title,String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }

}
