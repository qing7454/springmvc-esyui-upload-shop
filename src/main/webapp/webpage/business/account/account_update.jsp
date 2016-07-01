<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp" %>
<c:set var="actionUrl">
    ${pageContext.request.contextPath}/business/account.do
</c:set>
<c:set var="tableName" value="account" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@include file="/include/head.jsp" %>
    <%@include file="/include/code/simple/view_ext.jsp" %>
    <style type="text/css">
        select {
            width: 135px;
        }

        fieldset dl dd {
            float: left;
            margin-left: 20px;
        }

        fieldset pre {
            width: 100%;
            height: 400px;
            overflow-y: scroll;
            overflow-x: hidden;
        }

        input {
            width: 172px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webpage/business/account/area.js"></script>
    <script type="text/javascript">
        var id = '${ param.id}';
        window.onload = (function () {
            if (id != null && id != "") {
                loadFormData(id);
            }
            //设置省份数据
            setProvince();
            //设置背景颜色
            setBgColor();
            setArea();
            $('#registerdate').datebox('setValue', formatterDate(new Date()));

        });
        //保存
        function saveData(){
            if(!$("#editForm").form("validate"))
                return false;
            ajaxJson("${pageContext.request.contextPath}/business/account.do?save", $("#editForm").serialize(), function (data) {
                slideMsgInfo(data.msg);
                window.parent.$("#editDia").dialog("close");
            });
        }
        function setArea() {
            var procince = $("#selProvince").val();
            var city = $("#selCity").val();
            var area = procince + "-" + city;
            $("#area").val(area);
        }

        function formatterDate(date) {
            var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
            var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
            + (date.getMonth() + 1);
            return date.getFullYear() + '-' + month + '-' + day;
        }
        ;
    </script>
</head>
<body>
<div align="center">
    <form id="editForm">
        <input name="id" type="hidden" id="edit_id">
        <table align="center">
            <tr>

                <td style="padding: 5px">账号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' name='account' data-options="required:true"/>
                </td>

                <td style="padding: 5px">登录密码：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' name='passwordLogin'/>
                </td>
            </tr>
            <tr>

                <td style="padding: 5px">支付密码：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' name='passwordPay'/>
                </td>

                <td style="padding: 5px">邮箱：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' name='email'/>
                </td>
            </tr>
            <tr>

                <td style="padding: 5px">邮箱密码：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' name='passwordEmail'/>
                </td>

                <td style="padding: 5px">地区：</td>
                <td style="padding: 5px">
                    <select style="width: 75px;" id="selProvince" onchange="provinceChange(),setArea();"></select>
                    &nbsp;<select style="width: 75px;" id="selCity" onchange="setArea()"></select>
                    <input name="area" id="area" type="hidden">
                </td>
            </tr>
            <tr>

                <td style="padding: 5px">运营商：</td>
                <td style="padding: 5px">
                    <select name="operator" style="width: 175px">
                        <option value="电信" selected>电信</option>
                        <option value="联通">联通</option>
                        <option value="电信联通">电信联通</option>
                    </select>
                </td>

                <td style="padding: 5px">账号等级：</td>
                <td style="padding: 5px">
                    <select name="level" style="width: 175px">
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
                    <input class='easyui-validatebox' name='idcard'/>
                </td>

                <td style="padding: 5px">姓名：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' name='name'/>
                </td>
            </tr>
            <%--<tr>--%>

                <%--<td style="padding: 5px">异常原因：</td>--%>
                <%--<td style="padding: 5px">--%>
                    <%--<input class='easyui-validatebox' name='exception'/>--%>
                <%--</td>--%>

                <%--<td style="padding: 5px">删除原因：</td>--%>
                <%--<td style="padding: 5px">--%>
                    <%--<input class='easyui-validatebox' name='deletereason'/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr>

                <td style="padding: 5px">注册日期：</td>
                <td style="padding: 5px">
                    <input class='easyui-datebox' name='registerdate' id="registerdate"
                           data-options="formatter:myformatter,parser:myparser"/>
                </td>

                <td style="padding: 5px">手机号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox'  name='phone'   />
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">收货地址：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox'  name='address'  />
                </td>
            </tr>
            <tr align="center">
                <td colspan="4"><a href="javascript:void(0);" class=" easyui-linkbutton" onclick="saveData()">提交</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
