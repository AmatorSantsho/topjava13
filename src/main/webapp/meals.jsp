
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title> Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<h3>List of meals</h3>
<table border="1" bgcolor="#f0fff0" width="500" cellpadding="0">
  <tr>
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
    <th>Edit</th>
    <th>Delete</th>
  </tr>
  <c:forEach items="${list}" var="meal">
    <c:if test="${meal.exceed==true}">
      <c:set var="fstyle" value="color: red"/>
    </c:if>
    <c:if test="${meal.exceed==false}">
      <c:set var="fstyle" value="color: green"/>
    </c:if>
    <tr>
      <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="time" type="both"/>
      <fmt:formatDate pattern="yyy-MM-dd HH:mm" value="${time}" var="parsedTime"/>
      <th style="${fstyle}">${parsedTime}</th>
      <th style="${fstyle}">${meal.description}</th>
      <th style="${fstyle}">${meal.calories}</th>
      <th><a href="meals?param1=edit&param2=${meal.id}">edit</a></th>
      <th><a href="meals?param1=delete&param2=${meal.id}">delete</a></th>
    </tr>
  </c:forEach>
</table>
<br>
<h1>Add new Meal/Edit</h1>
<form method="post" action="meals">
  <c:if test="${mealToEdit.description==null}">
    <table>
      <tr>
        <td>Date</td>
        <td><input type="datetime" name="data"></td>
      </tr>
      <tr>
        <td>Description</td>
        <td><input type="text" name="description"></td>
      </tr>
      <tr>
        <td>Calories</td>
        <td><input type="number" name="calories"></td>
      </tr>
      <tr>
        <td colspan="2">

          <input type="submit" name="submit"
                 value="Add Meal"/>
        </td>
      </tr>

    </table>
  </c:if>
  <c:if test="${mealToEdit.description!=null}">
    <table>
      <tr>
        <td>Date</td>
        <fmt:parseDate value="${mealToEdit.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="time" type="both"/>
        <fmt:formatDate pattern="yyy-MM-dd HH:mm" value="${time}" var="parsedTime"/>
        <td><input type="datetime" name="data" value="${parsedTime}"></td>
      </tr>
      <tr>
        <td>Description</td>
        <td><input type="text" name="description" value="${mealToEdit.description}"></td>
      </tr>
      <tr>
        <td>Calories</td>
        <td><input type="number" name="calories" value="${mealToEdit.calories}"></td>
      </tr>
      <tr>
        <td>ID</td>
        <td><input type="number" name="id" value="${mealToEdit.id}" readonly></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" name="submit"
                 value="Edit Meal"/>
        </td>
      </tr>
    </table>
  </c:if>
</form>
</body>
</html>
