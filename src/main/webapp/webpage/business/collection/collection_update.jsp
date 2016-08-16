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
//            $("#shopnum").blur(function(){
//                var shopnum = $("#shopnum").val();
//                checkShop(shopnum);
//            });
        });


        //保存
        function saveData(){
            if(!$("#editForm").form("validate"))
                return false;

            var shopnum = $("#shopnum").val();
            checkShop(shopnum);
            ajaxJson(actionUrl+"?collection",$("#editForm").serialize(),function(data){
                if(data.success){
                    $("#editForm").form("load",data.dataMap.bean);
                    id=data.dataMap.bean.id;
                }
                slideMsgInfo(data.msg);
                $("#editForm").dialog("close");
            });
        }

        /**
        *检查商户是否存在
         */
        function checkShop(shopCode){
            $.ajax({
                url:"${pageContext.request.contextPath}/business/shop.do?getCheck",
                type:"post",
                data:{shopCode:shopCode},
                dataType:"json",
                cache:false,
                async:false,
                success:function(data){
                    if(data.success){
                        return true;
                    }else{
                        alertMsg("商家不存在")
                        return false;
                    }
                }
            });

        }
    </script>
</head>
<body >
<div align="center">
<form id="editForm">
    <input name="id" type="hidden" id="edit_id">
    <table align="center" style="margin-top: 20px">
        <tr>

            <td style="padding: 5px">收款类型：</td>
            <td style="padding: 5px">
                <input class='easyui-combobox'    name='collectionType' data-options="url:'${pageContext.request.contextPath}/sys/dic.do?dicList&dicKey=dic_key:dic_type=\'rwlx\'' , method: 'get', valueField:'cKey',textField:'cValue',panelHeight:'auto'" />
            </td>

            <td style="padding: 5px">商家编码：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'  id="shopnum"  name='shopnum'  />
            </td>
         </tr>
         <tr>

             <td style="padding: 5px">收款账户：</td>
             <td style="padding: 5px">
                 <input class='easyui-validatebox'    name='collectionAccount'  />
             </td>

            <td style="padding: 5px">收款日期：</td>
            <td style="padding: 5px">
                <input class='easyui-datebox'    name='collectionDate'  data-options="formatter:myformatter,parser:myparser" />
            </td>
         </tr>
         <tr>
            <td style="padding: 5px">打款账户：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='payAccount'  />
            </td>

             <td style="padding: 5px">收款金额：</td>
             <td style="padding: 5px">
                 <input class='easyui-numberbox'  name='collectionMoney'  />
             </td>
         </tr>
         <tr>


        </tr>
        <tr align="center">
            <td colspan="4"><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="saveData()">提交</a></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
