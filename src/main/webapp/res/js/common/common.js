/**
 * Created by shenwenjun on 2018/4/11.
 */
function ajax(param){
    $.ajax({
        url:param.url,
        type:param.type,
        async: param.async,
        dataType:param.dataType,
        data:param.data,
        success:param.success,
        error:function(jqXHR, textStatus, errorThrown){
            try{
                var r = $.parseJSON(jqXHR.responseText);
                $.alert("", r.msg);
                $.hideLoading();
            }catch(e){
                $.alert("", jqXHR.responseText);
            }

        }
    });
}
function isEmpty(s){
    if(s == null || s == '' || s == undefined)
        return true
    return false
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    var q = window.location.pathname.substr(1).match(reg_rewrite);
    if(r != null){
        return unescape(r[2]);
    }else if(q != null){
        return unescape(q[2]);
    }else{
        return null;
    }
}

function setCookie(name,value)
{
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + 24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
