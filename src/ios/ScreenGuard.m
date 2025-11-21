#import <Cordova/CDV.h>
#import <UIKit/UIKit.h>

@interface ScreenGuard : CDVPlugin
@end

@implementation ScreenGuard

- (void)pluginInitialize {
    [self startObservers];
}

- (void)startObservers {
    // Screenshot detection
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(onScreenshot)
                                                 name:UIApplicationUserDidTakeScreenshotNotification
                                               object:nil];

    // Screen recording detection
    [[UIScreen mainScreen] addObserver:self
                            forKeyPath:@"captured"
                               options:NSKeyValueObservingOptionNew
                               context:nil];
}

- (void)onScreenshot {
    [self sendEvent:@"screenshot"];
}

- (void)observeValueForKeyPath:(NSString *)keyPath
                      ofObject:(id)object
                        change:(NSDictionary<NSKeyValueChangeKey,id> *)change
                       context:(void *)context {

    BOOL isCaptured = [UIScreen mainScreen].isCaptured;

    if (isCaptured) {
        [self sendEvent:@"recording_started"];
    } else {
        [self sendEvent:@"recording_stopped"];
    }
}

- (void)sendEvent:(NSString *)eventName {
    NSString *js = [NSString stringWithFormat:@"window.SCREEN_GUARD_CB && window.SCREEN_GUARD_CB('%@');", eventName];
    [self.commandDelegate evalJs:js];
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    @try {
        [[UIScreen mainScreen] removeObserver:self forKeyPath:@"captured"];
    } @catch (NSException *exception) {
        // ignore
    }
}

@end
