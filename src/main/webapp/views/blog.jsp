<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Статьи</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Persons</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- jQuery -->
<script defer src="js/jquery.min.js"></script>
<!-- Bootstrap JS + Popper JS -->
<script defer src="js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<body>
 <div class="container-fluid">
 <jsp:include page="/views/header.jsp" />
 <div class="container-fluid">
 <div class="row justify-content-start ">
 <div class="col-8 border bg-light px-4">
 <h3>Список сотрудников</h3>
 <table class="table">
 <thead>
 <th scope="col">Заголовок</th>
 <th scope="col">Текст</th>
 <th scope="col">Автор</th>
 <th scope="col">Дата публикации</th>
 <th scope="col">Дата последнего обновления</th>
 <th scope="col">Редактировать</th>
 <th scope="col">Удалить</th>
 </thead>
 <tbody>
 <c:forEach var="b" items="${blogs}">
 <tr>
 <td>${b.getTitle()}</td>
 <td>${b.getContent()}</td>
 <td>${b.getAuthor()}</td>
 <td>${b.getCreationDate()}</td>
 <td>${b.getLastUpdateDate()}</td>
 <td width="20"><a href="#" role="button"
 class="btn btn-outline-primary">
 <img alt="Редактировать"
 src="images/icon-edit.png"></a>
 </td>
 <td width="20"><a href="#" role="button"
 class="btn btn-outline-primary">
 <img alt="Удалить"
 src="images/icon-delete.png"></a>
 </td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>
 <div class="col-4 border px-4">
 <form method="POST" action="">
 <h3>Новая статья</h3>
 <br>
 <div class="mb-3 row">
 <label for="blogTitle" class="col-sm-3 col-form-label">Заголовок</label>
 <div class="col-sm-7">
 <input type="text" class="form-control" id="blogTitle"name="blogTitle" />
 </div>
 </div>
 <div class="mb-3 row">
 <label for="blogContent"class="col-sm-3 col-form-label">Текст</label>
 <div class="col-sm-7">
 <input type="text" class="form-control" id="blogContent" name="blogContent" />
 </div>
 </div>
 <div class="mb-3 row">
 <label for="rolename" class="col-sm-3 col-form-label">Автор</label>
 <div class="col-sm-7">
 <select name="authorField" class="form-control">
 <option>Выберите автора</option>
 <c:forEach var="author" items="${authors}">
 <option value="${author}"> <c:out value="${author}"></c:out>
 </option>
 </c:forEach>
 </select>
 </div>
 </div>
 <p> <br>
 <button type="submit" class="btn btn-primary">Добавить</button>
 </p>
 </form>
 </div>
 </div>
 </div>
 <jsp:include page="/views/footer.jsp" />
 </div>
</body>
</html>