package myapplication.sch.example.com.first;
/*
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class BrowserActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressDialog mPgrogressDialog;
    private TextView tv_title;

    private Button btn_left;
    private Button btn_right;

    private String url = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        //------------
        webView = (WebView) this.findViewById(R.id.webView);
        webView.loadUrl("http://www.baidu.com");
        /*
        //获取传递的参数
        Intent it = getIntent();
        String u = it.getStringExtra("url");
        if(!TextUtils.isEmpty(u)){
            url = u;
        }

        webView = (WebView) this.findViewById(R.id.webView);
        tv_title = (TextView)this.findViewById(R.id.nav_titile_textview);
        btn_left = (Button) this.findViewById(R.id.nav_left_btn);
        btn_right =  (Button) this.findViewById(R.id.nav_right_btn);

        btn_left.setVisibility(View.VISIBLE);
        btn_right.setVisibility(View.VISIBLE);
        btn_left.setText("返回");
        btn_right.setText("刷新");

        //返回事件
        btn_left.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        //刷新事件
        btn_right.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPgrogressDialog.show();
                webView.loadUrl(url);
            }
        });
        // 显示网页加载中的小菊华花
        mPgrogressDialog =  new ProgressDialog(this);
        mPgrogressDialog.setTitle("");
        mPgrogressDialog.setMessage("正在加载网页...");
        mPgrogressDialog.show();

        webView.getSettings().setDefaultTextEncodingName("gbk");

        //在线程里启动网页加载
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                webView.loadUrl(url);
            }
        }).start();

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title)
            {
                // TODO Auto-generated method stub
                super.onReceivedTitle(view, title);
                final String tmpTitle = title;
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tv_title.setText(tmpTitle);
                    }
                });
            }
        });

        webView.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url)
            {

                super.onPageFinished(view, url);

                runOnUiThread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        mPgrogressDialog.dismiss();
                    }
                });

            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
            }
        });
        ///
    }
}
*/
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
public class BrowserActivity extends Activity implements OnClickListener {
    private String url = null;
    private WebView webView;
    private EditText et_url;
    private Button btn_login;
    private Button btn_back;
    private Button btn_exit;
    private Button btn_forward;
    private Button btn_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_browser);
        setProgressBarIndeterminate(true);
        webView = (WebView) findViewById(R.id.webView);
        et_url = (EditText) findViewById(R.id.et_url);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_forward = (Button) findViewById(R.id.btn_forward);
        btn_menu = (Button) findViewById(R.id.btn_menu);
        // 对五个按钮添加点击监听事件
        btn_login.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_forward.setOnClickListener(this);
        btn_menu.setOnClickListener(this);
    }
    protected void startReadUrl(String url) {
        webView.loadUrl(url);
        // 覆盖webView默认通过系统或者第三方浏览器打开网页的行为
        // 如果为false调用系统或者第三方浏览器打开网页的行为
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // webView加载web资源
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // web加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 当打开页面时 显示进度条 页面打开完全时 隐藏进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                setTitle("本页面已加载" + newProgress + "%");
                if (newProgress == 100) {
                    closeProgressBar();
                } else {
                    openProgressBar(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }
    protected void openProgressBar(int x) {
        setProgressBarIndeterminateVisibility(true);
        setProgress(x);
    }
    protected void closeProgressBar() {
        setProgressBarIndeterminateVisibility(false);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                url = "http://" + et_url.getText().toString();
                url = url.replace(" ", "");
                startReadUrl(url);
                break;
            case R.id.btn_back:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.btn_forward:
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
            case R.id.btn_exit:
                finish();
                break;
            case R.id.btn_menu:
                startReadUrl("http://www.baidu.com");
                break;
        }
    }
}