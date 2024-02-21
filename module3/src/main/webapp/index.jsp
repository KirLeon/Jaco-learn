<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Student Operations</title>
</head>
<body>
<h1>Student Operations</h1>
<br/>
<form action="students/lastName" method="get">
  <label for="lastName">Find Students by Last Name: </label>
  <input type="text" id="lastName" name="value" placeholder="Enter last name">
  <input type="submit" value="Search">
</form>
<br/>
<form action="students/firstName" method="get">
  <label for="firstName">Find Students by First Name: </label>
  <input type="text" id="firstName" name="value" placeholder="Enter first name">
  <input type="submit" value="Search">
</form>
<br/>
<form action="students/group" method="get">
  <label for="group">Find Students by Group: </label>
  <input type="text" id="group" name="value" placeholder="Enter group number">
  <input type="submit" value="Search">
</form>
<br/>
<form action="students/course" method="get">
  <label for="course">Find Students by Course: </label>
  <input type="number" id="course" name="value" placeholder="Enter course number">
  <input type="submit" value="Search">
</form>
<br/>
<form action="students/birthYear" method="get">
  <label for="birthYear">Find Students by Birth Year: </label>
  <input type="number" id="birthYear" name="value" placeholder="Enter birth year">
  <input type="submit" value="Search">
</form>
</body>
</html>
