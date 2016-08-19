<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<table id="dataTable" class="easyui-datagrid" align="center"
       data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
    <thead data-options="frozen:true">
    <tr>
        <th field="id" data-options="checkbox:true"></th>
    </tr>
    </thead>
    <thead>
    <tr>
        <th field='tasktype' sortable="true" data-options="width:150,formatter: function(value,row,index){ return getDicValue('dic_key:dic_type=\'rwlx\'','','',value);}">任务类型</th>
        <%--<th field='shopname' data-options="width:150" sortable="true">店铺名</th>--%>
        <th field='sku' data-options="width:150" sortable="true">SKU</th>
        <th field='sdfs' data-options="width:100" sortable="true">刷单方式</th>
        <th field='keyword' data-options="width:200" sortable="true">关键词</th>
        <th field='mark' data-options="width:200" sortable="true">备注</th>
        <th field='ordernun' data-options="width:150" sortable="true">订单编号</th>
        <th field='pjwz' data-options="width:150" sortable="true">评价文字</th>
        <th field='sfst' data-options="width:100" sortable="true">是否晒图</th>
        <th field='picture' data-options="width:150" sortable="true">图片名称</th>
        <th field='taskstate' data-options="width:100,formatter: function(value,row,index){ return getDicValue('dic_key:dic_type=\'rwzt\'','','',value);}"  sortable="true">任务状态</th>
        <%--<th field='completedate' sortable="true" data-options="width:100"  >完成时间</th>--%>
        <%--<th field='taskowner' data-options="width:100"  sortable="true">所属人</th>--%>
        <%--<th field="cz" data-options="formatter: function(value,row,index){ return buttonCz(value,row,index);}">操作</th>--%>
    </tr>
    </thead>
</table>
