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

                    <td style="padding: 5px">商品ID：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='goodid'  />
                    </td>

 
                    <td style="padding: 5px">商品名称：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='goodname'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">价格：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='price'  />
                    </td>

 
                    <td style="padding: 5px">备注：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='mark'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">商家ID：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='shopid'  />
                    </td>

         </tr>

    </table>
</form>
</div>
</body>
</html>
