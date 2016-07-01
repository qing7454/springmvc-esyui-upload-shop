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
                        <input class='easyui-validatebox'  disabled='disabled'  name='name'  />
                    </td>

 
                    <td style="padding: 5px">商家账号：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='shopaccount'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">货款：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='payment'  />
                    </td>

 
                    <td style="padding: 5px">佣金：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='commission'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">刷单价格APP：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='sdApp'  />
                    </td>

 
                    <td style="padding: 5px">刷单价格PC：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='sdPc'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">收货且评价价格：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='shPj'  />
                    </td>

 
                    <td style="padding: 5px">收货价格：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='sh'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">合作时间：</td>
                    <td style="padding: 5px">
                        <input class='easyui-datebox' disabled='disabled'   name='hzdate'  data-options="formatter:myformatter,parser:myparser" />
                    </td>

         </tr>

    </table>
</form>
</div>
</body>
</html>
