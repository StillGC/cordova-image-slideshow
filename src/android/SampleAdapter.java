package org.km.plugins.image_slideshow;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zhangchong on 16/3/30.
 */
public class SampleAdapter extends PagerAdapter {
    Activity activity;
    List<String> listUrl;
    String type;
    boolean isOpen;
    ProgressBar loadingBar;

    SampleAdapter(Activity activity, List<String> listUrl,String type, ProgressBar loadingBar){
        this.activity=activity;
        this.listUrl=listUrl;
        this.type=type;
        this.loadingBar=loadingBar;
    }

    private byte[] baseToByte (String baseStr){
        byte[] bitmapArray;
        bitmapArray = Base64.decode(baseStr, 0);
        return bitmapArray;
    }

    @Override
    public int getCount() {
        return listUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        final PhotoView photoView=new PhotoView(container.getContext());
        List<byte[]> baseList=new ArrayList<byte[]>();
        if (type.equals("base64")){
            for (int i = 0; i < listUrl.size(); i++) {
                byte[] bytes=baseToByte(listUrl.get(i));
                baseList.add(bytes);
            }
            Glide.with(activity).load(baseList.get(position)).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        loadingBar.setVisibility(View.INVISIBLE);
                        return false;
                    }
                }).into(photoView);
        }else {
            Glide.with(activity).load(listUrl.get(position)).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        loadingBar.setVisibility(View.INVISIBLE);
                        return false;
                    }
                }).into(photoView);
        }

        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                //container.setVisibility(View.GONE);
                isOpen=false;
                Intent mIntent = new Intent();
                mIntent.putExtra("isOpen", isOpen);
                // 设置结果，并进行传送
                activity.setResult(1, mIntent);
                activity.finish();
            }
        });
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("xxx", listUrl.get(position));
                return true;
            }
        });


        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
