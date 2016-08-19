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
            getAccountInfoByOrderId(id);
        });

        /**
        * 根据订单ID查找账号信息
        * @param id
         */
        function getAccountInfoByOrderId(id) {
            ajaxJson(actionUrl + "?getAccountInfoByOrderId", {orderId: id}, function (data) {
                if (data) {
                    $("#accountForm").form("load", data);
                    if(data.accountstate == '02'){
                        $("#message").append("账号异常");
                    }
                    if(data.accountstate == '03'){
                        $("#message").append("账号已删除");
                    }
                }
            });
        }
        /**
        * 完成收货
         */
        function completeTask(){
            ajaxJson(actionUrl + "?completeFreeTask", {orderId: id}, function (data) {
                if (data.success) {

                    window.parent.$("#editDia").dialog("close");
                    window.parent.slideMsgInfo(data.msg);
                }else{
                    window.parent.slideMsgInfo(data.msg);
                }
            });
        }
        /**
        * 标记异常
        * @returns {boolean}
         */
        function doException(){
            var reason = $("#reason").val();
            if(reason.length <= 0){
                $("#exceptionReason").show();
                slideMsgInfo("请填写异常原因")
                return false;
            }
            var accountId=$("#accountId").val();
            alert(reason);
            ajaxJson("${pageContext.request.contextPath}/business/account.do?markException",{id:accountId,reason:reason},function(data){
                if(data.success){
                    window.parent.$("#editDia").dialog("close");
                    window.parent.slideMsgInfo(data.msg);
                }
            });
        }
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
                <td style="padding: 5px">商品编号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='goodnum'/>
                </td>
            </tr>
        </table>
    </form>
    <form id="accountForm">
        <table align="center">
            <tr>
                <td colspan="4"><h2>账号信息</h2></td>
            </tr>
            <tr>
                <td colspan="4"><span id="message" style="color: #A60000"></span></td>
            </tr>
            <tr>
                <td style="padding: 5px">账号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='account'/>
                </td>
                <td style="padding: 5px">登录密码：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='passwordLogin'/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">支付密码：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='passwordPay'/>
                </td>
                <td style="padding: 5px">地区：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='area'/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">运营商：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='operator'/>
                </td>
            </tr>
            <tr style="display: none" id="exceptionReason">
                <td>异常原因：</td>
                <td colspan="3">
                    <textarea id="reason" rows="5" cols="40"></textarea>
                </td>

            </tr>
            <tr align="center">
                <td></td>
                <td ><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="completeTask()">完成收货</a></td>
                <td ><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="doException()">账号异常</a></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
