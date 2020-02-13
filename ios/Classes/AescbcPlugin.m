#import "AescbcPlugin.h"
#if __has_include(<aescbc/aescbc-Swift.h>)
#import <aescbc/aescbc-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "aescbc-Swift.h"
#endif

@implementation AescbcPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAescbcPlugin registerWithRegistrar:registrar];
}
@end
