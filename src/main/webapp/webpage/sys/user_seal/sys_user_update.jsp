<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/sys/user.do
</c:set>
<html>
<head>
    <title></title>
    <%@include file="/include/head.jsp"%>
    <script type="text/javascript" >

        window.onload=(function(){
            $("#editForm").form("reset");
            var id='${ param.id}';

            if(jQuery.trim(id).length>0){
                ajaxJson("${ actionUrl}?get",{id:id},function(data){
                    if(data){
                        $("#editForm").form("load",data);
                        $("#dep").combotree("setValue",data.dep.id);
                    }
                });
            }
        });
        //保存
        function saveData(){
            if(!$("#editForm").form("validate"))
                return false;
            ajaxJson("${ actionUrl}?save_seal",$("#editForm").serialize(),function(data){
                window.parent.closeDiaWithMsgWhenSuccess("editDia",data);
                window.parent.getDataList();
            });
        }
        //生成用户秘钥
        function generateUserKey(){
            ajaxJson("${actionUrl}?generateUserKey",{},function(data){
                if(assertAjaxSuccess(data))
                    $("#userKey").val(data);
            });
        }
    </script>
</head>
<body >
<div class="easyui-layout" fit="true">
<form id="editForm"  >
    <input name="id" type="hidden" id="edit_id">
    <table >
        <tr>
            <td style="padding: 5px" width="20%" align="right">所属部门：</td>
            <td style="padding: 5px">
                <input class='easyui-combotree'  name='dep.id'  id="dep" data-options="url:'${pageContext.request.contextPath}/sys/dep.do?gettree',required:true" />
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">用户名：</td>
            <td style="padding: 5px">
                <input class="input_arch_sea" class='easyui-validatebox'   name='username'  />
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">密码：</td>
            <td style="padding: 5px">
                <input class="input_arch_sea" class='easyui-validatebox' type="password"  name='passwd'  />
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">昵称：</td>
            <td style="padding: 5px">
                <input class="input_arch_sea" class='easyui-validatebox'   name='realname'  />
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">用户秘钥：</td>
            <td style="padding: 5px">
                <input class="input_arch_sea" class='easyui-validatebox' id="userKey"  name='userkey'  />
                &nbsp;<a  class='easyui-linkbutton'   name='realname'  onclick="generateUserKey()"  >生成秘钥</a>
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">签章ID：</td>
            <td style="padding: 5px">
                <input  class="input_arch_sea" class='easyui-validatebox'  name='seal_id'  />
                &nbsp;<a  class='easyui-linkbutton'  onclick="generateSeal()"  >生成签章</a>
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">签章验证ID：</td>
            <td style="padding: 5px">
                <input  class="input_arch_sea" class='easyui-validatebox'  name='seal_login_id'  />
                &nbsp;<a  class='easyui-linkbutton'  onclick="verify()"  >验证</a>
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">签章签名：</td>
            <td style="padding: 5px">
                <input  class="input_arch_sea" class='easyui-validatebox'   name='seal_name'  />
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">签章部门：</td>
            <td style="padding: 5px">
                <input  class="input_arch_sea" class='easyui-validatebox'   name='seal_dep'  />
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">状态：</td>
            <td style="padding: 5px">
                <input class='easyui-combobox'  name='state'  data-options="valueField:'value',textField:'text',data:[{'value':'1','text':'正常'},{'value':'2','text':'已删除'}],required:true" />
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">是否单一登录：</td>
            <td style="padding: 5px">
                <input type='checkbox'   name='singlelogin' value='1' />
            </td>
        </tr>
        <tr>
            <td style="padding: 5px" align="right">是否允许第三方登录：</td>
            <td style="padding: 5px">
                <input type='checkbox'   name='thirdlogin' value='1' />
            </td>
        </tr>
        <tr align="center">
            <td colspan="2"><a href="javascript:void(0);"    class="easyui-linkbutton" onclick="saveData()">提交</a></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
