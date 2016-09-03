<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
  <table id="dataTable"  class="easyui-datagrid"   align="center" data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
      <thead data-options="frozen:true">
      <tr>
          <th field="id" data-options="checkbox:true"></th>
      </tr>
      </thead>
      <thead>
      <tr>
         <th field='shopName' data-options="width:200"  sortable="true">店铺名称</th>
         <th field='commissionXd' sortable="true" data-options="width:150" >下单佣金</th>
         <th field='commissionShPj' sortable="true" data-options="width:150" >收货评价佣金</th>
         <th field='commissionSh' sortable="true" data-options="width:150" >收货佣金</th>
         <th field='commissionPj' sortable="true" data-options="width:150" >评价佣金</th>
         <th field='amount' sortable="true" data-options="width:150" >总计</th>
        </tr>
      </thead>
  </table>
