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

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

public enum AnimationInterpolator {

    //
    AccelerateDecelerate(AccelerateDecelerateInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new AccelerateDecelerateInterpolator();
        }
    },
    //
    Accelerate(AccelerateInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new AccelerateInterpolator();
        }
    },
    //
    Anticipate(AnticipateInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new AnticipateInterpolator();
        }
    },
    //
    AnticipateOvershoot(AnticipateOvershootInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new AnticipateOvershootInterpolator();
        }
    },
    // 
    Bounce(BounceInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new BounceInterpolator();
        }
    },
    //
    Cycle(CycleInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new CycleInterpolator(1);
        }
    },
    //
    Decelerate(DecelerateInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new DecelerateInterpolator();
        }
    },
    //
    Linear(LinearInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new LinearInterpolator();
        }
    },
    //
    Overshoot(OvershootInterpolator.class){
        @Override
        public Interpolator getInterpolator(){
            return new OvershootInterpolator();
        }
    };

    public Class<? extends Interpolator> interpolator;

    AnimationInterpolator(Class<? extends Interpolator> interpolator) {
        this.interpolator = interpolator;
    }

    public abstract Interpolator getInterpolator();

}
