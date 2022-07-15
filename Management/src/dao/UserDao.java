package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.User;

public class UserDao {

	private String jdbcUrl = "jdbc:mysql://localhost:3306/school?useSSL=false";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	private String jdbcUsername = "adel";
	private String jdbcpassword = "adel156651";
	
	
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users (name,email,country) values (?,?,?)";
	private static final String SELECT_USER_BY_ID= "select id,name,email,country from users where id = ?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete   from users where id = ? ";
	private static final String UPDATE_USERS_SQL = "update users set name = ? , email = ? , country = ? where id = ?";
	
	
	protected Connection getConnection()
	{
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcUrl , jdbcUsername, jdbcpassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection ;
		
	}
	//Insert user 
			public void insertUser(User user) throws SQLException {
				System.out.println("INSERT_USERS_SQL");
				try
				(
				  Connection con = getConnection();
				  PreparedStatement ps = con.prepareStatement(INSERT_USERS_SQL)){
					
					ps.setString(1, user.getName());
					ps.setString(2, user.getEmail());
					ps.setString(3, user.getCountry());
					System.out.println("PreparedStatement" + ps);
					ps.executeUpdate();
					
					
				}catch (SQLException e){
					e.printStackTrace();
				}
					
			
			}
			//select user by id
			
			public User selectUser(int id) throws SQLException {
				User user = null ;
				//step1:Establishing a Connection 
				try(Connection con  = getConnection()){
					//step2: Create a statement Using connection object
					PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);
					ps.setInt(1, id);
					System.out.println("selectUser"+ ps);
					//step 3 :execute the Query or update Query
					ResultSet rs = ps.executeQuery();
					
					
					//step 4: Process the resultSet object 
					while(rs.next())
					{
						String name = rs.getString("name");
						String email = rs.getString("email");
						String country = rs.getString("country");
						
						 user = new User(id,name , email , country);
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				return user ;
			}
			//select all users 
			public List<User> selsctAllUsers() throws SQLException
			{
				//Using try-with-resources to avoid closing resources (boiler plate code)
				List<User> users = new ArrayList<User>();
				//step1:Establishing a Connection 
				try(Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(SELECT_ALL_USERS);){
					System.out.println(ps);
					//step 3 :execute the Query or update Query
					ResultSet rs = ps.executeQuery();
					
					//step 4: Process the result object
					while (rs.next())
					{
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String email = rs.getString("email");
						String country = rs.getString("country");
						users.add(new User (id , name , email , country));

					}
				}catch (SQLException e) {
					
				}
			      return users ;
			}
			//update user 
			public boolean updateUser(User user) throws SQLException {
				boolean rowUpdate;
				try(
					Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(UPDATE_USERS_SQL);){
					 System.out.println("Update User" + ps);
					 ps.setString(1, user.getName());
					 ps.setString(2, user.getEmail());
					 ps.setString(3, user.getCountry());
					 ps.setInt(4, user.getId());
					 rowUpdate = ps.executeUpdate() > 0;
			}
				 return rowUpdate;
				 
}	
			//delete user 
			public boolean deleteUser(int id) throws SQLException {
				boolean rowDeleted;
				try(Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(DELETE_USERS_SQL);){
					System.out.println("Delete" + ps);
					ps.setInt(1, id);
					rowDeleted = ps.executeUpdate() > 0; 
				}
				
				return rowDeleted ;
			}
}

