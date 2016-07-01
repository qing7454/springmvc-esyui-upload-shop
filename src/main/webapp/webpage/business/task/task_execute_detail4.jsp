<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp" %>
<c:set var="actionUrl">
    ${pageContext.request.contextPath}/business/task.do
</c:set>
<c:set var="tableName" value="task" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@include file="/include/head.jsp" %>
    <%@include file="/include/code/simple/view_ext.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js"></script>
    <script type="text/javascript">
        var taskId = '${ param.id}';
        var accountId = '${param.accountId}';
        window.onload = (function () {
            $("#orderNum").blur(function(){
                var num = $("#orderNum").val();
                if(num != null && num.length >0){

                    ajaxJson("${pageContext.request.contextPath}/business/order.do?getNumCheck",{num:num},function(data){
                        if(!data.success){
                            $("#message").append(data.msg);
                        }else{
                            $("#message").empty();
                        }
                    });
                }
            });
            loadFormData(id);
        });

        //完成任務
        function endTask(){
            if(!checkOrder()){
                return false;
            }
            var taskId=$("#taskId").val()

            ajaxJson("${pageContext.request.contextPath}/business/task.do?completeTask",{taskId:taskId},function(data){
                if(data.success){
                    window.parent.$("#editDia").dialog("close");
                    window.parent.slideMsgInfo(data.msg);
                }
            });
        }
        //标记异常
        function doException(){
            var reason = $("#reason").val();
            if(reason.length <= 0){
                $("#exceptionReason").show();
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
        //检查数据格式
        function checkOrder(){
            var unCheckedBoxs = $("input[name='myCheckbox']").not("input:checked");
            if(unCheckedBoxs.length > 0){
                alertMsg("任务要求未完成！")
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div align="center">
    <form id="editForm">
        <input name="id" type="hidden" id="edit_id">
        <table align="center">
            <tr>
                <td colspan="4"><h3>任务信息</h3></td>
            </tr>
            <tr>
                <td style="padding: 5px">刷单方式：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='sdfs' value="${task.sdfs}"/>
                    <input id="taskId" value="${task.id}" type="hidden">
                </td>
                <td style="padding: 5px">店铺名：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='shopname' value="${task.shopname}"/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">SKU：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='sku' value="${task.sku}"/>
                </td>
                <td style="padding: 5px">评价信息：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='keyword' value="${task.pjwz}"/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">是否晒图：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='sku' value="${task.sfst}"/>
                </td>
                <td style="padding: 5px">图片名称：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='keyword' value="${task.picture}"/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">订单编号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='mark' value="${task.ordernun}"/>
                </td>
                <td style="padding: 5px">备注：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='mark' value="${task.mark}"/>
                </td>
            </tr>
            <tr>
                <td colspan="4"><h3>账号信息</h3></td>
            </tr>
            <tr>
                <td style="padding: 5px">账号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='account' value="${account.account}"/>
                    <input id="accountId" value="${account.id}" type="hidden">
                </td>
                <td style="padding: 5px">登录密码：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='passwordLogin' value="${account.passwordLogin}"/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">支付密码：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='passwordPay' value="${account.passwordPay}"/>
                </td>
                <td style="padding: 5px">地区：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='area' value="${account.area}"/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">运营商：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='operator' value="${account.operator}"/>
                </td>
                <td style="padding: 5px">账号等级：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='operator' value="${account.level}"/>

                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td><label><input name="myCheckbox" type="checkbox" value="" />评价确认已完成</label></td>
                <td><label><input name="myCheckbox" type="checkbox" value="" />晒图确认已完成</label></td>
                <td></td>
            </tr>
            <tr style="display: none" id="exceptionReason">
                <td>异常原因：</td>
                <td colspan="2">
                    <textarea id="reason" rows="5" cols="40"></textarea>
                </td>

            </tr>
            <tr align="center">
                <td></td>
                <td><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="endTask()">完成任务</a></td>
                <td><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="doException()">账号异常</a></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
