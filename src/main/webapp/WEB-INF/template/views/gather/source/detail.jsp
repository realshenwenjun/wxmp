<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/template/views/common/head.inc.jsp" %>

<jsp:include page="/WEB-INF/template/views/common/weuiTop.jsp"></jsp:include>

<body ontouchstart>


<header class='demos-header'>
    <h1 class="demos-title">${source.name}</h1>
</header>

<div class="weui-form-preview">
    <div class="weui-form-preview__hd">
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">付款金额</label>
            <em class="weui-form-preview__value">¥2400.00</em>
        </div>
    </div>
    <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">房屋数量</label>
            <span class="weui-form-preview__value">${source.houseCount}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">地址</label>
            <span class="weui-form-preview__value">${source.address}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">描述</label>
            <span class="weui-form-preview__value">${source.desc}</span>
        </div>
    </div>
    <div class="weui-form-preview__bd">
        <div class="weui-uploader">
            <div class="weui-uploader__hd">
                <%--<p class="weui-uploader__title">图片</p>--%>
                <label class="weui-form-preview__label">图片</label>
                <div class="weui-uploader__info"></div>
            </div>
            <input type="hidden" id="picsUrl" value="${source.picsUrl}">
            <div class="weui-uploader__bd">
                <ul class="weui-uploader__files" id="picsUrlContent">
                    <li class="weui-uploader__file" style="background-image:url(/res/js/plugins/jqueryweiui/images/pic_160.png)"></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="weui-form-preview">
    <div class="weui-form-preview__ft">
        <a class="weui-form-preview__btn  weui-btn_default" href="source.html">返回</a>
        <a class="weui-form-preview__btn weui-btn_default" href="javascript:qrcode('${source.qrcode}')">二维码</a>
        <a class="weui-form-preview__btn weui-btn weui-btn_default" href="javascript:del('${source.id}')">删除</a>
    </div>
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
<script src="<%=path%>/res/js/plugins/jqueryweiui/js/swiper.js"></script>
<script src="<%=path%>/res/js/gather/source/detail.js"></script>

</body>
</html>


