/**
 * Created by shenwenjun on 2018/4/11.
 */

$("#registAgree").unbind().on("click",function(){
    if($(this).prop('checked')){
        $("#showTooltips").addClass("weui-btn_primary")
        $("#showTooltips").removeClass("weui-btn_disabled weui-btn_default")
    }else{
        $("#showTooltips").removeClass("weui-btn_primary")
        $("#showTooltips").addClass("weui-btn_disabled weui-btn_default")
    }
})
$("#showTooltips").on("click",function(){
    if($(this).hasClass('weui-btn_primary')){
        var userPhone = $("#userPhone").val()
        var verificationCode = $("input[name='verificationCode']").val()
        var password = $("#userPhone").val()
        ajax({
            url:path + '/gather/auth/regist',
            type:'POST',
            async: false,
            dataType:'json',
            data:{userPhone:userPhone,verificationCode:verificationCode,password:password},
            success:function(data){
                if(data.success){
                    $.toast("注册成功", function() {
                        alert("跳转到登录");
                    });
                }else{
                    $.alert(data.msg, data.code);
                }
            }
        });
    }
})

$("#verificationCode").unbind().on("click",function(){
    var userPhone = $("#userPhone").val()
    if(!isEmpty(userPhone)){
        ajax({
            url:path + '/gather/auth/send/sms',
            type:'POST',
            async: false,
            dataType:'json',
            data:{userPhone:userPhone},
            success:function(data){
                if(data.success){
                    $.toast("验证码已发送", function() {
                        console.log('close');
                    });
                }else{
                    $.alert(data.msg, data.code);
                }
            }
        });
    }
})
