<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/template/views/common/head.inc.jsp" %>

<jsp:include page="/WEB-INF/template/views/common/weuiTop.jsp"></jsp:include>

<body ontouchstart>

<header class='demos-header'>
  <h1 class="demos-title">管理员</h1>
  <p class='demos-sub-title'>管理端</p>
</header>

<div class="weui-grids">
  <a href="<%=path%>/gather/source/source.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_button.png" alt="">
    </div>
    <p class="weui-grid__label">
      出租
    </p>
  </a>
  <a href="cell.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_cell.png" alt="">
    </div>
    <p class="weui-grid__label">
      租户
    </p>
  </a>
  <a href="form.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_cell.png" alt="">
    </div>
    <p class="weui-grid__label">
      收租
    </p>
  </a>
  <a href="flex.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_cell.png" alt="">
    </div>
    <p class="weui-grid__label">
      租房
    </p>
  </a>
  <a href="toast.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_toast.png" alt="">
    </div>
    <p class="weui-grid__label">
      交租
    </p>
  </a>
  <a href="dialog.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_dialog.png" alt="">
    </div>
    <p class="weui-grid__label">
      消息
    </p>
  </a>
  <a href="progress.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_progress.png" alt="">
    </div>
    <p class="weui-grid__label">
      历史
    </p>
  </a>
  <a href="msg.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_msg.png" alt="">
    </div>
    <p class="weui-grid__label">
      Msg
    </p>
  </a>
  <a href="article.html" class="weui-grid js_grid">
    <div class="weui-grid__icon">
      <img src="<%=path%>/res/js/plugins/jqueryweiui/images/icon_nav_article.png" alt="">
    </div>
    <p class="weui-grid__label">
      设置
    </p>
  </a>
</div>


<jsp:include page="/WEB-INF/template/views/common/weuiBottom.jsp"></jsp:include>
<script src="<%=path%>/res/js/gather/index.js"></script>

</body>

</html>
