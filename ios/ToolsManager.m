//
//  ToolsManager.m
//  reactNativeMoudleTest
//
//

#import "ToolsManager.h"
#import <React/RCTUtils.h>
#import <UIKit/UIKit.h>
@interface ToolsManager() <UIDocumentPickerDelegate, UIAdaptivePresentationControllerDelegate>
@property (nonatomic,strong)RCTResponseSenderBlock callback;
@end
@implementation ToolsManager

//+ (NSString *)moduleName {
//  return @"ToolsManager"
//}
//+ (void)load{
//  RCTRegisterModule(self);
//}
// 以上代码等价于
//RCT_EXPORT_MODULE("ToolsManager");
// 以上代码等价于
RCT_EXPORT_MODULE();
RCT_EXPORT_METHOD(test){
  NSLog(@"testLog");
}
RCT_EXPORT_METHOD(testSimple:(NSString *)name number:(float)number flag:(BOOL)flag){
  NSLog(@"testLog %@",name);
  NSLog(@"testLog %f",number);
  NSLog(@"testLog %d",flag);
}
RCT_EXPORT_METHOD(testComplex:(NSArray *)array object:(NSDictionary *)dict){
  NSLog(@"testLog %@",array);
  NSLog(@"testLog %@",dict);
}
RCT_EXPORT_METHOD(testCallback:(RCTResponseSenderBlock)callback){
  NSNumber *num  = @1;
  NSString *str = @"string";
  NSArray *arr = @[@"arr1",@"arr2"];
  NSDictionary *dict = [NSDictionary dictionaryWithObjectsAndKeys:@"v1",@"k1",@"v2",@"k2", nil];
  callback(@[num,str,arr,dict]);
}
RCT_REMAP_METHOD(testPromise,testPromise:(BOOL)flag withResolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject){
  if(flag){
    resolve(@"result");
  }
  else{
    NSDictionary *error = [NSDictionary dictionaryWithObjectsAndKeys:@"message",@"error", nil];
    resolve(error);
  }
}
RCT_EXPORT_METHOD(testToFile:(RCTResponseSenderBlock)callback){
  // 存起来，后面使用
  self.callback = callback;
  UIViewController *rootViewController = RCTPresentedViewController();
  // 由于和 react-native 和 javascript 通信的不是在 主线程中。
  dispatch_async(dispatch_get_main_queue(), ^{
       UIDocumentPickerViewController *documentPicker = [[UIDocumentPickerViewController alloc] initWithDocumentTypes:@[@"com.adobe.pdf"] inMode:UIDocumentPickerModeOpen];
       documentPicker.delegate = self;
       documentPicker.modalPresentationStyle = UIModalPresentationFormSheet;
       [rootViewController presentViewController:documentPicker animated:YES completion:nil];
     });
}
- (NSDictionary *)constantsToExport{
  return @{
    @"SUCCESS":@1,
    @"ERROR":@2,
  };
}
@end
