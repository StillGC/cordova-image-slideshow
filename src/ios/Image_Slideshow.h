//
//  ImageTest.h
//  ImagesSlideIos
//
//  Created by 张冲 on 16/9/7.
//
//

#ifndef ImageTest_h
#define ImageTest_h
#import <Cordova/CDV.h>
#import <UIKit/UIKit.h>
#import <Cordova/CDVViewController.h>
#import "PhotoBrowerViewController.h"

@interface Image_Slideshow : CDVPlugin <MWPhotoBrowserDelegate>
@property (nonatomic, strong) NSMutableArray *photos;
@property (nonatomic, strong) NSMutableArray *thumbs;
-(void)show:(CDVInvokedUrlCommand*)command;
@end


#endif /* ImageTest_h */
