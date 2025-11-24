var exec = require('cordova/exec');

var ScreenGuard = {
    start: function(success, error) {
        exec(success || null, error || null, "ScreenGuard", "start", []);
    },
    stop: function() {
        exec(null, null, "ScreenGuard", "stop", []);
    }
};

module.exports = ScreenGuard;
