<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl"  scope="request" >
   ${pageContext.request.contextPath}/business/shop.do
</c:set>
<c:set var="tableName" value="shop" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <title>商家信息表</title>
    <%@include file="/include/head.jsp"%>
    <%@include file="/include/code/simple/view_ext.jsp" %>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js" ></script>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js" ></script>
    <script type="text/javascript" >
        var context='${ pageContext.request.contextPath}';
        window.onload=(function(){ getDataList();

            $("#dataTable").datagrid({
                onDblClickCell: function (rowid,rowdata) {
                    //  alert(rowid);
                    // countYjs();
                    //   showDetail();
                    showDetailByDbClick(rowid)
                }
            });
        });//初始化

        //打开更新对话框
        function showUpdateDia(){
            var row=$("#dataTable").datagrid("getChecked");
            if(row.length==0){
                alertMsg("请选择条目");
                return false;
            }
            if(row.length>1){
                alertMsg("只能选择一条!");
                return false;
            }
            if(!checkDataState(row[0])){
                alertMsg("当前数据不可操作！");
                return false;
            }
            $("#editFrame").attr("src","${pageContext.request.contextPath}/webpage/business/shop/shop_update.jsp?id="+row[0].id);
            $("#editDia").dialog("setTitle","更新数据");
            openEasyuiDialog("editDia");
           // showBussinessWindow("更新","${pageContext.request.contextPath}/webpage/business/shop/shop_update.jsp?id="+row[0].id);
        }
        //打开新增对话框
        function showAddDia(){
            $("#editFrame").attr("src","${pageContext.request.contextPath}/webpage/business/shop/shop_update.jsp");
            $("#editDia").dialog("setTitle","新增数据");
            openEasyuiDialog("editDia");
        }

        //查看详情
        function showDetail(){
            var row=$("#dataTable").datagrid("getChecked");
            if(row.length==0){
                alertMsg("请选择条目");
                return false;
            }
            if(row.length>1){
                alertMsg("只能选择一条!");
                return false;
            }
            $("#editFrame").attr("src","${pageContext.request.contextPath}/webpage/business/shop/shop_detail.jsp?id="+row[0].id);
            $("#editDia").dialog("setTitle","数据详情");
            openEasyuiDialog("editDia");
            //showBussinessWindow("详情","${pageContext.request.contextPath}/webpage/business/shop/shop_detail.jsp?id="+row[0].id);
        }

        //双击查看详情
        function showDetailByDbClick(rowid){
            var row=$("#dataTable").datagrid("getChecked");
            if(row.length==0){
                alertMsg("请选择条目");
                return false;
            }
            if(row.length>1){
                alertMsg("只能选择一条!");
                return false;
            }
            $("#editFrame").attr("src","${pageContext.request.contextPath}/webpage/business/shop/shop_detail.jsp?id="+row[rowid].id);
            $("#editDia").dialog("setTitle","数据详情");
            openEasyuiDialog("editDia");
            //showBussinessWindow("详情","${pageContext.request.contextPath}/webpage/business/shop/shop_detail.jsp?id="+row[0].id);
   }

        //操作按钮
        function buttonCz(value,row,index){
            var button="";
        <pro:authFilter buttonCode="del" actionUrl="${_modulesLink}" >
                    button="<a href='javascript:void(0);' onclick=\"delData('"+row.id+"',"+checkDataState(row)+")\">[删除]</a>";
        </pro:authFilter>
            return button;
        }

        //查看文件按钮
        function showViewFile(value,row,fieldName,showType){
            var button="";
        <pro:authFilter buttonCode="viewfile" actionUrl="${_modulesLink}" >
            if($.trim(value).length<=0)
            value="upload/"+row.id+"/"+fieldName;
            button="<a href='javascript:void(0);' onclick=\"showFileList('"+value+"','"+showType+"')\">[查看文件]</a>";
        </pro:authFilter>
            return button;
        }
        //进入商品管理
        function showGoods(){
            var row=$("#dataTable").datagrid("getChecked");
            if(row.length==0){
                alertMsg("请选择条目");
                return false;
            }
            if(row.length>1){
                alertMsg("只能选择一条!");
                return false;
            }
            $("#editFrame").attr("src","${pageContext.request.contextPath}/webpage/business/shop/shop_goods_list.jsp?id="+row[0].id);
            $("#editDia").dialog("setTitle","商品信息");
            openEasyuiDialog("editDia");

        }
    </script>
</head>
<body class="easyui-layout" title="商家信息表" align="center" style="width: 900px;height: 650px" fit="true">
        <div data-options="region:'north'" align="center" style="width: 100%;height: 150px;overflow: auto" >
            <form id="searchForm" method="post">
                <%--预定义条件--%>
                <c:forEach items="${_conditions}" var="_condition">
                    <c:forEach items="${_condition.value}" var="_val">
                        <input type="hidden" name="${_condition.key}"  value="${_val}" />
                    </c:forEach>
                </c:forEach>
                <input type="hidden" name="pageNum" id="pageNum" value="1" />
                <input type="hidden" name="_tableName" id="_tableName" value="shop" />
                <input type="hidden" id="_order" name="orders" />
                <table width="100%" >
                    <tr height="20">
                        <td class="head_search" colspan="20" style="text-align:left;height:20px;padding:10px 0 0 0;">
                            &nbsp;&nbsp;&nbsp;&nbsp;<b class="head_font" style="font-size:13px">数据查询</b>
                        </td>
                    </tr>
                    <tr height="30">
                        <td  width="10%" align="right">商家名：</td><td><input class='easyui-validatebox'   style="width:190px"   name='name_'  /></td>
                        <td  width="10%" align="right">合作时间：</td><td><input class='easyui-datebox'   style="width:190px" name='hzdate'  data-options="formatter:myformatter,parser:myparser" /></td>
                    </tr>
                    <tr align="center">
                        <td colspan="6" style="border-bottom:1px solid #d8d8d8;padding:5px 25px 10px 0;height:25px;">
                            <a href="javascript:void(0);" class=" easyui-linkbutton" onclick="getDataList()">查询</a>
                            &nbsp;&nbsp;<a href="javascript:void(0);"  class="easyui-linkbutton"   onclick="resetForm()">重置</a>
                        </td>
                    </tr>
               </table>
            </form>
            </div>
        <div data-options="region:'center'" style="height:500px;margin:20px 0 0 0;" align="center">
            <table width="100%" >
                <tr height="20">
                    <td class="head_search" colspan="20" style="text-align:left;padding:0 0 10px 0;">
                        &nbsp;&nbsp;&nbsp;&nbsp;<b class="head_font" style="font-size:13px">数据列表</b>
                    </td>
                </tr>
            </table>
            <%@include file="shop_list_data_list_cache.jsp"%>
        </div>
        <div  data-options="region:'south'" >
            <div class="easyui-panel" style="height: 38px;">
                <div id="pager" class="easyui-pagination" data-options="showRefresh:false,showPageList:false,total:0,onSelectPage: function(pageNumber, pageSize){ $('#pageNum').val(pageNumber); getDataList();}"></div>
            </div>
        </div>
<div id="tools" style="padding: 5px">
<%@include file="/include/code/business_button_core.jsp"%>
    <a href="javascript:void(0);"  class=" easyui-linkbutton" iconCls="icon-edit" onclick="showGoods()">产品管理</a>
<%@include file="/include/code/business_button_ext.jsp"%>
</div>
<%@include file="/include/code/business_dialog.jsp" %>
<%@include file="/include/code/business_footer_ext.jsp" %>
</body>
</html>
