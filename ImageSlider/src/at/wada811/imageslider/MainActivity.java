/*
 * Copyright 2014 wada811<at.wada811@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.wada811.imageslider;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import at.wada811.imageslider.DebugFragment.DebugFragmentCallback;
import at.wada811.imageslider.DebugFragment.DebugFragmentCallbackProvider;
import at.wada811.imageslider.ImageSliderFragment.ImageSliderFragmentCallback;
import at.wada811.imageslider.ImageSliderFragment.ImageSliderFragmentCallbackProvider;
import at.wada811.utils.BitmapUtils;
import at.wada811.utils.LogUtils;
import at.wada811.view.animation.InOutAnimation;
import at.wada811.view.animation.InOutAnimationList;
import java.io.IOException;

public class MainActivity extends ActionBarActivity implements ImageSliderFragmentCallbackProvider, DebugFragmentCallbackProvider {

    final MainActivity self = this;
    private ImageSliderFragment mImageSliderFragment;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            initImageSlider();
            initCursorLoader();
        }
    }

    private void initImageSlider(){
        mImageSliderFragment = ImageSliderFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.image, mImageSliderFragment, ImageSliderFragment.TAG).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.debug, DebugFragment.newInstance(), DebugFragment.TAG).commit();
    }

    private void initCursorLoader(){
        getSupportLoaderManager().initLoader(0, null, new LoaderCallbacks<Cursor>(){
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle data){
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                return new CursorLoader(self, uri, null, null, null, null);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor){
                mCursor = cursor;
                LogUtils.d("Position: " + cursor.getPosition());
                boolean moveToFirst = cursor.moveToFirst();
                LogUtils.d("moveToFirst: " + moveToFirst);
                LogUtils.d("Position: " + cursor.getPosition());
                Bitmap bitmap = loadBitmap(cursor);
                mImageSliderFragment.setImageBitmap(bitmap);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader){

            }
        });
    }

    private Bitmap loadBitmap(Cursor cursor){
        LogUtils.d("Position: " + cursor.getPosition());
        String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
//        int orientation = getOrientationFromExif(filePath);
//        LogUtils.d("orientation: " + orientation);
        LogUtils.d(filePath);
        Bitmap bitmap = BitmapUtils.createBitmapFromFile(filePath);
//        bitmap = BitmapUtils.rotate(bitmap, orientation);
        return bitmap;
    }

    private int getOrientationFromExif(String filePath){
        try{
            ExifInterface exifInterface = new ExifInterface(filePath);
            int orientationAttr = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            LogUtils.d("orientationAttr: " + orientationAttr);
            switch(orientationAttr){
                case ExifInterface.ORIENTATION_NORMAL:
                    return 0;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return 90;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return 180;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return 270;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ImageSliderFragmentCallback getImageSliderCallback(){
        return new ImageSliderFragmentCallback(){
            @Override
            public void onSwipeUp(ImageSliderFragment imageSliderFragment){
                int i = (int)(System.currentTimeMillis() % InOutAnimationList.values().length);
                InOutAnimationList animations = InOutAnimationList.values()[i];
                Animation inAnimation = animations.getInOutAnimation(self).getInAnimation();
                Interpolator interpolator = inAnimation.getInterpolator();
                imageSliderFragment.setInOutAnimation(animations.getInOutAnimation(self).getInAnimation(), animations.getInOutAnimation(self).getOutAnimation());
                nextImage(imageSliderFragment);
            }

            @Override
            public void onSwipeRight(ImageSliderFragment imageSliderFragment){
                nextImage(imageSliderFragment);
            }

            @Override
            public void onSwipeLeft(ImageSliderFragment imageSliderFragment){
                prevImage(imageSliderFragment);
            }

            @Override
            public void onSwipeDown(ImageSliderFragment imageSliderFragment){
                prevImage(imageSliderFragment);
            }
        };
    }

    private void nextImage(ImageSliderFragment imageSliderFragment){
        LogUtils.d();
        if(mCursor.moveToNext()){
        }else{
            mCursor.moveToFirst();
        }
        Bitmap bitmap = loadBitmap(mCursor);
        imageSliderFragment.setImageBitmap(bitmap);
    }

    private void prevImage(ImageSliderFragment imageSliderFragment){
        LogUtils.d();
        if(mCursor.moveToPrevious()){
        }else{
            mCursor.moveToLast();
        }
        Bitmap bitmap = loadBitmap(mCursor);
        imageSliderFragment.setImageBitmap(bitmap);
    }

    @Override
    public DebugFragmentCallback getDebugCallback(){
        return new DebugFragmentCallback(){
            @Override
            public void setInterpolator(Interpolator interpolator){
                mImageSliderFragment.setInterpolator(interpolator);
            }

            @Override
            public void setInOutAnimation(InOutAnimation inOutAnimation){
                mImageSliderFragment.setInOutAnimation(inOutAnimation.getInAnimation(), inOutAnimation.getOutAnimation());
            }

            @Override
            public void setDuration(int duration){
                mImageSliderFragment.setDuration(duration);
            }
        };
    }

}
