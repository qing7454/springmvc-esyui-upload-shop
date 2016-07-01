<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/include/include.jsp" %>
<c:set var="actionUrl" scope="request">
    ${pageContext.request.contextPath}/business/account.do
</c:set>
<c:set var="tableName" value="account" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title>账号信息表</title>
    <%@include file="/include/head.jsp" %>
    <%@include file="/include/code/simple/view_ext.jsp" %>
    <style type="text/css">
        select {
            width: 135px;
        }

        fieldset dl dd {
            float: left;
            margin-left: 20px;
        }

        fieldset pre {
            width: 100%;
            height: 400px;
            overflow-y: scroll;
            overflow-x: hidden;
        }

        input {
            width: 172px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webpage/business/account/area.js"></script>
    <script type="text/javascript">
        var context = '${ pageContext.request.contextPath}';
        window.onload = (function () {
            getDataList();
            //设置省份数据
            setProvince();
            //设置背景颜色
            setBgColor();
//            setArea();

            $("#dataTable").datagrid({
                onDblClickCell: function (rowid, rowdata) {
                    showDetailByDbClick(rowid)
                }
            });
        });//初始化

        //打开更新对话框
        function showUpdateDia() {
            var row = $("#dataTable").datagrid("getChecked");
            if (row.length == 0) {
                alertMsg("请选择条目");
                return false;
            }
            if (row.length > 1) {
                alertMsg("只能选择一条!");
                return false;
            }
            if (!checkDataState(row[0])) {
                alertMsg("当前数据不可操作！");
                return false;
            }
            $("#editFrame").attr("src", "${pageContext.request.contextPath}/webpage/business/account/account_update.jsp?id=" + row[0].id);
            $("#editDia").dialog("setTitle", "更新数据");
            openEasyuiDialog("editDia");
        }
        //打开新增对话框
        function showAddDia() {
            $("#editFrame").attr("src", "${pageContext.request.contextPath}/webpage/business/account/account_update.jsp");
            $("#editDia").dialog("setTitle", "新增数据");
            openEasyuiDialog("editDia");
        }

        //查看详情
        function showDetail() {
            var row = $("#dataTable").datagrid("getChecked");
            if (row.length == 0) {
                alertMsg("请选择条目");
                return false;
            }
            if (row.length > 1) {
                alertMsg("只能选择一条!");
                return false;
            }
            $("#editFrame").attr("src", "${pageContext.request.contextPath}/webpage/business/account/account_detail.jsp?id=" + row[0].id);
            $("#editDia").dialog("setTitle", "数据详情");
            openEasyuiDialog("editDia");
        }

        //双击查看详情
        function showDetailByDbClick(rowid) {
            var row = $("#dataTable").datagrid("getChecked");
            if (row.length == 0) {
                alertMsg("请选择条目");
                return false;
            }
            if (row.length > 1) {
                alertMsg("只能选择一条!");
                return false;
            }
            $("#editFrame").attr("src", "${pageContext.request.contextPath}/webpage/business/account/account_detail.jsp?id=" + row[rowid].id);
            $("#editDia").dialog("setTitle", "数据详情");
            openEasyuiDialog("editDia");
        }

        //操作按钮
        function buttonCz(value, row, index) {
            var button = "";
            <pro:authFilter buttonCode="del" actionUrl="${_modulesLink}" >
            button = "<a href='javascript:void(0);' onclick=\"deleteData('" + row.id + "')\">[删除]</a>";
            </pro:authFilter>
            return button;
        }
        function deleteData(id){
            $("#editFrame").attr("src","${pageContext.request.contextPath}/webpage/business/account/account_reason.jsp?type=delete&id="+id);
            $("#editDia").dialog("setTitle","数据详情");
            openEasyuiDialog("editDia");
        }

        function setArea() {
            var procince = $("#selProvince").val();
            var city = $("#selCity").val();
            var area = procince + "-" + city;
            $("#area").val(area);
        }
        function removeException(){
            var rows=$("#dataTable").datagrid("getChecked");
            if(rows.length==0){
                alertMsg("请选择条目");
                return false;
            }
            if (rows.length > 1) {
                alertMsg("只能选择一条!");
                return false;
            }
            jQuery.messager.confirm("提示","确定解除选中的记录？",function(r){
                JSON.stringify();
                if(r==true){
                    var ids=new Array();
                    for(var i=0;i<rows.length;i++){
                        ids.push(rows[i].id);
                    }
                    ajaxJson(actionUrl+"?removeException",jQuery.param({id:ids[0]},true),function(data){
                        slideMsgInfo(data.msg);
                        getDataList();
                    });
                }
            });
        }

    </script>
</head>
<body class="easyui-layout" title="账号信息表" align="center" style="width: 900px;height: 650px" fit="true">
<div data-options="region:'north'" align="center" style="width: 100%;height: 150px;overflow: auto">
    <form id="searchForm" method="post">
        <%--预定义条件--%>
        <c:forEach items="${_conditions}" var="_condition">
            <c:forEach items="${_condition.value}" var="_val">
                <input type="hidden" name="${_condition.key}" value="${_val}"/>
            </c:forEach>
        </c:forEach>
        <input type="hidden" name="pageNum" id="pageNum" value="1"/>
        <input type="hidden" name="_tableName" id="_tableName" value="account"/>
        <input type="hidden" id="_order" name="orders"/>
            <%--异常账号--%>
        <input type="hidden" id="accountstate" name="accountstate" value="02"/>
        <table width="100%">
            <tr height="20">
                <td class="head_search" colspan="20" style="text-align:left;height:20px;padding:10px 0 0 0;">
                    &nbsp;&nbsp;&nbsp;&nbsp;<b class="head_font" style="font-size:13px">数据查询</b>
                </td>
            </tr>
            <tr height="30">
                <td width="10%" align="right">账号：</td>
                <td><input class='easyui-validatebox' style="width:190px" name='account'/></td>
                <td width="10%" align="right">邮箱：</td>
                <td><input class='easyui-validatebox' style="width:190px" name='email'/></td>
                <td width="10%" align="right">手机号：</td>
                <td><input class='easyui-validatebox' style="width:190px" name='phone'/></td>
                <%--<td  width="10%" align="right">地区：</td>--%>
                <%--<td>--%>
                <%--<select style="width: 90px;" id="selProvince" onchange="provinceChange(),setArea();"></select>&nbsp;--%>
                <%--<select style="width: 90px;" id="selCity" onchange="setArea()"></select>--%>
                <%--<input name="area" id="area" type="hidden"></td>--%>
            </tr>
            <tr align="center">
                <td colspan="6" style="border-bottom:1px solid #d8d8d8;padding:5px 0px 10px 0;height:25px;">
                    <a href="javascript:void(0);" class=" easyui-linkbutton" onclick="getDataList()">查询</a>
                    &nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" onclick="resetForm()">重置</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div data-options="region:'center'" style="height:500px;margin:0 0 0 0;" align="center">
    <table width="100%">
        <tr height="20">
            <td class="head_search" colspan="20" style="text-align:left;padding:0 0 10px 0;">
                &nbsp;&nbsp;&nbsp;&nbsp;<b class="head_font" style="font-size:13px">数据列表</b>
            </td>
        </tr>
    </table>
    <table id="dataTable" class="easyui-datagrid" align="center"
           data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
        <thead data-options="frozen:true">
        <tr>
            <th field="id" data-options="checkbox:true"></th>
        </tr>
        </thead>
        <thead>
        <tr>
            <th field='account' data-options="width:100" sortable="true">账号</th>
            <th field='passwordLogin' data-options="width:100" sortable="true">登录密码</th>
            <th field='passwordPay' data-options="width:100" sortable="true">支付密码</th>
            <th field='phone' data-options="width:100" sortable="true">手机号</th>
            <th field='email' data-options="width:100" sortable="true">邮箱</th>
            <th field='passwordEmail' data-options="width:100" sortable="true">邮箱密码</th>
            <th field='area' data-options="width:100" sortable="true">地区</th>
            <th field='operator' data-options="width:100" sortable="true">运营商</th>
            <th field='level' data-options="width:100" sortable="true">账号等级</th>
            <%--<th field='idcard' data-options="width:100"  sortable="true">身份账号</th>--%>
            <%--<th field='name' data-options="width:100"  sortable="true">姓名</th>--%>
            <th field='exception' data-options="width:100"  sortable="true">异常原因</th>
            <%--<th field='deletereason' data-options="width:100"  sortable="true">删除原因</th>--%>
            <%--<th field='registerdate' sortable="true" data-options="width:100"  >注册日期</th>--%>
            <%--<th field='accountstate' data-options="width:100"  sortable="true">账号状态</th>--%>
            <th field="cz" data-options="formatter: function(value,row,index){ return buttonCz(value,row,index);}">操作</th>
        </tr>
        </thead>
    </table>
</div>
<div data-options="region:'south'">
    <div class="easyui-panel" style="height: 38px;">
        <div id="pager" class="easyui-pagination"
             data-options="showRefresh:false,showPageList:false,total:0,onSelectPage: function(pageNumber, pageSize){ $('#pageNum').val(pageNumber); getDataList();}"></div>
    </div>
</div>
<div id="tools" style="padding: 5px">
    <pro:authFilter buttonCode="detail" actionUrl="${_modulesLink}" >
        <a href="javascript:void(0);"  class=" easyui-linkbutton" iconCls="icon-edit" onclick="removeException()">解除异常</a>
    </pro:authFilter>
    <%@include file="/include/code/business_button_ext.jsp" %>
</div>
<%@include file="/include/code/business_dialog.jsp" %>
<%@include file="/include/code/business_footer_ext.jsp" %>
</body>
</html>
