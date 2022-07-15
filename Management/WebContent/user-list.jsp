<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/list.css"/>
</head>
<body>

<div class="navbar">
      <h1 class="back">The Management Application</h1>
            <ul>
            
              <li><a href="<%=request.getContextPath()%>/new" >Users</a></li>
             
             </ul>
</div>



          
          
      <a class="myLink" href="<%=request.getContextPath()%>/new">Add New User</a>
      
      
             
         

     <table>
           <thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Country</th>
						<th>Actions</th>
					</tr>
				</thead>
				
				<tbody>
				          <c:forEach var="user" items="${listuser}">
				                 <tr>
				                    <td><c:out value="${user.id}"/></td>
				                    <td><c:out value="${user.name}"/></td>
				                    <td><c:out value="${user.email}"/></td>
				                    <td><c:out value="${user.country}"/></td>
				                    
				                    <td><a href="edit?id=<c:out value='${user.id}'/>">Edit</a>
				                    
				                    &nbsp; &nbsp; &nbsp; &nbsp;
				                    
				                    <td><a href="delete?id=<c:out value='${user.id}'/>">Delete</a>
				                 </tr>
				          </c:forEach>
				</tbody>
           
            
     </table>
</body>
</html>