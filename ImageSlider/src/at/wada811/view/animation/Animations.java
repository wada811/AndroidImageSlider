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
package at.wada811.view.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Animations {

    public static Animation getAccelerateDecelerate(Context context){
//        android.support.v7.appcompat.R.anim.abc_fade_in
        return AnimationUtils.loadAnimation(context, android.R.anim.accelerate_decelerate_interpolator);
    }

    public static Animation getAccelerate(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.accelerate_interpolator);
    }

    public static Animation getAnticipate(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.anticipate_interpolator);
    }

    public static Animation getAnticipateOvershoot(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.anticipate_overshoot_interpolator);
    }

    public static Animation getBounce(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.bounce_interpolator);
    }

    public static Animation getCycle(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.cycle_interpolator);
    }

    public static Animation getDecelerate(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.decelerate_interpolator);
    }

    public static Animation getFadeIn(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
    }

    public static Animation getFadeOut(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
    }

    public static Animation getLinear(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.linear_interpolator);
    }

    public static Animation getOvershoot(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.overshoot_interpolator);
    }

    public static Animation getSlideInLift(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
    }

    public static Animation getSlideOutRight(Context context){
        return AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
    }
}
