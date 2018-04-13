/**
 * 登录
 */
$("#showTooltips").on("click",function(){
    if($(this).hasClass('weui-btn_primary')){
        var userPhone = $("#userPhone").val()
        if(isEmpty(userPhone)){
            $.toast("手机号未填写", "text");
            return;
        }
        var password = $("#password").val()
        if(isEmpty(password)){
            $.toast("密码未填写", "text");
            return;
        }
        ajax({
            url:path + '/gather/auth/login',
            type:'POST',
            async: false,
            dataType:'json',
            data:{userPhone:userPhone,password:password},
            success:function(data){
                if(data.success){
                    $.toast("登录成功", function() {
                        window.location.href = data.obj;
                    });
                }else{
                    $.toast(data.msg, "text");
                }
            }
        });
    }
})
