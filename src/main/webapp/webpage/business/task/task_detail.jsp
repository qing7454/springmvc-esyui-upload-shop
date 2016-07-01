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
                <td style="padding: 5px">任务类型：</td>
                <td style="padding: 5px">
                    <input class='easyui-combobox' disabled="disabled" name='tasktype'
                           data-options="url:'${pageContext.request.contextPath}/sys/dic.do?dicList&dicKey=dic_key:dic_type=\'rwlx\'' , method: 'get', valueField:'cKey',textField:'cValue',panelHeight:'auto'"/>
                </td>
                <td style="padding: 5px">刷单方式：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='sdfs'/>
                </td>

            </tr>
            <tr>
                <td style="padding: 5px">店铺名：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='shopname'/>
                </td>
                <td style="padding: 5px">SKU：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='sku'/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">订单编号：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='ordernun'/>
                </td>
                <td style="padding: 5px">关键词：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='keyword'/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">评价文字：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='pjwz'/>
                </td>

                <td style="padding: 5px">是否晒图：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='sfst'/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px">图片名称：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='picture'/>
                </td>

                <td style="padding: 5px">备注：</td>
                <td style="padding: 5px">
                    <input class='easyui-validatebox' disabled='disabled' name='mark'/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
