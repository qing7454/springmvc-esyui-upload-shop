<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp"%>
<c:set var="actionUrl" >
  ${pageContext.request.contextPath}/business/task.do
</c:set>
<c:set var="tableName" value="task" scope="request" />
<!DOCTYPE html>
<html>
<head>
  <title></title>
  <%@include file="/include/head.jsp"%>
  <%@include file="/include/code/simple/view_ext.jsp" %>
  <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/common_method.js" ></script>
  <script  type="text/javascript" src="${pageContext.request.contextPath}/plug-in/code_js/simple/method.js" ></script>
  <script type="text/javascript" >
    function submitImport(){
      var file = $("#taskFile").val();
      if(file == null|| file.length == 0){
        alertMsg("请选择要导入的文件")
        return false;
      }

      $('#editForm').form('submit', {
        url:"${pageContext.request.contextPath}/business/task.do?taskImport",
        success:function(data){
          // change the JSON string to javascript object
          var msg = eval('(' + data + ')');
          if (msg.success) {
            window.parent.$("#editDia").dialog("close");
            window.parent.slideMsgInfo(msg.msg);
          }else{
            window.parent.slideMsgInfo(msg.msg)
          }
        }
      });
    }
  </script>
</head>
<body>
<form id="editForm" enctype="multipart/form-data" method="post">
  <table align="center">
    <tr>
      <td width="150px">选择的导入文件：</td>
      <td><input type="file" id="taskFile" name="taskFile" style="width: 200px"
                 accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
        <span style="color:blue">&nbsp;*</span></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="button" onclick="submitImport()" value="导入">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
