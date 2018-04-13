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
            $.alert("", jqXHR.responseText);
        }
    });
}
function isEmpty(s){
    if(s == null || s == '' || s == undefined)
        return true
    return false
}