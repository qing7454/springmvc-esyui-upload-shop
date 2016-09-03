<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
  <table id="dataTable"  class="easyui-datagrid"   align="center" data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
      <thead data-options="frozen:true">
      <tr>
          <th field="id" data-options="checkbox:true"></th>
      </tr>
      </thead>
      <thead>
      <tr>
         <th field='shopName' data-options="width:200"  sortable="true">商家名称</th>
         <th field='payment' sortable="true" data-options="width:150" >欠货款总额</th>
         <th field='commission' sortable="true" data-options="width:150" >欠佣金总额</th>
         <th field='ammount' sortable="true" data-options="width:150" >总计</th>
        </tr>
      </thead>
  </table>
