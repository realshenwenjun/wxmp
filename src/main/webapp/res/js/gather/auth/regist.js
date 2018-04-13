/**
 * Created by shenwenjun on 2018/4/11.
 */
function timer(time){
    setTimeout(function(){
        if(time > 1){
            time--
            $("#verificationCode").text(time)
            $("#verificationCode").attr("disabled","disabled");
            $("#verificationCode").css("color","gray");
            timer(time)
        }else{
            $("#verificationCode").removeAttr("disabled");
            $("#verificationCode").css("color","");
            $("#verificationCode").text("获取验证码")
        }
    },1000);
}
var start = $("#verificationCode").attr("start");
if(!isEmpty(start)){
    timer(60 - start)
}
$("#registAgree").unbind().on("click",function(){
    if($(this).prop('checked')){
        $("#showTooltips").addClass("weui-btn_primary")
        $("#showTooltips").removeClass("weui-btn_disabled weui-btn_default")
    }else{
        $("#showTooltips").removeClass("weui-btn_primary")
        $("#showTooltips").addClass("weui-btn_disabled weui-btn_default")
    }
})
/**
 * 注册
 */
$("#showTooltips").on("click",function(){
    if($(this).hasClass('weui-btn_primary')){
        var userPhone = $("#userPhone").val()
        if(isEmpty(userPhone)){
            $.toast("手机号未填写", "text");
            return;
        }
        var verificationCode = $("input[name='verificationCode']").val()
        if(isEmpty(verificationCode)){
            $.toast("验证码未填写", "text");
            return;
        }
        var password = $("#password").val()
        if(isEmpty(password)){
            $.toast("密码未填写", "text");
            return;
        }
        var password2 = $("#password2").val()
        if(isEmpty(password2)){
            $.toast("确认密码未填写", "text");
            return;
        }
        if(password2 != password){
            $.toast("两次密码不相同", "text");
            return;
        }
        ajax({
            url:path + '/gather/auth/regist',
            type:'POST',
            async: false,
            dataType:'json',
            data:{userPhone:userPhone,verificationCode:verificationCode,password:password},
            success:function(data){
                if(data.success){
                    $.toast("注册成功", function() {
                        window.location.href = "login.html";
                    });
                }else{
                    $.toast(data.msg, "text");
                }
            }
        });
    }
})
/**
 * 发送验证码
 */
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

                    });

                    timer(60);
                }else{
                    $.toast(data.msg, "text");
                }
            }
        });
    }else{
        $.toast("手机号未填写", "text");
        return;
    }
})
