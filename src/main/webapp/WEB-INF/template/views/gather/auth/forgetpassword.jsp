<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/template/views/common/head.inc.jsp" %>

<jsp:include page="/WEB-INF/template/views/common/weuiTop.jsp"></jsp:include>

<body ontouchstart>


<header class='demos-header'>
    <h1 class="demos-title">找回密码</h1>
</header>


<div class="weui-cells weui-cells_form">
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">手机号</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="userPhone" name="userPhone" type="tel" maxlength="11" placeholder="请输入手机号">
        </div>
        <div class="weui-cell__ft">
            <button class="weui-vcode-btn" id="verificationCode" start="${verificationCodeStart}" <c:choose><c:when test="${verificationCodeStart < 60}">style="color: grey" ></c:when><c:otherwise>>获取验证码</c:otherwise></c:choose></button>
        </div>
    </div>
</div>

<div class="weui-cells weui-cells_form">
    <div class="weui-cell ">
        <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" name="verificationCode"  placeholder="请输入验证码">
        </div>
    </div>
</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">新密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="password" id="password"  placeholder="请输入密码">
        </div>
    </div>
</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">确认密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="password" id="password2"  placeholder="再次输入确认密码">
        </div>
    </div>
</div>

<div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
</div>
<div style="display:inline-flex;">
    <a href="login.html">
        <label class="weui-agree">
              <span class="weui-agree__text">
                手机号登录
              </span>
        </label>
    </a>
    <a href="regist.html">
        <label class="weui-agree" >
              <span class="weui-agree__text">
                快速注册
              </span>
        </label>
    </a>
</div>

<jsp:include page="/WEB-INF/template/views/common/weuiBottom.jsp"></jsp:include>
<script src="<%=path%>/res/js/gather/auth/forgetpassword.js"></script>
</body>
</html>


