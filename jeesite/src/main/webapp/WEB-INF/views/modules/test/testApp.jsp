<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试App</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
	$(function(){
		test();
	});
	function test(){
		 
		 var userBroadband="13859309653";
		 var liveTime="2018-04-17 14:47:45";
		 var ipAddress="218.207.174.223";
		 var checkCode="6706F99808BF281A8751E5902BB4A083";
		 var datas ={"userBroadband":userBroadband,"liveTime":liveTime,"ipAddress":ipAddress,"checkCode":checkCode};
		 $.ajax({  
		    	type:"post",
		        url: "http://218.207.162.27:8081/nettest-web/a/test/nettestTest/findBandwidthTest",  
		        data:datas,
		        dataType: "json",
		        success: function(data) { 
		       	     console.log("8081"+JSON.stringify(data));

		        }
		  });  
		 
		 
		 $.ajax({  
		    	type:"post",
		        url: "http://112.50.251.31:8182/findBandwidthTest",  
		        data:datas,
		        dataType: "json",
		        success: function(data) { 
		        	console.log("8182"+JSON.stringify(data));

		        }
		  }); 
	}
	 
	</script>
</head>
<body>
	
</body>
</html>
