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
    <th field='userName' data-options="width:100" sortable="true">刷手</th>
    <th field='registerCount' data-options="width:100" sortable="true">注册号量</th>
    <th field='sdCount' data-options="width:100" sortable="true">刷单量</th>
    <th field='shAndpjCount' data-options="width:100" sortable="true">收货且评价量</th>
    <th field='shCount' data-options="width:100" sortable="true">收货量</th>
    <th field='pjCount' data-options="width:100" sortable="true">评价量</th>
    <th field='removeException' data-options="width:100" sortable="true">解异常量</th>

    <th field='sdPercent' data-options="width:100" sortable="true">刷单完成率(%)</th>
    <th field='shAndPjPercent' data-options="width:120" sortable="true">收货且评价完成率(%)</th>
    <th field='shPercent' data-options="width:100" sortable="true">收货完成率(%)</th>
    <th field='pjPercent' data-options="width:100" sortable="true">评价完成率(%)</th>

  </tr>
  </thead>
</table>
