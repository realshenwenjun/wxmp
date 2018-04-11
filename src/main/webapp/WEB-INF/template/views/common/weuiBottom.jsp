<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/template/views/common/head.inc.jsp" %>
<script src="<%=path%>/res/js/common/jquery.js"></script>
<script src="<%=path%>/res/js/common/common.js"></script>
<script src="<%=path%>/res/js/plugins/jqueryweiui/lib/fastclick.js"></script>

<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
<script src="<%=path%>/res/js/plugins/jqueryweiui/js/jquery-weui.js"></script>