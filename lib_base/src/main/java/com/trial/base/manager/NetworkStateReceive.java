package com.trial.base.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.trial.base.R;
import com.trial.base.utils.NetworkUtils;

import java.util.Objects;

/**
 * <pre>
 *     @author : Trial
 *     @time   : 7/7/21
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
public class NetworkStateReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (!NetworkUtils.isConnected()) {
                Toast.makeText(context, context.getString(R.string.network_not_good), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
