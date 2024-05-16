<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/css/common.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/css/main.css"/>
</head>
<body>
<div class="container">
  <div class="content" style="margin: auto">
    <div class="information-title">
      <span>当前位置>在线购物>商品清单</span>
      <div class="bottom fr">

      </div>
    </div>

    <div class="unit-information">
      <div class="unit">
        <p class="unit-content">商品信息</p>
      </div>
      <div class="unit-list clearfix">
        <div class="fl">
              <c:if test="${not empty commodity}">
                <form action="<c:url value="/commodity/update"/>" method="post">
                  <div class="list ">
                    <input type="hidden" name="type" value="update">
                    <input type="hidden" name="commodity_id" value="${commodity.commodity_id}">
                    <label>商品名</label> : <input type="text" name="commodity_name" value="${commodity.commodity_name}"/>
                    <label>商品单价</label> : <input type="text" name="price" value="${commodity.price}"/>
                    <br><br><br>
                    <label>商品介绍</label> : <input type="text" name="introduce" value="${commodity.introduce}"/>
                  </div>
                  <label>&nbsp</label><input type="submit" value="确认" class="list">
                </form>
              </c:if>
              <c:if test="${empty commodity}">
              <form action="<c:url value="/commodity/save"/>" method="post">
                <div class="list ">
                  <input type="hidden" name="type" value="save">
                  <label>商品ID</label> : <input type="text" name="commodity_id" value=""/>
                  <label>商品名</label> : <input type="text" name="commodity_name" value=""/>
                  <br><br><br>
                  <label>商品单价</label> : <input type="text" name="price" value="${commodity.price}"/>
                  <label>商品介绍</label> : <input type="text" name="introduce" value="${commodity.introduce}"/>
                </div>
                <label>&nbsp</label><input type="submit" value="确认" class="list">
              </form>
              </c:if>
            </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
