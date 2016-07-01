<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/setting.do
</c:set>
<c:set var="tableName" value="setting" scope="request" />
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

                    <td style="padding: 5px">同店疲劳期：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='tdplq'  />
                    </td>

 
                    <td style="padding: 5px">同SKU疲劳期：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='tskuplq'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">月最多购买件数：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='gmjs'  />
                    </td>

 
                    <td style="padding: 5px">月最多购买金额：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='gmje'  />
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
