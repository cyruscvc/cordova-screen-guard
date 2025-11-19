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

## Folder Structure

```
cordova-screen-guard/
│
├── plugin.xml
│
├── src/
│   ├── android/
│   │   └── ScreenGuard.java
│   └── ios/
│       └── ScreenGuard.m
│
└── www/
    └── screenguard.js
```

---

## Installation

### Cordova CLI

```
cordova plugin add cordova-screen-guard
```

or from local folder:

```
cordova plugin add ./cordova-screen-guard
```

### OutSystems (MABS)

1. Create a Mobile Plugin Module
2. Add the plugin files to the module
3. Set each file to "Copy to Target Directory"
4. Add the plugin ID inside Extensibility Configurations:

```
cordova-screen-guard
```

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

## Optional UI Protection (For iOS)

You can blur sensitive content:

```css
.blur-sensitive {
    filter: blur(16px);
    transition: 0.3s;
}
```

Or add a black overlay:

```css
#screenGuardOverlay {
    display: none;
    position: fixed;
    inset: 0;
    background: black;
    opacity: 0.9;
    z-index: 99999;
}
```

---

## Testing

### Android

* Attempt to take a screenshot
* Start a screen recording
* Open recent apps view

Screen capture should be fully blocked.

### iOS

* Take a screenshot (event should fire)
* Start recording (event should fire)
* Stop recording (event should fire)

---

## License

MIT License

---

## Support

If you encounter any issues, open an Issue or Pull Request on GitHub.

