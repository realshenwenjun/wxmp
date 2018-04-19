/**
 * Created by shenwenjun on 2018/4/19.
 */
var picsUrl = $("#picsUrl").val()
if(!isEmpty(picsUrl)){
    var arr = picsUrl.split(";")
    if(arr.length > 0){
        $("#picsUrlContent").html("")
        var pics = []
        for(var i=0;i<arr.length;i++){
            if(!isEmpty(arr[i])){
                $("#picsUrlContent").append('<li order="'+i+'" class="weui-uploader__file" style="background-image:url('+arr[i]+')"></li>')
                pics.push(arr[i])
            }

        }

        $("#picsUrlContent").find("li").unbind().on("click",function(){
            var order = $(this).attr("order")*1
            var picsOrder = []
            for(var i = order;i<pics.length;i++){
                picsOrder.push(pics[i])
            }
            for(var i = 0;i<order;i++){
                picsOrder.push(pics[i])
            }
            var pb1 = $.photoBrowser({
                items: picsOrder,

                onSlideChange: function(index) {
                    console.log(this, index);
                },

                onOpen: function() {
                    console.log("onOpen", this);
                },
                onClose: function() {
                    $(".weui-photo-browser-modal").remove()
                }
            });
            pb1.open(order);
        })
    }
}

function del(id){
    $.confirm("", "确认删除?", function() {
        ajax({
            url:path + '/gather/source/del',
            type:'POST',
            async: false,
            dataType:'json',
            data:{id:id},
            success:function(data){
                if(data.success){
                    $.toast("删除成功", function() {
                        window.location.href = path + '/gather/source/source.html';
                    });
                }else{
                    $.toast(data.msg, "text");
                }
            }
        });
    }, function() {
        //取消操作
    });

}

function qrcode(qrcode){
    $.showLoading();

    var pb1 = $.photoBrowser({
        items: [qrcode],

        onSlideChange: function(index) {
            console.log(this, index);
        },

        onOpen: function() {
            console.log("onOpen", this);
        },
        onClose: function() {
            $(".weui-photo-browser-modal").remove()
        }
    });
    pb1.open();
    $.hideLoading();
}