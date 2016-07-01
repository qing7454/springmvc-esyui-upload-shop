<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/goods.do
</c:set>
<c:set var="tableName" value="goods" scope="request" />
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
        var shopid='${ param.shopid}';
        window.onload=(function(){
            if(shopid!=null&&shopid.length>0){
                $("#shopid").val(shopid);
            }
            if(id!=null&&id.length>0){
                loadFormData(id);
            }

        });

    </script>
</head>
<body >
<div align="center">
<form id="editForm">
    <input name="id" type="hidden" id="edit_id">
    <input name='shopid' id="shopid" type="hidden"/>
    <table align="center">
        <tr>

            <td style="padding: 5px">商品ID：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='goodid'  />
            </td>

            <td style="padding: 5px">商品名称：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='goodname'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">价格：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='price'  />
            </td>

            <td style="padding: 5px">备注：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='mark'  />
            </td>
         </tr>
         <%--<tr>--%>

            <%--<td style="padding: 5px">商家ID：</td>--%>
            <%--<td style="padding: 5px">--%>
                <%--<input class='easyui-validatebox'    name='shopid' id="shopid" />--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr align="center">
            <td colspan="4"><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="saveData()">提交</a></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
