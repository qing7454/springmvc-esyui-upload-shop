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
                        <input class='easyui-validatebox'  disabled='disabled'  name='shopName'  />
                    </td>

 
                    <td style="padding: 5px">欠货款总额：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='payment'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">欠佣金总额：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='commission'  />
                    </td>

 
                    <td style="padding: 5px">总计：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='ammount'  />
                    </td>

                </tr>
                <tr>
           </tr>



                   <tr>
                       <td style="padding: 5px">创建人：</td>
                       <td style="padding: 5px"><input class="easyui-validatebox validatebox-text" disabled="disabled" name="_createUserName"></td>
                       <td style="padding: 5px">创建时间：</td>
                       <td style="padding: 5px"><input class="easyui-validatebox validatebox-text" disabled="disabled" name="_createDate"></td>
                   </tr>

                   <tr>
                       <td style="padding: 5px">更新人：</td>
                       <td style="padding: 5px"><input class="easyui-validatebox validatebox-text" disabled="disabled" name="_updateUserName"></td>
                       <td style="padding: 5px">更新时间人：</td>
                       <td style="padding: 5px"><input class="easyui-validatebox validatebox-text" disabled="disabled" name="_updateDate"></td>
                   </tr>

                   <tr>
                       <td style="padding: 5px">部门ID：</td>
                       <td style="padding: 5px"><input class="easyui-validatebox validatebox-text" disabled="disabled" name="depIdIdentify"></td>
                   </tr>


    </table>
</form>
</div>
</body>
</html>
