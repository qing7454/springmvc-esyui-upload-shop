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
        <th field='account' data-options="width:200" sortable="true">账号</th>
        <th field='passwordLogin' data-options="width:150" sortable="true">登录密码</th>
        <th field='passwordPay' data-options="width:150" sortable="true">支付密码</th>
        <th field='phone' data-options="width:120" sortable="true">手机号</th>
        <th field='email' data-options="width:150" sortable="true">邮箱</th>
        <th field='passwordEmail' data-options="width:100" sortable="true">邮箱密码</th>
        <th field='area' data-options="width:100" sortable="true">地区</th>
        <th field='operator' data-options="width:100" sortable="true">运营商</th>
        <th field='level' data-options="width:100" sortable="true">账号等级</th>
        <%--<th field='address' data-options="width:100" sortable="true">收货地址</th>--%>
        <%--<th field='idcard' data-options="width:100"  sortable="true">身份账号</th>--%>
        <%--<th field='name' data-options="width:100"  sortable="true">姓名</th>--%>
        <%--<th field='exception' data-options="width:100"  sortable="true">异常原因</th>--%>
        <%--<th field='deletereason' data-options="width:100"  sortable="true">删除原因</th>--%>
        <th field='registerdate' sortable="true" data-options="width:100,formatter:function(value,row,index){return myformatter(value);}">注册日期</th>
        <%--<th field='accountstate' data-options="width:100"  sortable="true">账号状态</th>--%>
        <%--<th field="cz" data-options="formatter: function(value,row,index){ return buttonCz(value,row,index);}">操作</th>--%>
    </tr>
    </thead>
</table>
