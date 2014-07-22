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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import at.wada811.view.animation.AnimationInterpolator;
import at.wada811.view.animation.InOutAnimation;
import at.wada811.view.animation.InOutAnimationList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DebugFragment extends Fragment {

    public static final String TAG = DebugFragment.class.getSimpleName();
    private DebugFragmentCallback mCallback;

    public static interface DebugFragmentCallbackProvider {

        public DebugFragmentCallback getDebugCallback();

    }

    public static interface DebugFragmentCallback {

        public void setInOutAnimation(InOutAnimation inOutAnimation);

        public void setInterpolator(Interpolator interpolator);

        public void setDuration(int duration);

    }

    public static DebugFragment newInstance(){
        DebugFragment fragment = new DebugFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        if(activity instanceof DebugFragmentCallbackProvider == false){
            throw new ClassCastException(activity.getLocalClassName() + " must implements " + DebugFragmentCallbackProvider.class.getSimpleName());
        }
        DebugFragmentCallbackProvider provider = (DebugFragmentCallbackProvider)activity;
        mCallback = provider.getDebugCallback();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_debug, container, false);
        Spinner animationSpinner = (Spinner)view.findViewById(R.id.animation);
        Spinner interpolatorSpinner = (Spinner)view.findViewById(R.id.interpolator);
        Spinner durationSpinner = (Spinner)view.findViewById(R.id.duration);
        initAnimation(animationSpinner);
        initInterpolator(interpolatorSpinner);
        initDuration(durationSpinner);
        return view;
    }

    private void initAnimation(Spinner animationSpinner){
        List<InOutAnimationList> inOutAnimationLists = Arrays.asList(InOutAnimationList.values());
        ArrayList<InOutAnimation> tempInOutAnimations = new ArrayList<InOutAnimation>();
        for(InOutAnimationList inOutAnimationList : inOutAnimationLists){
            InOutAnimation inOutAnimation = inOutAnimationList.getInOutAnimation(getActivity());
            tempInOutAnimations.add(inOutAnimation);
        }
        final ArrayList<InOutAnimation> inOutAnimations = new ArrayList<InOutAnimation>(tempInOutAnimations);
        AnimationAdapter adapter = new AnimationAdapter(getActivity(), inOutAnimations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animationSpinner.setAdapter(adapter);
        animationSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                InOutAnimation inOutAnimation = inOutAnimations.get(position);
                mCallback.setInOutAnimation(inOutAnimation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }

    public class AnimationAdapter extends ArrayAdapter<InOutAnimation> {

        private ArrayList<InOutAnimation> mInOutAnimations;
        private LayoutInflater mLayoutInflater;

        public AnimationAdapter(Context context, ArrayList<InOutAnimation> inOutAnimations) {
            super(context, 0);
            mInOutAnimations = inOutAnimations;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public InOutAnimation getItem(int position){
            return mInOutAnimations.get(position);
        }

        @Override
        public int getCount(){
            return mInOutAnimations.size();
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view = mLayoutInflater.inflate(R.layout.layout_spinner, parent, false);
            TextView textView = (TextView)view.findViewById(R.id.item);
            textView.setText(getItem(position).getClass().getSimpleName());
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View view = mLayoutInflater.inflate(R.layout.layout_spinner, parent, false);
            TextView textView = (TextView)view.findViewById(R.id.item);
            textView.setText(getItem(position).getClass().getSimpleName());
            return view;
        }

    }

    private void initInterpolator(Spinner interpolatorSpinner){
        List<AnimationInterpolator> animationInterpolatorLists = Arrays.asList(AnimationInterpolator.values());
        ArrayList<Interpolator> tempAnimationInterpolators = new ArrayList<Interpolator>();
        for(AnimationInterpolator animationInterpolator : animationInterpolatorLists){
            Interpolator interpolator = animationInterpolator.getInterpolator();
            tempAnimationInterpolators.add(interpolator);
        }
        final ArrayList<Interpolator> animationInterpolators = new ArrayList<Interpolator>(tempAnimationInterpolators);
        interpolatorSpinner.setAdapter(new InterpolatorAdapter(getActivity(), animationInterpolators));
        interpolatorSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                Interpolator interpolator = animationInterpolators.get(position);
                mCallback.setInterpolator(interpolator);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }

    public class InterpolatorAdapter extends ArrayAdapter<Interpolator> {

        private ArrayList<Interpolator> mInterpolators;
        private LayoutInflater mLayoutInflater;

        public InterpolatorAdapter(Context context, ArrayList<Interpolator> animationInterpolators) {
            super(context, 0);
            mInterpolators = animationInterpolators;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public Interpolator getItem(int position){
            return mInterpolators.get(position);
        }

        @Override
        public int getCount(){
            return mInterpolators.size();
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view = mLayoutInflater.inflate(R.layout.layout_spinner, parent, false);
            TextView textView = (TextView)view.findViewById(R.id.item);
            textView.setText(getItem(position).getClass().getSimpleName());
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View view = mLayoutInflater.inflate(R.layout.layout_spinner, parent, false);
            TextView textView = (TextView)view.findViewById(R.id.item);
            textView.setText(getItem(position).getClass().getSimpleName());
            return view;
        }
    }

    private void initDuration(Spinner durationSpinner){
        List<String> asList = Arrays.asList(new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        });
        final ArrayList<String> durations = new ArrayList<String>(asList);
        durationSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.layout_spinner, R.id.item, durations));
        durationSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String duration = durations.get(position);
                mCallback.setDuration(Integer.valueOf(duration) * 1000);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }
}
