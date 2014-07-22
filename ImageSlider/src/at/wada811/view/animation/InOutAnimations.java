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

import android.view.animation.Animation;

public class InOutAnimations {

    public Animation inAnimation;
    public Animation outAnimation;

    public InOutAnimations(Animation inAnimation, Animation outAnimation){
        this.inAnimation = inAnimation;
        this.outAnimation = outAnimation;
    }

}
