<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/task.do
</c:set>
<c:set var="tableName" value="task" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@include file="/include/head.jsp"%>
    <%@include file="/include/code/simple/view_ext.jsp" %>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js" ></script>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js" ></script>
    <script type="text/javascript" >
        var ids='${ param.ids}';
        window.onload=(function(){
            $('#taskAssign').combobox({
                url:'${pageContext.request.contextPath}/sys/user.do?allusers2',
                valueField:'cKey',
                textField:'cValue'
            });
        });
        function submitAssign() {
            var userId = $("#taskAssign").combobox('getValue');
            var userName = $("#taskAssign").combobox("getText");
            ajaxJson(actionUrl + "?assignTask", {taskIds:ids,userId:userId,userName:userName}, function (data) {
                if (data.success) {
                }
                window.parent.$("#editDia").dialog("close");
            });
        }
    </script>
</head>
<body >
<div align="center">
<form id="editForm">
    <input name="id" type="hidden" id="edit_id">
    <table align="center" style="margin-top: 32px;margin-bottom: 30px">
        <tr>
            <td style="padding: 5px">选择任务接收人：</td>
            <td style="padding: 5px">
                <input class='easyui-combobox'  id="taskAssign"  name='taskAssign'  />
            </td>
         </tr>
    </table>
    <a href="javascript:void(0);"  class=" easyui-linkbutton" iconCls="icon-edit" onclick="submitAssign()">提交</a>
</form>
</div>
</body>
</html>
