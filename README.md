# cordova-image-slideshow
多图浏览插件（Android版）

使用第三方包 photoview与glide

开发工程下执行以下命令导入本插件：

	$ ionic plugin add https://github.com/18502778916/cordova-image-slideshow.git

已安装插件查看：

	$ionic plugin list

执行以下命令删本插件：

	$ionic plugin rm org.km.plugins.image_slideshow

## 2 JS调用说明

imageIndex为图片设置起始图。从1开始。

image_slideshow.show(["url1","url2","url3",......],imageIndex);

image_slideshow.showBase64(["base64","base64","base64",......],imageIndex);

一张图显示同理
