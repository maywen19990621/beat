import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
 
    public static void main(String[] args) throws SQLException {
         
        DBCon connection=new DBCon();
        //connection.getData();
        //connection.AllCUID();
        connection.update("C00004");
    }
}
 
class DBCon{
    private Connection con;
    private Statement st;
    private ResultSet rs;
     
    public DBCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?serverTimezone=UTC ","maywen","Maywen46280930");
            st= con.createStatement();
             
        }catch(Exception ex){
            System.out.println("Error: "+ex);
        }
    }
     
    public void getData() {
//        try {
//            String query = "select * from customer";
//            rs = st.executeQuery(query);
//            System.out.println("Records for Database");
//            while(rs.next()) {
//                String id = rs.getString("CU_ID");
//                String name = rs.getString("CU_name");
//                System.out.println("id= "+id+" name= "+name);
//            }
//        }catch(Exception ex) {
//            System.out.println(ex);
//        }
    	String sql = "insert into customer(CU_ID,CU_name,CU_PhoneNum,CU_Address,CU_Email,CU_Age,CU_Sex,Pro_ID) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "C00004");
            pstmt.setString(2, "Jerry");
            pstmt.setString(3, "0920847136");
            pstmt.setString(4, null);
            pstmt.setString(5, null);
            pstmt.setString(6, "20");
            pstmt.setString(7, "boy");
            pstmt.setString(8, "P0005");
            pstmt.execute();
            pstmt.close();
            System.out.println("success");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String AllCUID() {
    	String id="";
    	try {
        	String query = "select CU_ID from customer";
        	rs = st.executeQuery(query);
        	//System.out.println("Records for Database");
        	while(rs.next()) {
        		id += " "+rs.getString("CU_ID");
        		//String name = rs.getString("CU_name");
        		//System.out.println("id= "+id);
        	}
        	System.out.println(id);
        	//TODO
        	//if()
        }catch(Exception ex) {
        	System.out.println(ex);
        }
		return id;
    }
    
    public void update(String id) throws SQLException {
    	String All_id=AllCUID();
    	String proID="P0001";
    	Statement stmt = null;

    	if(All_id.indexOf(id)!=-1) {
    		stmt = con.createStatement();
    	      String sql = "UPDATE customer " +
    	    		  "SET Pro_ID =CONCAT(Pro_ID,' ,"+proID+"') WHERE CU_ID='"+id+"' ";
    	      stmt.executeUpdate(sql);
    	}
    	System.out.println("success");
    }
     
}
