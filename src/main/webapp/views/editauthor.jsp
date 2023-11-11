<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Авторы</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Roles</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->

<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- jQuery -->
<script defer src="js/jquery-3.6.4.js"></script>
<!-- Bootstrap JS + Popper JS -->
<script defer src="js/bootstrap.min.js"></script>
</head>
<body>
 <div class="container-fluid">
 <jsp:include page="header.jsp" />
 <div class="container-fluid">
 <div class="row justify-content-start ">
 <div class="col-8 border bg-light px-4">
 <h3>Список авторов</h3>
 <table class="table">
 <thead>
 <th scope="col">ID</th>
 <th scope="col">Фамилия</th>
 <th scope="col">Имя</th>
 <th scope="col">email</th>
 <th scope="col">Дата регистрации</th>
 <th scope="col">Редактировать</th>
 <th scope="col">Удалить</th>
 </thead>
 <tbody>
 <c:forEach var="author" items="${authors}">
 <tr>
 <td>${author.getAuthorId()}</td>
 <td>${author.getLastName()}</td>
 <td> ${author.getFirstName()}</td>
 <td>${author.getEmail()} </td>
 <td>${author.getRegistrationDate()} </td>
<td width="20">
<a href='<c:url value="/editauthor?id=${author.getAuthorId()}" />' role="button" class="btn btn-outline-primary">
<img alt="Редактировать" src="images/icon-edit.png">
</a></td>
<td width="20">
 <a href="<c:url value="/deleteauthor?id=${author.getAuthorId()}"/>" role="button" class="btn btn-outline-primary">
 <img alt="Удалить" src="images/icon-delete.png" onclick="return confirm('Удалить автора с ID:'+${author.getAuthorId()}+'?')">
 </a></td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>
 <div class="col-4 border px-4">
 <form method="POST" action="">
 <h3>Редактирование данных</h3>
 <br>
  <div class="mb-3">
  <label for="idAuthor" class="col-sm-3 col-form-label">ID</label>
  <div class="col-sm-6">
  <input type="text" class="form-control" readonly
  id="idperson" value="${authorEdit.getAuthorId()}" />
  </div>
 <div class="mb-3">
<label for="lastName" class="col-sm-3 col-form-label">Фамилия</label>
 <div class="col-sm-6">
 <input type="text" name="lastName" class="form-control" id="lastName" value="${authorEdit.getLastName()}"/>
 </div>
 </div>
 <div class="mb-3">
 <label for="firstName" class="col-sm-3 col-form-label">Имя</label>
  <div class="col-sm-6">
  <input type="text" name="firstName" class="form-control" id="firstName" value="${authorEdit.getFirstName()}"/>
  </div>
  </div>
  <div class="mb-3">
  <label for="email" class="col-sm-3 col-form-label">email</label>
   <div class="col-sm-6">
   <input type="text" name="email" class="form-control" id="email" value="${authorEdit.getEmail()}"/>
   </div>
   </div>
 <p>
 <br>
 <button type="submit" class="btn btn-primary">Изменить</button>
 <br>
 </p>
 </form>
 </div>
 </div>
 </div>
 <jsp:include page="footer.jsp" />
 </div>
</body>
</html>