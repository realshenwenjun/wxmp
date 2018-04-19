<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/template/views/common/head.inc.jsp" %>

<jsp:include page="/WEB-INF/template/views/common/weuiTop.jsp"></jsp:include>

<body ontouchstart>


<header class='demos-header'>
    <h1 class="demos-title">登录</h1>
</header>


<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd">
            <label class="weui-label">手机号</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="userPhone" name="userPhone" type="tel" maxlength="11" placeholder="请输入手机号">
        </div>
    </div>
</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="password" id="password"  placeholder="请输入密码">
        </div>
    </div>
</div>




<div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">登录</a>
</div>

<div style="display:inline-flex;">
    <a href="regist.html">
        <label class="weui-agree">
              <span class="weui-agree__text">
                快速注册
              </span>
        </label>
    </a>
    <a href="forgetpassword.html">
        <label class="weui-agree" >
              <span class="weui-agree__text">
                找回密码
              </span>
        </label>
    </a>
</div>
<script src="<%=path%>/res/js/common/jquery.js"></script>
<script src="<%=path%>/res/js/common/common.js"></script>
<script src="<%=path%>/res/js/plugins/jqueryweiui/lib/fastclick.js"></script>

<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
<script src="<%=path%>/res/js/plugins/jqueryweiui/js/jquery-weui.js"></script>
<script src="<%=path%>/res/js/gather/auth/login.js"></script>
</body>
</html>


