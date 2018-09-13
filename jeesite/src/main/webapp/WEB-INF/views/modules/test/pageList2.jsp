<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>bootstrap分页测试</title>	
	<%@ include file="/WEB-INF/views/include/bootstrap.jsp"%>
<script type="text/javascript">
    $(function() {

    });

    function changeImg() {        
        var imgSrc = $("#imgObj");    
        var src = imgSrc.attr("src");        
        imgSrc.attr("src", chgUrl(src));
    }
    
    // 时间戳
    // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }

</script>
</head>
<body>
    <form action="http://192.168.17.20:8182/checkCode" method="post">
        请输入验证码：<input type="text" name="code" style="width: 200px;" /> <img id="imgObj" alt="验证码" style="width: 200px;height: 50px" 
            src="http://192.168.17.20:8182/getCode"><a href="#" onclick="changeImg()">换一张</a><br/> <input
            type="submit" value="提交" />
    </form>

</body>

</html>
