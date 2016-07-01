<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
  <table id="dataTable"  class="easyui-datagrid"   align="center" data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
      <thead data-options="frozen:true">
      <tr>
          <th field="id" data-options="checkbox:true"></th>
      </tr>
      </thead>
      <thead>
      <tr>
         <th field='name' data-options="width:100"  sortable="true">商家名</th>
         <th field='shopaccount' data-options="width:100"  sortable="true">商家账号</th>
         <th field='payment' sortable="true" data-options="width:100" >货款</th>
         <th field='commission' sortable="true" data-options="width:100" >佣金</th>
         <th field='sdApp' sortable="true" data-options="width:100" >刷单价格APP</th>
         <th field='sdPc' sortable="true" data-options="width:100" >刷单价格PC</th>
         <th field='shPj' sortable="true" data-options="width:100" >收货且评价价格</th>
         <th field='sh' sortable="true" data-options="width:100" >收货价格</th>
         <th field='hzdate' sortable="true" data-options="width:100"  >合作时间</th>
         <th field="cz"   data-options="formatter: function(value,row,index){ return buttonCz(value,row,index);}" >操作</th>
        </tr>
      </thead>
  </table>
