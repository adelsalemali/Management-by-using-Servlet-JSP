package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.User;


@WebServlet({"/list", "/new", "/edit", "/delete", "/insert", "/update"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao ;
	
	public void init() throws ServletException {
		userDao  = new UserDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			doGet(request, response);
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	
	 	
		String action = request.getServletPath();
		
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
			
		case "/insert":
			
			try {
				insertUser(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
			
		case "/delete":
			deleteUser(request , response);
			break;
			
			
		case "/edit":
			showEditForm(request , response);
			break;
		
			
		case "/update":
			try {
				updateUser(request , response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/list":
			listUser(request, response);
			break;
		}
		}
		private void showNewForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			dispatcher.forward(request, response);
		}
		// insert 
		private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
		{
			String name = request.getParameter("name");
			String email= request.getParameter("email");
			String country = request.getParameter("country");
			User newUser = new User(name , email , country);
		
			userDao.insertUser(newUser);
			response.sendRedirect("list");
		}
		

		//Delete User
		private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				userDao.deleteUser(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("list");
		}
		
		//Edit
		public void showEditForm(HttpServletRequest request, HttpServletResponse response)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			User existingUser;
			try {
				existingUser = userDao.selectUser(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
				request.setAttribute("user",existingUser );
				dispatcher.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		//update
		public void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String country = request.getParameter("country");
			
			User user = new User(id, name, email, country);
			userDao.updateUser(user);	
			response.sendRedirect("list");
		}
		

		public void listUser(HttpServletRequest request, HttpServletResponse response)
		{
			try {
				List<User> listUser = userDao.selsctAllUsers();
				request.setAttribute("listuser", listUser);
				RequestDispatcher  dispatcher = request.getRequestDispatcher("user-list.jsp");
				dispatcher.forward(request, response);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}


