# Cordova Screen Guard

Cordova Screen Guard is a cross-platform security plugin for Cordova and OutSystems mobile applications.
It protects sensitive data by **blocking screenshots and screen recordings on Android**, and **detecting screenshot and recording events on iOS**.

This plugin is useful for enterprise apps, HR systems, finance apps, employee ID badges, and any application that handles confidential information.

---

## Features

### Android (Full Blocking)

Android allows complete prevention of screen capture. This plugin:

* Blocks screenshots
* Blocks screen recording (recordings appear black)
* Hides the app preview in the task switcher
* Requires no UI changes

### iOS (Event Detection)

iOS does not allow blocking screenshots.
Instead, this plugin detects events so your application can react accordingly.

* Detects when a screenshot is taken
* Detects when screen recording starts
* Detects when screen recording stops
* Allows you to blur, mask, or hide sensitive UI

---

## Usage

Initialize the plugin once (e.g., on app start):

```javascript
ScreenGuard.start(function(event) {
    if (event === "screenshot") {
        console.log("Screenshot captured");
    }

    if (event === "recording_started") {
        console.log("Screen recording started");
    }

    if (event === "recording_stopped") {
        console.log("Screen recording stopped");
    }
});
```

---

## Android Behavior

The plugin applies:

```
WindowManager.LayoutParams.FLAG_SECURE
```

This ensures:

* Screenshots fail
* Screen recordings display a black screen
* Recent-app preview is hidden

This behavior is automatic and does not require any UI-level changes.

---

## iOS Behavior

### Screenshot Detection

Uses:

```
UIApplicationUserDidTakeScreenshotNotification
```

### Screen Recording Detection

Monitors:

```
UIScreen.main.isCaptured
```

Events are forwarded to JavaScript through the callback function used in `ScreenGuard.start()`.

---

## License

MIT License

---

## Support

If you encounter any issues, open an Issue or Pull Request on GitHub.

