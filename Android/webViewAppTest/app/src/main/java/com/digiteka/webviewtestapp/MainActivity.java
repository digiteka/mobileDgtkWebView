package com.digiteka.webviewtestapp;

//import com.google.android.gms.ads.identifier.AdvertisingIdClient;
//import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
//import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
//import com.google.android.gms.common.GooglePlayServicesRepairableException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AbsListView;
import android.text.Html;
import android.util.*;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    WebView dtkWebView,articleWebView;
    boolean loadingFinished = true;
    boolean redirect = false;
    ScrollView scrollview;
    public float yPos;

    String htmlString =
            "<h1>HTML String Title in TextView</h1>\n" +
                    "<h3>HTML Paragraph:</h3>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p><a target='_blank' href='https://www.ultimedia.com/deliver/generic/iframe/mdtk/01310035/src/ss5rl5/zone/3/showtitle/1/autoplay/no'>CLICK HERE </a></p>\n"+
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<p>Display html string in android TextView. How to show HTMl string in android TextView. This html paragraph</p>\n" +
                    "<h3>HTML Unordered List: </h3>\n" +
                    "<ol>\n" +
                    "\t<li>HTML List Item 1</li> \n" +
                    "\t<li>HTML List Item 2</li>\n" +
                    "\t<li>HTML List Item 3</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "\t<li>HTML List Item 4</li>\n" +
                    "</ol>";

    @SuppressLint("JavascriptInterface")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dtkWebView = (WebView) findViewById(R.id.idDtkWebView);
        dtkWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        dtkWebView.getSettings().setJavaScriptEnabled(true);
        dtkWebView.getSettings().setLoadWithOverviewMode(true);
        dtkWebView.getSettings().setUseWideViewPort(true);
        dtkWebView.getSettings().setDomStorageEnabled(true);
        dtkWebView.getSettings().setAppCacheEnabled(false);
        dtkWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        dtkWebView.clearCache(true);
        TextView textView = (TextView) findViewById(R.id.display_html_string);
        textView.setText(Html.fromHtml(htmlString));
        TextView textView_2 = (TextView) findViewById(R.id.display_html_string_2);
        textView_2.setText(Html.fromHtml(htmlString));

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("REFERER", "jukebo.net");
        headers.put("HTTP_X_REQUESTED_WITH", "jukebo.net");
        dtkWebView.loadUrl("https://www.ultimedia.com/deliver/generic/iframe/mdtk/01898292/src/rlukm3/zone/2/showtitle/1/", headers);

        dtkWebView.setWebViewClient(new WebViewClient(){
            // Load the url
            /*public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            }*/

            // api less then 24
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.err.println("LOADED URL STRING 1 "+ url);
                view.loadUrl(url,getCustomHeaders());
                return false;
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                System.err.println("LOADED URL STRING 2");
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("REFERER", "jukebo.net");
                view.loadUrl(view.getUrl(),getCustomHeaders());
                return false;
            }

            // When finish loading page
            public void onPageFinished(WebView view, String url) {
                String manufacturer = Build.MANUFACTURER;
                String model = Build.MODEL;
                int version = Build.VERSION.SDK_INT;
                String versionRelease = Build.VERSION.RELEASE;

                Log.e("MyActivity", "manufacturer " + manufacturer
                        + " \n model " + model
                        + " \n version " + version
                        + " \n versionRelease " + versionRelease
                );
//                System.err.println("Testing onPageFinished callback...first.." + getAdId());
            }

        });
    }

    /*private WebViewClient getWebViewClient()
    {
        return new WebViewClient()
        {
            @Override
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                System.err.println("LOADED URL STRING 1 "+ request.getUrl().toString());
                view.loadUrl(request.getUrl().toString(), getCustomHeaders());
                return true;
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                System.err.println("LOADED URL STRING 2 "+ url);
                view.loadUrl(url, getCustomHeaders());
                return true;
            }
        };
    }*/

    /** Retrieve the Android Advertising Id
     *
     * The device must be KitKat (4.4)+
     * This method must be invoked from a background thread.
     *
     * */
//    public String getAdId () {
//        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... params) {
//                AdvertisingIdClient.Info idInfo = null;
//                try {
//                    idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                String advertId = null;
//                try{
//                    advertId = idInfo.getId();
//                }catch (NullPointerException e){
//                    e.printStackTrace();
//                }
//                return advertId;
//            }
//
//            @Override
//            protected void onPostExecute(String advertId) {
//                System.err.println("DEBUG ANDROID ADVERTISING ID : " + advertId);
//                Toast.makeText(getApplicationContext(), advertId, Toast.LENGTH_SHORT).show();
//            }
//
//        };
//        task.execute();
//        return "testing";
//    }

    private Map<String, String> getCustomHeaders()
    {
        Map<String, String> headers = new HashMap<>();
        headers.put("REFERER", "jukebo.net");
        return headers;
    }

    protected void onStart() {
        super.onStart();
        System.err.println("Testing onStart callback...");
    }
}