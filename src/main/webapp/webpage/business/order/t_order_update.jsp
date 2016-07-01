<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/order.do
</c:set>
<c:set var="tableName" value="t_order" scope="request" />
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

            <td style="padding: 5px">订单编号：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='ddnum'  />
            </td>

            <td style="padding: 5px">评价时间：</td>
            <td style="padding: 5px">
                <input class='easyui-datebox'    name='pjdate'  data-options="formatter:myformatter,parser:myparser" />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">下单人员：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='xdpersonid'  />
            </td>

            <td style="padding: 5px">收货人员：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='shpersionid'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">评价人员：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='pjpsersionid'  />
            </td>

            <td style="padding: 5px">银行卡：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='bankcard'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">货款：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='payment'  />
            </td>

            <td style="padding: 5px">下单佣金：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='commissionXd'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">收货评价佣金：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='commissionShPj'  />
            </td>

            <td style="padding: 5px">收货佣金：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='commissionSh'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">评价佣金：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='commissionPj'  />
            </td>

            <td style="padding: 5px">账号ID：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='accountid'  />
            </td>
         </tr>
         <tr>

            <td style="padding: 5px">商品编号：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='goodnum'  />
            </td>

            <td style="padding: 5px">账号：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='account'  />
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
