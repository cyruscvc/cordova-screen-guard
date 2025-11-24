package cordova.plugin.screenguard;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import android.view.WindowManager;
import android.util.Log;

import org.json.JSONArray;

public class ScreenGuard extends CordovaPlugin {

    private static final String TAG = "ScreenGuard";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("start".equals(action) || "enable".equals(action)) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cordova.getActivity().getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_SECURE,
                        WindowManager.LayoutParams.FLAG_SECURE
                    );
                    Log.d(TAG, "FLAG_SECURE enabled – screenshots & recordings blocked");
                    callbackContext.success("ScreenGuard activated");
                }
            });
            return true;
        }

        if ("stop".equals(action) || "disable".equals(action)) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
                    Log.d(TAG, "FLAG_SECURE disabled");
                    callbackContext.success("ScreenGuard deactivated");
                }
            });
            return true;
        }

        return false;
    }
    @Override
    public void onResume(boolean multitasking) {
        cordova.getActivity().getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        );
    }
}
