<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp" %>
<c:set var="actionUrl">
    ${pageContext.request.contextPath}/business/order.do
</c:set>
<c:set var="tableName" value="t_order" scope="request"/>
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
        window.onload = (function () {

            loadFormData(id);
        });

    </script>
</head>
<body>
<div align="center">
    <form id="editForm">
        <input name="id" type="hidden" id="edit_id">
        <table align="center">
            <tr>

                <td style="padding: 5px">订单编号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='ddnum'/>
                </td>


                <td style="padding: 5px">下单时间：</td>
                <td style="padding: 5px">
                    <input class='easyui-datebox' disabled='disabled' name='xdsj'
                           data-options="formatter:myformatter,parser:myparser"/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">收货时间：</td>
                <td style="padding: 5px">
                    <input class='easyui-datebox' disabled='disabled' name='shdate'
                           data-options="formatter:myformatter,parser:myparser"/>
                </td>


                <td style="padding: 5px">评价时间：</td>
                <td style="padding: 5px">
                    <input class='easyui-datebox' disabled='disabled' name='pjdate'
                           data-options="formatter:myformatter,parser:myparser"/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">下单人员：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='xdpersonid'/>
                </td>


                <td style="padding: 5px">收货人员：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='shpersionid'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">评价人员：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='pjpsersionid'/>
                </td>


                <td style="padding: 5px">订单状态：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='djstate'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">银行卡：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='bankcard'/>
                </td>


                <td style="padding: 5px">货款：</td>
                <td style="padding: 5px">
                    <input class='easyui-numberbox' disabled='disabled' name='payment'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">下单佣金：</td>
                <td style="padding: 5px">
                    <input class='easyui-numberbox' disabled='disabled' name='commissionXd'/>
                </td>


                <td style="padding: 5px">收货评价佣金：</td>
                <td style="padding: 5px">
                    <input class='easyui-numberbox' disabled='disabled' name='commissionShPj'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">收货佣金：</td>
                <td style="padding: 5px">
                    <input class='easyui-numberbox' disabled='disabled' name='commissionSh'/>
                </td>


                <td style="padding: 5px">评价佣金：</td>
                <td style="padding: 5px">
                    <input class='easyui-numberbox' disabled='disabled' name='commissionPj'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">账号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='account'/>
                </td>


                <td style="padding: 5px">商品编号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='goodnum'/>
                </td>

            </tr>

        </table>
    </form>
</div>
</body>
</html>
