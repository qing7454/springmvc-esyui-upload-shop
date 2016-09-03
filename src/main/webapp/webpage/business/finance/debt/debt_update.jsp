<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/finance/debt.do
</c:set>
<c:set var="tableName" value="debt" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@include file="/include/head.jsp"%>
    <%@include file="/include/code/simple/view_ext.jsp" %>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js" ></script>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js" ></script>
    <script type="text/javascript" >
        var id='${ param.id}';
        window.onload=(function(){
            loadFormData(id);
        });

    </script>
</head>
<body >
<div align="center">
<form id="editForm">
    <input name="id" type="hidden" id="edit_id">
    <table align="center">
        <tr>

            <td style="padding: 5px">商家名称：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='shopName'  />
            </td>

            <td style="padding: 5px">欠货款总额：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='payment'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">欠佣金总额：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='commission'  />
            </td>

            <td style="padding: 5px">总计：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='ammount'  />
            </td>
         </tr>
         <tr>
        </tr>
        <tr align="center">
            <td colspan="4"><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="saveData()">提交</a></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
