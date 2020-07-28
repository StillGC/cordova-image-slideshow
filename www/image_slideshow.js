var exec=require('cordova/exec');
var image_slideshow={
show:function(content,type,isOpen){
exec(isOpen,null,"Image_Slideshow","show",[content,type]);
},
showBase64:function(content,type,isOpen){
exec(isOpen,null,"Image_Slideshow","showBase64",[content,type]);
}
};
module.exports=image_slideshow;