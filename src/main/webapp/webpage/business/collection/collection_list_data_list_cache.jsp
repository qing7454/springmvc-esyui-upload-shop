<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
  <table id="dataTable"  class="easyui-datagrid"   align="center" data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
      <thead data-options="frozen:true">
      <tr>
          <th field="id" data-options="checkbox:true"></th>
      </tr>
      </thead>
      <thead>
      <tr>
         <th field='collectionType' data-options="width:100"  sortable="true">收款类型</th>
         <th field='shopnum' data-options="width:100"  sortable="true">商家</th>
         <th field='collectionDate' sortable="true" data-options="width:100"  >收款日期</th>
         <th field='collectionAccount' data-options="width:100"  sortable="true">收款账户</th>
         <th field='payAccount' data-options="width:100"  sortable="true">打款账户</th>
         <th field='collectionMoney' sortable="true" data-options="width:100" >收款金额</th>
         <th field="cz"   data-options="formatter: function(value,row,index){ return buttonCz(value,row,index);}" >操作</th>
        </tr>
      </thead>
  </table>
