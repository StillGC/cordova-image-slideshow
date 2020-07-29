package org.km.plugins.image_slideshow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;

//import _PACKAGE_NAME_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchong on 16/5/16.
 */
public class ImageShowActivity extends Activity {
    ViewPager viewPager;
    int imageNum;
    ArrayList listUrl;
    String type;
    boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.image_show_activity);
        setContentView(getApplication().getResources().getIdentifier("image_show_activity", "layout", getApplication().getPackageName()));
        init();
        showImage(listUrl,imageNum,type);

    }

    private void init(){
        listUrl=new ArrayList();
        Bundle bundle=getIntent().getExtras();
        imageNum=bundle.getInt("imageNum");
        SharedPreferences sps = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
        String base64Img =sps.getString("base64Img", "");
        type=bundle.getString("type");
        if (type.equals("base64")){
            //Log.e("spscg",base64Img);
            listUrl.add(base64Img);
        }else {
            listUrl=bundle.getStringArrayList("listUrl");
        }

    }

    private void showImage(List<String> listUrl,int imageNum,String type) {
        viewPager = new HackyViewPager(this);
        viewPager.setAdapter(new SampleAdapter(this, listUrl,type));
        viewPager.setCurrentItem(imageNum-1);
        try {
            this.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            int backGroundColor = Color.parseColor("#000000");
                            viewPager.setBackgroundColor(backGroundColor);
                            addContentView(viewPager, layoutParams);


                        }
                    }

            );
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Log.e("onKeyDown","KEYCODE_BACK");
            isOpen=false;
            Intent mIntent = new Intent();
            mIntent.putExtra("isOpen", isOpen);
            // 设置结果，并进行传送
            setResult(1, mIntent);
            this.finish();
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


}
