<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp" %>
<c:set var="actionUrl">
    ${pageContext.request.contextPath}/business/account.do
</c:set>
<c:set var="tableName" value="account" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@include file="/include/head.jsp" %>
    <%@include file="/include/code/simple/view_ext.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js"></script>
    <script type="text/javascript">
        var id = '${ param.id}';
        var type = '${param.type}';
        window.onload = (function () {
        });
        function doMark2(){
            if(type=='exception'){
                ajaxJson(actionUrl+"?markException",jQuery.param({id:id,reason:$("#reason").val()},true),function(data){
                    slideMsgInfo(data.msg);
                    window.parent.$("#editDia").dialog("close");
                });
            }else if(type=='delete'){
                ajaxJson(actionUrl+"?markDelete",jQuery.param({id:id,reason:$("#reason").val()},true),function(data){
                    slideMsgInfo(data.msg);
                    window.parent.$("#editDia").dialog("close");
                });
            }
        }
    </script>
</head>
<body>
<div align="center">
    <form id="editForm">
        <input name="id" type="hidden" id="edit_id">
        <table align="center">
            <tr>
                <td style="padding: 5px">原因：</td>

            </tr>
            <tr>
                <td>
                    <textarea style="width: 400px" rows="10" id="reason" name="reason"></textarea>
                </td>
            </tr>
        </table>
        <a href="javascript:void(0);" class=" easyui-linkbutton" onclick="doMark2()">提交</a>
    </form>
</div>
</body>
</html>
