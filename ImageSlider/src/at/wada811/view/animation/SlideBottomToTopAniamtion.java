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
import at.wada811.imageslider.R;

public class SlideBottomToTopAniamtion implements InOutAnimation {

    private InOutAnimations mInOutAnimation;

    public SlideBottomToTopAniamtion(Context context) {
        Animation inAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
        Animation outAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out_top);
        mInOutAnimation = new InOutAnimations(inAnimation, outAnimation);
    }

    @Override
    public Animation getInAnimation(){
        return mInOutAnimation.inAnimation;
    }

    @Override
    public Animation getOutAnimation(){
        return mInOutAnimation.outAnimation;
    }

}
