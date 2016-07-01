<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/shop.do
</c:set>
<c:set var="tableName" value="shop" scope="request" />
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

            <td style="padding: 5px">商家名：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='name'  />
            </td>

            <td style="padding: 5px">商家账号：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='shopaccount'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">佣金：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='commission'  />
            </td>

            <td style="padding: 5px">刷单价格APP：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='sdApp'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">刷单价格PC：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='sdPc'  />
            </td>

            <td style="padding: 5px">收货且评价价格：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='shPj'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">收货价格：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='sh'  />
            </td>

            <td style="padding: 5px">合作时间：</td>
            <td style="padding: 5px">
                <input class='easyui-datebox'    name='hzdate'  data-options="formatter:myformatter,parser:myparser" />
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
