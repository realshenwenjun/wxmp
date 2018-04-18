<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/template/views/common/head.inc.jsp" %>

<div class="weui-tabbar" id="bottomTip">
    <a href="/gather/index/index.html" class="weui-tabbar__item weui-bar__item--on" tab="1">
        <span class="weui-badge" style="position: absolute;top: -.4em;right: 1em;">8</span>
        <div class="weui-tabbar__icon">
            <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_button.png" alt="">
        </div>
        <p class="weui-tabbar__label">微信</p>
    </a>
    <a href="/gather/index/index.html" class="weui-tabbar__item" tab="2">
        <div class="weui-tabbar__icon">
            <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_msg.png" alt="">
        </div>
        <p class="weui-tabbar__label">通讯录</p>
    </a>
    <a href="/gather/index/index.html" class="weui-tabbar__item" tab="3">
        <div class="weui-tabbar__icon">
            <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_article.png" alt="">
        </div>
        <p class="weui-tabbar__label">发现</p>
    </a>
    <a href="/gather/index/index.html" class="weui-tabbar__item" tab="4">
        <div class="weui-tabbar__icon">
            <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-tabbar__label">我</p>
    </a>
</div>
</div>
<script src="<%=path%>/res/js/common/jquery.js"></script>
<script src="<%=path%>/res/js/common/common.js"></script>
<script src="<%=path%>/res/js/plugins/jqueryweiui/lib/fastclick.js"></script>

<script>
    $(function() {
        FastClick.attach(document.body);
        var tab = getCookie("tab")
        if (!isEmpty(tab)){
            $("#bottomTip").find("a").removeClass("weui-bar__item--on")
            $("#bottomTip").find("a[tab='"+tab+"']").addClass("weui-bar__item--on")
        }
        $("#bottomTip").find("a").unbind().on("click",function(){
            var tab = $(this).attr("tab")
            setCookie("tab",tab)
            $("#bottomTip").find("a").removeClass("weui-bar__item--on")
            $("#bottomTip").find("a[tab='"+tab+"']").addClass("weui-bar__item--on")
        })
    });
</script>
<script src="<%=path%>/res/js/plugins/jqueryweiui/js/jquery-weui.js"></script>