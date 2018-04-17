/**
 * Created by shenwenjun on 2018/4/17.
 */

$("#uploaderInput").unbind().on("change",function(){
    var id = new Date().getTime();
    $("#uploaderFiles").append(
        '<li class="weui-uploader__file weui-uploader__file_status" id="'+id+'_pic" style="background-image:url(/res/js/plugins/jqueryweiui/images/pic_160.png)">'+
        '<span class="weui-badge" onclick="remove('+id+')" style="position: absolute;top: -.4em;right: 0em;">x</span>'+
        '<div class="weui-uploader__file-content" id="'+id+'">0%</div>'+
        '</li>');
    var file = document.getElementById("uploaderInput").files[0];
    upload(file,id);
});
function remove(id){
    $("#"+id+"_pic").remove();
    $("#finishedUploader").val($("#finishedUploader").val()*1 - 1)
    $("#finishedUploaderTitle").html($("#finishedUploader").val()+"/10")
    if($("#finishedUploader").val()*1 < 10){
        $("#uploaderForm").css("display","block")
    }
}
//上传文件
function upload(file,id){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = callhandle;
    var fd = new FormData();

    fd.append("file", file);
    //监听事件
    xhr.upload.addEventListener("progress", uploadProgress, false);

    //发送文件和表单自定义参数
    xhr.open("POST", "/gather/resource/file",true);
    xhr.send(fd);
    function uploadProgress(evt){
        if (evt.lengthComputable) {
            //evt.loaded：文件上传的大小   evt.total：文件总的大小
            var percentComplete = Math.round((evt.loaded) * 100 / evt.total);
            //加载进度条，同时显示信息
            $("#"+id).html(percentComplete + '%')

        }
    }
    function callhandle()
    {
        if (xhr.readyState == 4)
        {
            if(xhr.status == 200)
            {
                var dzb = eval("(" + xhr.responseText +")");
                $("#"+id+"_pic").css("background-image","url("+dzb.obj+")");
                $("#uploaderInput").val("")
                $("#finishedUploader").val($("#finishedUploader").val()*1 + 1)
                $("#finishedUploaderTitle").html($("#finishedUploader").val()+"/10")
                if($("#finishedUploader").val()*1 == 10){
                    $("#uploaderForm").css("display","none")
                }
            }
        }
    }

}

$("#showTooltips").unbind().on("click",function(){
    var imgs = $("#uploaderFiles").find("li.weui-uploader__file")
    var picUrl = "";
    var picsUrl = "";
    $.each(imgs,function(i){
        var p = $(imgs[i]).css("background-image")
        p = p.replace('url("','').replace('")','');
        picsUrl = picsUrl + p + ";"
        if(i == 0)
            picUrl = p
    })
    ajax({
        url:path + '/gather/source/add',
        type:'POST',
        async: false,
        dataType:'json',
        data:{name:$("#name").val(),address:$("#address").val(),houseCount:$("#houseCount").val(),desc:$("#desc").val(),picUrl:picUrl,picsUrl:picsUrl},
        success:function(data){
            if(data.success){
                $.toast("保存成功", function() {

                });
            }else{
                $.toast(data.msg, "text");
            }
        }
    });
})