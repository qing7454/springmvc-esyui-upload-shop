<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl"  scope="request" >
  ${pageContext.request.contextPath}/business/task.do
</c:set>
<c:set var="tableName" value="task" scope="request" />
<!DOCTYPE html>
<html>
<head>
  <title>任务信息表</title>
  <%@include file="/include/head.jsp"%>
  <%@include file="/include/code/simple/view_ext.jsp" %>
  <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js" ></script>
  <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js" ></script>
  <style>
    td{border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;}
    table{border:solid #add9c0; border-width:1px 0px 0px 1px;width: 60%;margin-top: 30px}
  </style>
  <script type="text/javascript" >
    function formatNum(num){
      alert(num);
      return num.toFixed(2);
    }
    function showToday(){
      $("#today").show();
      $("#yesterday").hide();
    }
    function showYesterday(){
      $("#yesterday").show();
      $("#today").hide();
    }
  </script>
</head>
<body>
<div align="center">
  <div style="margin: 30px">
    <input type="button" value="实时" style="margin-right: 30px;width: 80px" onclick="showToday()">
    <input type="button" value="昨日" style="width: 80px" onclick="showYesterday()">
  </div>

  <table align="center" id="today">
    <tr>
      <td colspan="5">实时数据</td>
    </tr>
    <tr>
      <td width="40%">账号</td>
      <td width="15%">总任务数</td>
      <td width="15%">已完成</td>
      <td width="15%">未完成</td>
      <td width="15%">百分比</td>
    </tr>
    <c:forEach items="${datas}" var="data">
      <tr>
        <td >${data.realName}</td>
        <td >${data.taskCount}</td>
        <td >${data.complete}</td>
        <td >${data.unComplete}</td>
        <td >${data.percent}</td>
      </tr>
    </c:forEach>
    <tr>
      <td >${supervision.realName}</td>
      <td >${supervision.taskCount}</td>
      <td >${supervision.complete}</td>
      <td >${supervision.unComplete}</td>
      <td >${supervision.percent}</td>
    </tr>
  </table>
  <table align="center" id="yesterday" style="display: none">
    <tr>
      <td colspan="5">昨日数据</td>
    </tr>
    <tr>
      <td width="40%">账号</td>
      <td width="15%">总任务数</td>
      <td width="15%">已完成</td>
      <td width="15%">未完成</td>
      <td width="15%">百分比</td>
    </tr>
    <c:forEach items="${datas1}" var="data">
      <tr>
        <td >${data.realName}</td>
        <td >${data.taskCount}</td>
        <td >${data.complete}</td>
        <td >${data.unComplete}</td>
        <td >${data.percent}</td>
      </tr>
    </c:forEach>
    <tr>
      <td >${supervision1.realName}</td>
      <td >${supervision1.taskCount}</td>
      <td >${supervision1.complete}</td>
      <td >${supervision1.unComplete}</td>
      <td >${supervision1.percent}</td>
    </tr>
  </table>
</div>
</body>
</html>
