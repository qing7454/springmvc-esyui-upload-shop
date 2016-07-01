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
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js"></script>
    <script type="text/javascript">
        var id = '${ param.id}';
        window.onload = (function () {

            loadFormData(id);
        });
        function myformatter1(d){
            var date=new Date(d);
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
        function myparser1(s){
            if (!s) {
                return new Date();
            }
            s = myformatter1(s);
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }
        function ww4(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            var h = date.getHours();
            return  y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日'+(h<10?('0'+h):h)+'点';

        }
        function w4(s){
            var reg=/[\u4e00-\u9fa5]/  //利用正则表达式分隔
            var ss = (s.split(reg));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            var h = parseInt(ss[3],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h)){
                return new Date(y,m-1,d,h);
            } else {
                return new Date();
            }
        }
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


                <td style="padding: 5px">邮箱：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='email'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">邮箱密码：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='passwordEmail'/>
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


                <td style="padding: 5px">账号等级：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='level'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">身份账号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='idcard'/>
                </td>


                <td style="padding: 5px">姓名：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='name'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">异常原因：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='exception'/>
                </td>


                <td style="padding: 5px">删除原因：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='deletereason'/>
                </td>

            </tr>
            <tr>

                <td style="padding: 5px">注册日期：</td>
                <td style="padding: 5px">
                    <input  name="registerdate" class="easyui-datebox">
                </td>


                <td style="padding: 5px">手机号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='phone'/>
                </td>

            </tr>
            <tr>
                <td style="padding: 5px">收货地址：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox'  disabled='disabled'  name='address'  />
                </td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
