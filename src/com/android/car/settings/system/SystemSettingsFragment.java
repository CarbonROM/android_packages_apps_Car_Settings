/*
 * Copyright (C) 2017 The Android Open Source Project
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
 * limitations under the License
 */


package com.android.car.settings.system;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.android.car.list.TypedPagedListAdapter;
import com.android.car.settings.R;
import com.android.car.settings.common.ListSettingsFragment;
import com.android.car.settings.home.ExtraSettingsLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Shows basic info about the system and provide some actions like update, reset etc.
 */
public class SystemSettingsFragment extends ListSettingsFragment {

    // Copied from hidden version in android.provider.Settings
    private static final String ACTION_SYSTEM_UPDATE_SETTINGS =
            "android.settings.SYSTEM_UPDATE_SETTINGS";

    public static SystemSettingsFragment getInstance() {
        SystemSettingsFragment systemSettingsFragment = new SystemSettingsFragment();
        Bundle bundle = ListSettingsFragment.getBundle();
        bundle.putInt(EXTRA_TITLE_ID, R.string.system_setting_title);
        systemSettingsFragment.setArguments(bundle);
        return systemSettingsFragment;
    }

    @Override
    public ArrayList<TypedPagedListAdapter.LineItem> getLineItems() {
        ArrayList<TypedPagedListAdapter.LineItem> lineItems = new ArrayList<>();
        Intent settingsIntent = new Intent(ACTION_SYSTEM_UPDATE_SETTINGS);
        PackageManager packageManager = getContext().getPackageManager();
        if (settingsIntent.resolveActivity(packageManager) != null) {
            lineItems.add(new SystemUpdatesLineItem(getContext(), settingsIntent));
        }
        ExtraSettingsLoader extraSettingLoader = new ExtraSettingsLoader(getContext());
        Map<String, Collection<TypedPagedListAdapter.LineItem>> extraSettings = extraSettingLoader.load();
        lineItems.addAll(extraSettings.get(ExtraSettingsLoader.SYSTEM_CATEGORY));

        lineItems.add(new AboutSystemLineItem(getContext(), getFragmentController()));
        lineItems.add(new LegalInfoLineItem(getContext()));
        return lineItems;
    }
}
