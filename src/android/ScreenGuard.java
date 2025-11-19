package com.cordova.screenguard;

import android.view.WindowManager;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class ScreenGuard extends CordovaPlugin {

    @Override
    protected void pluginInitialize() {
        enableSecureFlag();
    }

    private void enableSecureFlag() {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cordova.getActivity().getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE
                );
            }
        });
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {

        if (action.equals("enableSecure")) {
            enableSecureFlag();
            callbackContext.success();
            return true;
        }

        callbackContext.error("Unknown action: " + action);
        return false;
    }
}
