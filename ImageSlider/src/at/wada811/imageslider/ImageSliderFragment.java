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

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;
import at.wada811.utils.BitmapUtils;
import at.wada811.utils.LogUtils;
import at.wada811.view.SwipeTouchListener;
import at.wada811.view.SwipeTouchListener.OnSwipeListener;

public class ImageSliderFragment extends Fragment {

    public static final String TAG = ImageSliderFragment.class.getSimpleName();
    private ImageSliderFragmentCallback mCallback;
    private ImageSwitcher mImageSwitcher;
    private SwipeTouchListener mSwipeTouchListener = new SwipeTouchListener(new OnSwipeListener(){
        @Override
        public void onSwipeUp(){
            LogUtils.v();
            mCallback.onSwipeUp(ImageSliderFragment.this);
        }

        @Override
        public void onSwipeRight(){
            LogUtils.v();
            mCallback.onSwipeRight(ImageSliderFragment.this);
        }

        @Override
        public void onSwipeLeft(){
            LogUtils.v();
            mCallback.onSwipeLeft(ImageSliderFragment.this);
        }

        @Override
        public void onSwipeDown(){
            LogUtils.v();
            mCallback.onSwipeDown(ImageSliderFragment.this);
        }
    });

    public static interface ImageSliderFragmentCallbackProvider {

        public ImageSliderFragmentCallback getImageSliderCallback();

    }

    public static interface ImageSliderFragmentCallback {

        public void onSwipeUp(ImageSliderFragment imageSliderFragment);

        public void onSwipeRight(ImageSliderFragment imageSliderFragment);

        public void onSwipeLeft(ImageSliderFragment imageSliderFragment);

        public void onSwipeDown(ImageSliderFragment imageSliderFragment);
    }

    public static ImageSliderFragment newInstance(){
        ImageSliderFragment fragment = new ImageSliderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        if(activity instanceof ImageSliderFragmentCallbackProvider == false){
            throw new ClassCastException(activity.getLocalClassName() + " must implements " + ImageSliderFragmentCallbackProvider.class.getSimpleName());
        }
        ImageSliderFragmentCallbackProvider provider = (ImageSliderFragmentCallbackProvider)activity;
        mCallback = provider.getImageSliderCallback();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mImageSwitcher = new ImageSwitcher(getActivity());
        mImageSwitcher.setFactory(new ViewFactory(){
            @Override
            public View makeView(){
                return new ImageView(getActivity());
            }
        });
        mImageSwitcher.setOnTouchListener(mSwipeTouchListener);
        setImageBitmap(BitmapUtils.createBitmapFromResource(getActivity(), R.drawable.ic_launcher));
        return mImageSwitcher;
    }

    public void setInOutAnimation(Animation inAnimation, Animation outAnimation){
        mImageSwitcher.setInAnimation(inAnimation);
        mImageSwitcher.setOutAnimation(outAnimation);
    }

    public void setInterpolator(Interpolator interpolator){
        Animation inAnimation = mImageSwitcher.getInAnimation();
        inAnimation.setInterpolator(interpolator);
        Animation outAnimation = mImageSwitcher.getOutAnimation();
        outAnimation.setInterpolator(interpolator);
        setInOutAnimation(inAnimation, outAnimation);
    }

    public void setDuration(int duration){
        Animation inAnimation = mImageSwitcher.getInAnimation();
        inAnimation.setDuration(duration);
        Animation outAnimation = mImageSwitcher.getOutAnimation();
        outAnimation.setDuration(duration);
        setInOutAnimation(inAnimation, outAnimation);
    }

    public void setImageBitmap(Bitmap bitmap){
        mImageSwitcher.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
    }

}
