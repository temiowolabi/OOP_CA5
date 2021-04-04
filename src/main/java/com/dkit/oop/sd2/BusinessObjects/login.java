//package com.dkit.oop.sd2.BusinessObjects;
//
//import com.dkit.oop.sd2.Exceptions.DaoException;
//import com.dkit.oop.sd2.Server.MySqlDao;
//import com.dkit.oop.sd2.Server.MySqlStudentDao;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class Login {
//
//    private int caoNumber;
//    private String password;
//
//    public Login(int user, String pass)
//    {
//        this.caoNumber = user;
//        this.password = pass;
//    }
//
//    public String validate() throws DaoException {
//        String output=null;
//        MySqlDao main = new MySqlDao();
////        String database_url = main.Databaseurl();
////        String database_username = main.Databaseusername();
////        String database_password = main.Databasepassword();
//        MySqlStudentDao db_connection = new MySqlStudentDao();
//        java.sql.Connection connection = db_connection.findStudentByCaoNumberPassword();
//        Statement stmnt = null;
//        String query = "SELECT * FROM student WHERE caonumber=\""+caoNumber+"\" && password=\""+password+"\"";
//        try {
//            stmnt = db_connection.createStatement();
//            ResultSet rs = stmnt.executeQuery(query);
//            int count=0;
//            while (rs.next()){
//                count = count+1;
//            }
//            if (count>0){
//                output = "You are logged in.";
//            }
//            else{
//                output = "Login Failed.";
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//
//        }
//        return output;
//    }
//}