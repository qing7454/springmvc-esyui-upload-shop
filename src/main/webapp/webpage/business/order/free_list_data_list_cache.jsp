<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
  <table id="dataTable"  class="easyui-datagrid"   align="center" data-options="height:370,toolbar:'#tools',onSortColumn:function(sort,order){$('#_order').val(sort+':'+order);getDataList();}">
      <thead data-options="frozen:true">
      <tr>
          <th field="id" data-options="checkbox:true"></th>
      </tr>
      </thead>
      <thead>
      <tr>
         <th field='ddnum' data-options="width:100"  sortable="true">订单编号</th>
         <th field='xdsj' sortable="true" data-options="width:100"  >下单时间</th>
         <th field='shdate' sortable="true" data-options="width:100"  >收货时间</th>
         <th field='pjdate' sortable="true" data-options="width:100"  >评价时间</th>
         <th field='xdpersonid'  sortable="true" data-options="width:100,formatter:function(value,row,index){return getUserById(value);}">下单人员</th>
         <th field='shpersionid' data-options="width:100,formatter:function(value,row,index){return getUserById(value);}"  sortable="true">收货人员</th>
         <th field='pjpsersionid' data-options="width:100,formatter:function(value,row,index){return getUserById(value);}"  sortable="true">评价人员</th>
         <th field='djstate' data-options="width:100,formatter: function(value,row,index){ return getDicValue('dic_key:dic_type=\'ddzt\'','','',value);}"  sortable="true">订单状态</th>
         <th field='bankcard' data-options="width:200,formatter:function(value,row,index){return getBankCardById(value);}"  sortable="true">银行卡</th>
         <th field='payment' sortable="true" data-options="width:100" >货款</th>
         <th field='commissionXd' sortable="true" data-options="width:100" >下单佣金</th>
         <th field='goodnum' data-options="width:100"  sortable="true">商品编号</th>
         <th field='account' data-options="width:100"  sortable="true">账号</th>
        </tr>
      </thead>
  </table>
