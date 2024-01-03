import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/update"})
public class update extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        try{
            out.println("<html><body><center><form>"
                    + "<h1>IRONMAN</h1><br><br>"
                    + "User ID <input type='text' name='t1'><br><br>"
                    + "Address <input type='text' name='t2'><br><br>"
                    + "Mobile No<input type='number' name='t3'><br><br>"
                    + "Fees <select name='s1'>"
                    + "<option>Pending"
                    + "<option>Paid"
                    + "</select><br><br>"
                    + "Status<select name='s2'>"
                    + "<option>Active"
                    + "<option>Left"
                    + "</select><br><br>"
                    + "<input type='submit' value='UPDATE' name='b1'><br><br>"
                    + "<input type='submit' value='Home' name='b2'>");
           out.println("</form></center></body></html>");
           if(request.getParameter("b2")!=null)
           {
               RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
               rd.forward(request, response);
           }
          if(request.getParameter("b1")!=null)
            {
                String id=request.getParameter("t1");
                String add=request.getParameter("t2");
                String fee=request.getParameter("s1");
                String sta=request.getParameter("s2");
                String no=request.getParameter("t3");
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection c=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
                PreparedStatement ps=c.prepareStatement("update IRONMAN set ADDRESS=?,FEES=?,STATUS=?,MOBILE_NUMBER=? where ID=?");
                
                ps.setString(1,add);
                ps.setString(2,fee);
                ps.setString(3,sta);
                ps.setString(4,no);
                ps.setString(5,id);
                
                int i=ps.executeUpdate();
                if(i>0)
                {
                    out.println("<script>alert('Data Updated Successfully ......'); window.location.href='login.jsp'</script>");
                    
                }
                else{
                    out.println("<script>alert('Oops! Error Occured ......');</script>");
                }
            }
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }

  
}
