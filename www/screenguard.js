var exec = require("cordova/exec");

exports.start = function(callback) {
    window.SCREEN_GUARD_CB = callback;

    // iOS: start observers
    exec(null, null, "ScreenGuard", "startObservers", []);

    // Android: enable FLAG_SECURE
    exec(null, null, "ScreenGuard", "enableSecure", []);
};
