import Foundation
import UIKit

@objc(ScreenGuard) class ScreenGuard: CDVPlugin {

    var callbackId: String?

    @objc(start:)
    func start(command: CDVInvokedUrlCommand) {
        self.callbackId = command.callbackId

        NotificationCenter.default.addObserver(
            self,
            selector: #selector(screenshotTaken),
            name: UIApplication.userDidTakeScreenshotNotification,
            object: nil
        )

        let screen = UIScreen.main
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(screenCapturedChanged),
            name: UIScreen.capturedDidChangeNotification,
            object: nil
        )

        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK)
        self.commandDelegate!.send(pluginResult, callbackId: command.callbackId)
    }

    @objc func screenshotTaken() {
        sendEvent("screenshot")
    }

    @objc func screenCapturedChanged() {
        let captured = UIScreen.main.isCaptured
        sendEvent(captured ? "recording_started" : "recording_stopped")
    }

    func sendEvent(_ event: String) {
        guard let callbackId = self.callbackId else { return }
        let result = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: event)
        result?.setKeepCallbackAs(true)
        self.commandDelegate!.send(result, callbackId: callbackId)
    }

    @objc(stop:)
    func stop(command: CDVInvokedUrlCommand) {
        NotificationCenter.default.removeObserver(self)
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK)
        self.commandDelegate!.send(pluginResult, callbackId: command.callbackId)
    }
}
