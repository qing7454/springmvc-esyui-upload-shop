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
            var accountId=$("#accountId").val();
            var level=$("#level").combobox('getValue');
            var idcard=$("#idcard").val()
            var name=$("#name").val();
            var address=$("#address").val();
            var bankcard = $("#bankcard").combobox('getValue');
            var payment =$("#payment").val();
            var orderNum = $("#orderNum").val();
            ajaxJson("${pageContext.request.contextPath}/business/order.do?submitOrder",{taskId:taskId,accountId:accountId,level:level,idcard:idcard,
                name:name,address:address,bankcard:bankcard,payment:payment,orderNum:orderNum},function(data){
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
            ajaxJson("${pageContext.request.contextPath}/business/account.do?markException",{id:accountId,reason:reason},function(data){
                if(data.success){
                    window.parent.$("#editDia").dialog("close");
                    window.parent.slideMsgInfo(data.msg);
                }
            });
        }
        //检查数据格式
        function checkOrder(){
            var bankcard = $("#bankcard").combobox('getValue');
            var payment =$("#payment").val();
            var orderNum = $("#orderNum").val();
            if(bankcard ==null || bankcard.length == 0){
                alertMsg("请选择银行卡号！")
                return false;
            }
            if(payment ==null || payment.length == 0){
                alertMsg("请填写货款！")
                return false;
            }
            if(orderNum ==null || orderNum.length == 0){
                alertMsg("请填写订单号！")
                return false;
            }
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
                <td style="padding: 5px">关键词：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='keyword' value="${task.keyword}"/>
                </td>
            </tr>

            <tr>

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
                    <select id="level" name="level" style="width: 147px" class="easyui-combobox" data-options="panelHeight:'auto'" value="${account.level}">
                        <option value="注册账号" selected>注册账号</option>
                        <option value="铜牌会员">铜牌会员</option>
                        <option value="银牌会员">银牌会员</option>
                        <option value="金牌会员">金牌会员</option>
                        <option value="钻石会员">钻石会员</option>
                    </select>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">身份证号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' id="idcard" name='idcard' value="${account.idcard}"/>
                </td>


                <td style="padding: 5px">姓名：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' name='name' id="name" value="${account.name}"/>
                </td>

            </tr>

            <tr>
                <td style="padding: 5px">收货地址：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' id="address"  name='address' value="${account.address}"  />
                </td>
            </tr>
            <tr>
                <td colspan="4"><h3>任务要求</h3></td>
            </tr>
            <tr>
                <td><label><input name="myCheckbox" type="checkbox" value="" />外比</label></td>
                <td><label><input name="myCheckbox" type="checkbox" value="" />内比</label> </td>
                <td><label><input name="myCheckbox" type="checkbox" value="" />收藏主款</label></td>
                <td><label><input name="myCheckbox" type="checkbox" value="" />收藏副款</label></td>
                <td><label><input name="myCheckbox" type="checkbox" value="" />收藏店铺</label></td>
            </tr>
            <tr>
                <td colspan="4"><h3>付款信息</h3></td>
            </tr>
            <tr>
                <td>银行卡号；</td><td><input id="bankcard" class="easyui-combobox" name="bankcard"
                                         data-options="valueField:'cKey',textField:'cValue',url:'${pageContext.request.contextPath}/business/bankcard.do?getBankCard',panelHeight:'auto'" />
            </td>
                <td>货款金额</td><td><input id="payment" type="text" class="easyui-numberbox" /></td>
            </tr>
            <tr>
                <td colspan="4"><h3>订单信息</h3></td>
            </tr>
            <tr>
                <td>订单编号：</td><td><input id="orderNum" class="easyui-validatebox"/></td>
                <td><span id="message" style="font-size:12px;color:red"></span></td>
                <td><label><input name="myCheckbox" type="checkbox" value="" />下单已备注</label></td>
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
