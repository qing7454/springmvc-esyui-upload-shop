<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/setting.do
</c:set>
<c:set var="tableName" value="setting" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@include file="/include/head.jsp"%>
    <%@include file="/include/code/simple/view_ext.jsp" %>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js" ></script>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js" ></script>
    <script type="text/javascript" >
        var id='${id}';
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

            <td style="padding: 5px">同店疲劳期：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='tdplq'  />
            </td>

            <td style="padding: 5px">同SKU疲劳期：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='tskuplq'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">月最多购买件数：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='gmjs'  />
            </td>

            <td style="padding: 5px">月最多购买金额：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='gmje'  />
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
