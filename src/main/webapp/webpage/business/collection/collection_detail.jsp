<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/collection.do
</c:set>
<c:set var="tableName" value="collection" scope="request" />
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

                    <td style="padding: 5px">收款类型：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='collectionType'  />
                    </td>

 
                    <td style="padding: 5px">商家：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='shopid'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">商家编码：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='shopnum'  />
                    </td>

 
                    <td style="padding: 5px">收款日期：</td>
                    <td style="padding: 5px">
                        <input class='easyui-datebox' disabled='disabled'   name='collectionDate'  data-options="formatter:myformatter,parser:myparser" />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">收款账户：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='collectionAccount'  />
                    </td>

 
                    <td style="padding: 5px">打款账户：</td>
                    <td style="padding: 5px">
                        <input class='easyui-validatebox'  disabled='disabled'  name='payAccount'  />
                    </td>

                </tr>
                <tr>
 
                    <td style="padding: 5px">收款金额：</td>
                    <td style="padding: 5px">
                        <input class='easyui-numberbox' disabled='disabled' name='collectionMoney'  />
                    </td>

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
