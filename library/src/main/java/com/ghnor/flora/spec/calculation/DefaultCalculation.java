package com.ghnor.flora.spec.calculation;

/**
 * Copyright 2016 Zheng Zibin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Edited by ghnor on 2017/7/7.
 * ghnor.me@gmail.com
 */

public class DefaultCalculation extends Calculation {

    @Override
    public int calculateInSampleSize(int srcWidth, int srcHeight) {
        srcWidth = srcWidth % 2 == 1 ? srcWidth + 1 : srcWidth;
        srcHeight = srcHeight % 2 == 1 ? srcHeight + 1 : srcHeight;

        int longSide = Math.max(srcWidth, srcHeight);
        int shortSide = Math.min(srcWidth, srcHeight);

        int inSampleSize;

        float scale = ((float) shortSide / longSide);
        if (scale <= 1 && scale > 0.5625) {
            if (longSide < 1664) {
                inSampleSize = 1;
            } else if (longSide >= 1664 && longSide < 4990) {
                inSampleSize = 2;
            } else if (longSide > 4990 && longSide < 10240) {
                inSampleSize = 4;
            } else {
                inSampleSize = longSide / 1280 == 0 ? 1 : longSide / 1280;
            }
        } else if (scale <= 0.5625 && scale > 0.5) {
            inSampleSize = longSide / 1280 == 0 ? 1 : longSide / 1280;
        } else {
            inSampleSize = (int) Math.ceil(longSide / (900.0 / scale));
        }

        return inSampleSize;
    }
}
