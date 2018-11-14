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
 * limitations under the License.
 */
package com.android.car.settings.wifi.details;

import android.content.Context;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;

import com.android.car.settings.common.FragmentController;

import java.net.Inet4Address;

/**
 * Shows info about Wifi IP address.
 */
public class WifiIpAddressPreferenceController extends WifiDetailPreferenceControllerBase {

    public WifiIpAddressPreferenceController(
            Context context, String preferenceKey, FragmentController fragmentController) {
        super(context, preferenceKey, fragmentController);
    }

    @Override
    public void onLinkPropertiesChanged(Network network, LinkProperties lp) {
        super.onLinkPropertiesChanged(network, lp);
        updateInfo();
    }

    @Override
    protected void updateInfo() {
        String ipv4Address = null;

        for (LinkAddress addr : mWifiInfoProvider.getLinkProperties().getLinkAddresses()) {
            if (addr.getAddress() instanceof Inet4Address) {
                ipv4Address = addr.getAddress().getHostAddress();
            }
        }

        if (ipv4Address == null) {
            mWifiDetailPreference.setVisible(false);
        } else {
            mWifiDetailPreference.setDetailText(ipv4Address);
            mWifiDetailPreference.setVisible(true);
        }
    }
}