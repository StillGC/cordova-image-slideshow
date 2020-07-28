package org.km.plugins.image_slideshow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by zhangchong on 16/4/1.
 */
public class ImagePlugin extends CordovaPlugin {
    CallbackContext callbackContext;
    public boolean isOpen;
    ArrayList<String> listUrl;
    int imageNum;
    public Handler handler;


    @Override
    public boolean execute(String action, final JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext=callbackContext;
        if ("show".equals(action)){
            Log.e("xx", "1");
            imageNum=args.getInt(1);
            isOpen=true;
            try {
                listUrl=new ArrayList<String>();
                JSONArray jsonArray = args.getJSONArray(0);
                for (int i=0; i < jsonArray.length(); i++) {
                    Log.e("x", "" + jsonArray.get(i));
                    listUrl.add(jsonArray.getString(i));
                }
                Intent intent = new Intent(this.cordova.getActivity(),ImageShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listUrl", listUrl);
                bundle.putInt("imageNum", imageNum);
                bundle.putString("type","url");
                intent.putExtras(bundle);
                this.cordova.startActivityForResult((CordovaPlugin) this, intent, 1);
                Log.e("x",""+isOpen);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }else if ("showBase64".equals(action)){
            Log.e("xx", "2");
                imageNum=args.getInt(1);
                isOpen=true;
                try {
                listUrl=new ArrayList<String>();
                //JSONArray jsonArray = args.getJSONArray(0);
                this.cordova.getThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray jsonArray2 = args.getJSONArray(0);
                            SharedPreferences sps = cordova.getActivity().getSharedPreferences("base64", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sps.edit();
                            for (int i=0; i < jsonArray2.length(); i++) {
                                Log.e("x", "" + jsonArray2.get(i));
                                listUrl.add(jsonArray2.getString(i));
                                editor.putString("base64Img", jsonArray2.getString(i));
                                editor.commit();
                                Log.e("cg","spscg");
                            }
                        }catch (Exception e){
                           e.printStackTrace();
                        }

                    }
                });
                Intent intent = new Intent(this.cordova.getActivity(),ImageShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listUrl", null);
                bundle.putInt("imageNum", imageNum);
                bundle.putString("type","base64");
                intent.putExtras(bundle);
                this.cordova.startActivityForResult((CordovaPlugin) this, intent, 1);
                Log.e("x",""+isOpen);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (resultCode) { //resultCode为回传的标记，我在第二个Activity中回传的是RESULT_OK
            case 1:
                Bundle b=intent.getExtras();  //data为第二个Activity中回传的Intent
                isOpen=b.getBoolean("isOpen");  //str即为回传的值
                Log.e("x",""+isOpen);
                //下面三句为cordova插件回调页面的逻辑代码
                PluginResult mPlugin = new PluginResult(PluginResult.Status.OK,
                        isOpen);
                mPlugin.setKeepCallback(true);
                callbackContext.sendPluginResult(mPlugin);
                Log.e("x",""+isOpen);
                break;
            default:
                break;
        }
    }
}
