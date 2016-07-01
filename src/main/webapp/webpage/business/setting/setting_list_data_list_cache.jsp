<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
  <table id="dataTable"  class="easyui-datagrid"   align="center" data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
      <thead data-options="frozen:true">
      <tr>
          <th field="id" data-options="checkbox:true"></th>
      </tr>
      </thead>
      <thead>
      <tr>
         <th field='tdplq' sortable="true" data-options="width:100" >同店疲劳期</th>
         <th field='tskuplq' sortable="true" data-options="width:100" >同SKU疲劳期</th>
         <th field='gmjs' sortable="true" data-options="width:100" >月最多购买件数</th>
         <th field='gmje' sortable="true" data-options="width:100" >月最多购买金额</th>
         <th field="cz"   data-options="formatter: function(value,row,index){ return buttonCz(value,row,index);}" >操作</th>
        </tr>
      </thead>
  </table>
