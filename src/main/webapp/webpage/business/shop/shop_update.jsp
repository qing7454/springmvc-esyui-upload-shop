<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
    ${pageContext.request.contextPath}/business/shop.do
</c:set>
<c:set var="tableName" value="shop" scope="request" />
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

        /**
         * 加载form数据
         * @param id
         */
        function loadFormData(id){
            $("#editForm").form("reset");
            if(jQuery.trim(id).length>0){
                ajaxJson(actionUrl+"?get",{id:id},function(data){
                    if(data){
                        $("#editForm").form("load",data);
                        if(data.scyq == 1){
                            $("#zysc").show();
                        }
                    }
                });
            }
        }

        function showScyq(){
            $("#zysc").show();
        }
        function hideScyq(){
            $("#zysc").hide();
        }

        //保存
        function saveData(){
            if(!$("#editForm").form("validate"))
                return false;
            ajaxJson(actionUrl+"?save1",$("#editForm").serialize(),function(data){

                if($(".download_button").length<=0){
                    window.parent.closeDiaWithMsgWhenSuccess("editDia",data);
                }else{
                    if(data.success){
                        $("#editForm").form("load",data.dataMap.bean);
                        id=data.dataMap.bean.id;
                    }
                    slideMsgInfo(data.msg);
                    $("#editForm").dialog("close");
                }
            });
        }
    </script>
</head>
<body >
<div align="center">
<form id="editForm">
    <input name="id" type="hidden" id="edit_id">
    <table align="center">
        <tr>

            <td style="padding: 5px">商家名：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='name'  />
            </td>

            <td style="padding: 5px">商家账号：</td>
            <td style="padding: 5px">
                <input class='easyui-validatebox'    name='shopaccount'  />
            </td>
         </tr>
         <tr>
             <td style="padding: 5px">刷单价格PC：</td>
             <td style="padding: 5px">
                 <input class='easyui-numberbox'  name='sdPc'  />
             </td>

            <td style="padding: 5px">刷单价格APP：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='sdApp'  />
            </td>
         </tr>
         <tr>

             <td style="padding: 5px">收货价格：</td>
             <td style="padding: 5px">
                 <input class='easyui-numberbox'  name='sh'  />
             </td>

            <td style="padding: 5px">收货且评价价格：</td>
            <td style="padding: 5px">
                <input class='easyui-numberbox'  name='shPj'  />
            </td>

         </tr>
         <tr>

            <td style="padding: 5px">合作时间：</td>
            <td style="padding: 5px">
                <input class='easyui-datebox'    name='cooperatedate'  data-options="formatter:myformatter,parser:myparser" />
            </td>

             <td style="padding: 5px">收菜要求：</td>
             <td style="padding: 5px">
                 <label for="task">任务收菜</label><input type="radio" id="task" name="scyq" value="0" checked="checked" onclick="hideScyq()"/>
                 <label for="free">自由收菜</label><input type="radio" id="free" name="scyq" value="1" onclick="showScyq()"/>
             </td>
         </tr>
         <tr style="display: none" id="zysc">
             <td>收菜百分比：</td>
             <td><input class="easyui-numberbox" name="scbfb" data-options="min:0,max:100"> </td>
             <%--<td>收菜天数</td>--%>
             <%--<td><input class="easyui-numberbox" name="scts" data-options="min:0"></td>--%>
        </tr>
        <tr align="center">
            <td colspan="4"><a href="javascript:void(0);"  class=" easyui-linkbutton" onclick="saveData()">提交</a></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
