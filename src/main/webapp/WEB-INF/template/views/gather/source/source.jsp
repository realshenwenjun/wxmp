<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/template/views/common/head.inc.jsp" %>

<jsp:include page="/WEB-INF/template/views/common/weuiTop.jsp"></jsp:include>

<body ontouchstart>
<div class="page__pd">
    <div class="weui-search-bar weui-search-bar_focusing" id="searchBar">
        <a href="add.html" class="weui-search-bar__cancel-btn" id="" style="margin-left: 0;margin-right: 10px;" id="addButton">添加</a>
        <form class="weui-search-bar__form" action="<%=path%>/gather/source/source.html" method="post" id="searchForm">
            <div class="weui-search-bar__box" style="height: auto;">
                <i class="weui-icon-search"></i>
                <input type="search" class="weui-search-bar__input" name="name" id="searchInput" value="${form.name}" placeholder="搜索" required="">
                <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
            </div>
            <label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
                <i class="weui-icon-search"></i>
                <span>搜索</span>
            </label>
        </form>
        <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">搜索</a>
    </div>
    <div class="weui-panel weui-panel_access">
        <div class="weui-panel__bd">
            <c:forEach items="${sources}" var="source" varStatus="index">
                <a href="detail.html?id=${source.id}" class="weui-media-box weui-media-box_appmsg">
                    <div class="weui-media-box__hd">
                        <img class="weui-media-box__thumb" src="${source.picUrl}" alt="" height="50">
                        <span class="weui-badge" style="position: absolute;top: -.4em;right: -.4em;">8</span>
                    </div>
                    <div class="weui-media-box__bd">
                        <h4 class="weui-media-box__title">
                            ${source.name}
                            <span class="weui-media-box__title-after"><fmt:formatDate value="${ source.createTime }"  type="both" /></span>
                        </h4>
                        <p class="weui-media-box__desc">房屋数量：${source.houseCount}</p>
                        <p class="weui-media-box__desc">地址：${source.address}</p>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
<style>
    .weui-panel {
        margin: 0;
    }
    .weui-media-box {
        padding: 8px 15px;
    }
    .weui-panel__bd .weui-media-box__hd {
        width: 50px;
        height: 50px;
        line-height: 50px;
        position: relative;
    }
    .weui-media-box__desc {
        -webkit-line-clamp: 1;
    }
    .weui-media-box__title {
        margin-top: -4px;
    }
</style>
<jsp:include page="/WEB-INF/template/views/common/weuiBottom.jsp"></jsp:include>
<script src="<%=path%>/res/js/gather/source/source.js"></script>

<script>
</script>
</body>
</html>


