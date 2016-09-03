<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/finance/commission.do
</c:set>
<c:set var="tableName" value="commission" scope="request" />
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

                    <td style="padding: 5px">店铺名称：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='shopName'  />
                    </td>

 
                    <td style="padding: 5px">下单佣金：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='commissionXd'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">收货评价佣金：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='commissionShPj'  />
                    </td>

 
                    <td style="padding: 5px">收货佣金：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='commissionSh'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">评价佣金：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='commissionPj'  />
                    </td>

 
                    <td style="padding: 5px">总计：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='amount'  />
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
