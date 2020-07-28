//
//  ImageTest.m
//  ImagesSlideIos
//
//  Created by 张冲 on 16/9/7.
//
//

#import <Photos/Photos.h>
#import "Image_Slideshow.h"
#import <Foundation/Foundation.h>
#import "SDImageCache.h"
#import "MWCommon.h"

@interface Image_Slideshow()


@end

@implementation Image_Slideshow

-(void)show:(CDVInvokedUrlCommand*)command{
    // Do any additional setup after loading the view, typically from a nib.
    NSArray* urls = [command.arguments objectAtIndex:0];
    NSString* type = [command.arguments objectAtIndex:1];
    NSInteger* imageIndex=[type integerValue]-1;
    NSMutableArray *photos = [[NSMutableArray alloc] init];
    
    for (int i=0; i<=urls.count-1; i++){
        [photos addObject:[MWPhoto photoWithURL:[NSURL URLWithString:urls[i]]]];
    }
    NSLog(@"DEBUG = %@",urls[0]);
    //    [photos addObject:[MWPhoto photoWithURL:[NSURL URLWithString:@"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png"]]];
    //    [photos addObject:[MWPhoto photoWithURL:[NSURL URLWithString:@"http://farm4.static.flickr.com/3629/3339128908_7aecabc34b_b.jpg"]]];
    //    [photos addObject:[MWPhoto photoWithURL:[NSURL URLWithString:@"http://farm4.static.flickr.com/3364/3338617424_7ff836d55f_b.jpg"]]];
    //    [photos addObject:[MWPhoto photoWithURL:[NSURL URLWithString:@"http://farm4.static.flickr.com/3590/3329114220_5fbc5bc92b_b.jpg"]]];
    //    [photos addObject:[MWPhoto photoWithURL:[NSURL URLWithString:@"http://farm3.static.flickr.com/2449/4052876281_6e068ac860_b.jpg"]]];
    self.photos = photos;
    PhotoBrowerViewController *browser = [[PhotoBrowerViewController alloc] initWithDelegate:self];
    [browser setCurrentPhotoIndex:imageIndex];
    [self.viewController presentViewController:browser
                                      animated:YES
                                    completion:nil];
}

- (NSUInteger)numberOfPhotosInPhotoBrowser:(MWPhotoBrowser *)photoBrowser {
    return _photos.count;
}

- (id <MWPhoto>)photoBrowser:(MWPhotoBrowser *)photoBrowser photoAtIndex:(NSUInteger)index {
    if (index < _photos.count)
        return [_photos objectAtIndex:index];
    return nil;
}

@end
