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
            state();

        });
        function state(){
            var taskState = $("#taskState").val();
            if(taskState == 2){
                $("#taskTr").hide();
            }
        }
        /**
         * 执行任务
         */
        function execute() {
            var taskState = $("#taskState").val();
            if(taskState == 2){
                alertMsg("该任务已完成");
                return false;
            }
            var taskType = $("#taskType").combobox("getValue");
            var taskId = $("#edit_id").val();
            if(taskType == "01"){      //刷单任务
                var url="${pageContext.request.contextPath}/webpage/business/task/task_account_list.jsp?taskId="+taskId;
                window.location.href=url;
            }else if(taskType == "02"){    //收货且评价任务
                var url="${pageContext.request.contextPath}/business/task.do?toReceive&taskId="+taskId;
                window.location.href=url;
            }else if(taskType == "03"){    //收货任务
                var url="${pageContext.request.contextPath}/business/task.do?toReceive&taskId="+taskId;
                window.location.href=url;
            }else if(taskType == "04"){     //评价任务
                var url="${pageContext.request.contextPath}/business/task.do?toReceive&taskId="+taskId;
                window.location.href=url;
            }

        }
    </script>
</head>
<body>
<div align="center">
    <form id="editForm">
        <input name="id" type="hidden" id="edit_id">
        <input name="taskstate" type="hidden" id="taskState">
        <table align="center">
            <tr>
                <td style="padding: 5px">任务类型：</td>
                <td style="padding: 5px">
                    <input class='easyui-combobox' disabled="disabled" name='tasktype' id="taskType"
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
            <tr></tr>
            <tr align="center" id="taskTr">
                <td colspan="4"><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="execute()">执行任务</a></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
