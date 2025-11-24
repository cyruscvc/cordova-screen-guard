package cordova.plugin.screenguard;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import android.view.WindowManager;

public class ScreenGuard extends CordovaPlugin {

    @Override
    public void pluginInitialize() {
        applySecureFlag();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("start".equals(action)) {
            applySecureFlag();
            callbackContext.success("started");
            return true;
        }
        if ("stop".equals(action)) {
            cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
            callbackContext.success("stopped");
            return true;
        }
        return false;
    }

    @Override
    public void onResume(boolean multitasking) {
        applySecureFlag();
    }

    private void applySecureFlag() {
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
}
