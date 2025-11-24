var exec = require('cordova/exec');

exports.start = function(callback) {
    window.SCREEN_GUARD_CB = callback;

    // iOS: start observers (if plugin class exists)
    try {
        exec(null, null, "ScreenGuard", "startObservers", []);
    } catch (e) {
        // ignore if not available
    }

    // Android: enable FLAG_SECURE
    try {
        exec(null, null, "ScreenGuard", "enableSecure", []);
    } catch (e) {
        // ignore if not available
    }
};
