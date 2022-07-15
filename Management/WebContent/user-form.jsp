<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="styleuser.css">
</head>
<body>

<header>

  
  
  
</header>

<div class="navbar">
      <h1 class="back">The Management Application</h1>
            <ul>
                   <li> <a href="<c:url value="/list"/>" >Users List</a>
            </ul>
            </div>

                <div class="countiner">
                     
                          
                                <c:if test="${user != null}">
                                    <form action="<c:url value="/update"/>" method="post">
                                </c:if>
                                
                                <c:if test="${user == null}">
                                    <form action="<c:url value="/insert"/>" method="post">
                                </c:if>
                                
                                <caption>
                                
                                <h2>
                                    <c:if test="${user != null }">
                                    Edit User
                                    </c:if>
                                    
                                    <c:if test="${user == null }">
                                    Add new User
                                    </c:if>
                                </h2>
                                </caption>
                                <c:if test="${user != null}">
                 
                     <input type="hidden" name="id" value="${user.id}">
                 </c:if>
                 
                 <fieldset>
                      <label>User Name </label>
                      <input type="text" value="<c:out value='${user.name}'/>" name="name" required="required">
                 
                 
                 
                      <label>User Email </label>
                      <input type="text" value="<c:out value='${user.email}'/>" name="email" >
                 
                 
                 
                      <label>User Country </label>
                      <input type="text" value="<c:out value='${user.country}'/>" name="country" >
                 
</fieldset>
                                <button type="submit">Save</button>
                                
                                </form>
                          </div>
                     
               
                 
                 
</body>
</html>