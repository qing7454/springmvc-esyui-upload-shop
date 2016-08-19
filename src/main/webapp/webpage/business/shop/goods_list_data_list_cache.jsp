<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
  <table id="dataTable"  class="easyui-datagrid"   align="center" data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
      <thead data-options="frozen:true">
      <tr>
          <th field="id" data-options="checkbox:true"></th>
      </tr>
      </thead>
      <thead>
      <tr>
         <th field='goodid' data-options="width:100"  sortable="true">商品ID</th>
         <th field='goodname' data-options="width:150"  sortable="true">商品名称</th>
         <th field='price' sortable="true" data-options="width:100" >价格</th>
         <th field='mark' data-options="width:150"  sortable="true">备注</th>
         <%--<th field='shopid' data-options="width:100"  sortable="true">商家ID</th>--%>
         <th field="cz"   data-options="formatter: function(value,row,index){ return buttonCz(value,row,index);}" >操作</th>
        </tr>
      </thead>
  </table>
