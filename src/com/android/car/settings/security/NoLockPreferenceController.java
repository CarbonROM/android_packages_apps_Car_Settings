/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.car.settings.security;

import android.app.admin.DevicePolicyManager;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.android.car.settings.common.FragmentController;

/** Business logic for the no lock preference. */
public class NoLockPreferenceController extends LockTypeBasePreferenceController {

    private static final int[] ALLOWED_PASSWORD_QUALITIES =
            new int[]{DevicePolicyManager.PASSWORD_QUALITY_UNSPECIFIED};

    public NoLockPreferenceController(Context context, String preferenceKey,
            FragmentController fragmentController) {
        super(context, preferenceKey, fragmentController);
    }

    @Override
    protected Fragment fragmentToOpen() {
        return new ConfirmRemoveScreenLockDialog(mContext, getFragmentController(),
                getCurrentPassword());
    }

    @Override
    protected int[] allowedPasswordQualities() {
        return ALLOWED_PASSWORD_QUALITIES;
    }
}