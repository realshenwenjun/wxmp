<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/template/views/common/head.inc.jsp" %>

<jsp:include page="/WEB-INF/template/views/common/weuiTop.jsp"></jsp:include>

<body ontouchstart>


<header class='demos-header'>
    <h1 class="demos-title">添加房源</h1>
</header>

<div class="weui-cells__title">基本信息</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">名称</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" name="name" id="name" placeholder="请输入名称">
        </div>
    </div>
</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">地址</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" name="address" id="address" placeholder="请输入地址">
        </div>
    </div>
</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">房间数</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" name="houseCount" id="houseCount" pattern="[0-9]*"  placeholder="请输入房间数量">
        </div>
    </div>
</div>

<div class="weui-cells__title">描述</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <textarea class="weui-textarea" name="desc" id="desc" maxlength="250" placeholder="请输入描述" rows="3"></textarea>
            <div class="weui-textarea-counter"><span id="descCount">0</span>/250</div>
        </div>
    </div>
</div>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <div class="weui-uploader">
                <div class="weui-uploader__hd">
                    <p class="weui-uploader__title">图片上传</p>
                    <input type="hidden" id="finishedUploader">
                    <div class="weui-uploader__info" id="finishedUploaderTitle">0/10</div>
                </div>
                <div class="weui-uploader__bd">
                    <ul class="weui-uploader__files" id="uploaderFiles">
                        <%--<li class="weui-uploader__file" style="background-image:url(/res/js/plugins/jqueryweiui/images/pic_160.png)"></li>--%>
                        <%--<li class="weui-uploader__file" style="background-image:url(/res/js/plugins/jqueryweiui/images/pic_160.png)"></li>--%>
                        <%--<li class="weui-uploader__file" style="background-image:url(/res/js/plugins/jqueryweiui/images/pic_160.png)"></li>--%>
                        <%--<li class="weui-uploader__file weui-uploader__file_status" style="background-image:url(/res/js/plugins/jqueryweiui/images/pic_160.png)">--%>
                            <%--<span class="weui-badge" style="position: absolute;top: -.4em;right: 1em;">x</span>--%>
                            <%--<div class="weui-uploader__file-content">--%>
                                <%--<i class="weui-icon-warn">X</i>--%>
                            <%--</div>--%>
                        <%--</li>--%>
                        <%--<li class="weui-uploader__file weui-uploader__file_status" style="background-image:url(/res/js/plugins/jqueryweiui/images/pic_160.png)">
                            <div class="weui-uploader__file-content">50%</div>
                        </li>--%>
                    </ul>
                    <form id="uploaderForm">
                        <div class="weui-uploader__input-box">
                            <input id="uploaderInput" class="weui-uploader__input" name="file" type="file" accept="image/*" multiple="">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">保存</a>
    <a href="source.html" class="weui-btn weui-btn_default">取消</a>
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
<script src="<%=path%>/res/js/gather/source/add.js"></script>

</body>
</html>


