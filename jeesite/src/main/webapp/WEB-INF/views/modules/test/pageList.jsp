<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>bootstrap分页测试</title>	
	<%@ include file="/WEB-INF/views/include/bootstrap.jsp"%>

	
<script type="text/javascript">



// 业务图标
$(function(){
	 
	
	
    initData(); 
   
	//展示图表	
	showTable();
	//绑定查询事件
	$(document).on('click', '#btnSubmit', function() {

		showTable();
	});



	
	
});






//设置时间
function initData(){
	    var date = new Date();
		var year = date.getFullYear()+"";
		var month_temp = date.getMonth()+1;
		var month = month_temp<10?("0"+month_temp):(""+month_temp);
		var day = date.getDate()<10?("0"+date.getDate()):(""+date.getDate());
		var hour = date.getHours()<10?("0"+date.getHours()):(""+date.getHours());
		var minute = date.getMinutes()<10?("0"+date.getMinutes()):(""+date.getMinutes());
	    var second = date.getSeconds()<10?("0"+date.getSeconds()):(""+date.getSeconds());
	    $('#endTime').val(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second);
	    var n = 7;
	    var d = new Date();
	    var yearl = d.getFullYear();
        var monl=d.getMonth()+1;
	    var dayl=d.getDate();
        if(dayl <= n){
          if(monl>1) {
             monl=monl-1;
          }
          else {
             yearl = yearl-1;
             monl = 12;
          }
        }
        d.setDate(d.getDate()-n);
        yearl = d.getFullYear();           
        monl=d.getMonth()+1;
        dayl=d.getDate();
	$('#beginTime').val(yearl+"-"+(monl<10?('0'+monl):monl)+"-"+(dayl<10?('0'+dayl):dayl)+" "+"00"+":"+"00"+":"+"00");

}











//表单的数据加载
function showTable(){
	var columns=[];
	columns = [
        {title:"序号",field:"createtime",align: "center", valign:"middle",formatter: function (value, row, index) {  
    	    var pageSize=$('#detailTable').bootstrapTable('getOptions').pageSize;
            var pageNumber=$('#detailTable').bootstrapTable('getOptions').pageNumber;  
            return pageSize * (pageNumber - 1) + index + 1;   
        } },
        {title:"IP",field:"ip",align: "center", valign:"middle"},
		{title:"虚拟机名称",field:"virtualName",align: "center", valign:"middle" },
		{title:"工单单号",field:"constructionNumber",align: "center", valign:"middle"}
	];
			
	$("#detailTable").bootstrapTable('destroy').bootstrapTable({
		height:$(document).height()- $("#searchForm").height()-$('#header', parent.document).click().height(),
		url: "${ctx}/test/pageMybatis/getNettestWebTest", 
		method: "post",
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		pagination: true, //分页
		clickToSelect: true,  
		sidePagination: "server", //服务端处理分页
		queryParams: queryParams,
		uniqueId: "id",
		pageSize: 20,			//每页的记录行数（*）
		pageList: [20,50,100,200],	//可供选择的每页的行数（*）
		queryParamsType: "limit",	
		paginationPreText: "上一页",
		paginationNextText: "下一页",
		columns: columns
	});	

	
}

//加载参数
function queryParams(params){
	var pageNo=parseInt(params.offset)/parseInt(params.limit)+1;
	
	return $("#searchForm").serialize()+"&pageSize="+params.limit+"&pageNo="+pageNo;
}







		  
</script>
</head>
<body>

<form:form id="searchForm" action="" class="breadcrumb form-search" method="POST">

		<sys:message content="${message}"/>
		<ul class="ul-form">
 	

			<li ><label>开始时间：</label>
		         <input name="beginTime" id="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			
			<li><label>结束时间：</label>
                <input name="endTime" id="endTime"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"	
		        onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				</span>
			</li>
			
			<li class="btns">
		   &nbsp;<input id="btnSubmit" style="margin-left: 20px;" class="btn btn-primary" type="button"  value="查询"/>

		   </li>
		</ul>
		
		
	</form:form>



	
<div class="container-fluid" style="width:95%" >
	 
	  <div class=row-fluid> 

			<table  id="detailTable" ></table>	
			</div>
		  
	   </div>

</div>


</body>
</html>
