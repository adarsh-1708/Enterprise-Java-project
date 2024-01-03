import static java.awt.SystemColor.window;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/display"})
public class display extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection c=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            PreparedStatement ps=c.prepareStatement("select * from IRONMAN");
            ResultSet rs=ps.executeQuery();
         out.println("<center><br><br>"
                 + "<h1> IRONMAN GYM </h1><br><br>"
                 + "<table border='2'>"
                 + "<tr><th colspan='8'>Client Data</th></tr>"
                 + "<tr><td>ID</td><td>NAME</td><td>ADDRESS</td><td>JOINING_DATE</td><td>FEES</td><td>DURATION</td><td>STATUS</td><td>MOBILE NUMBER</td></tr>");
         while(rs.next()){
            out.println("<tr>"
                    + "<td>"+rs.getInt(1)+"</td>"
                    + "<td>"+rs.getString(2)+"</td>"
                    + "<td>"+rs.getString(3)+"</td>"
                    + "<td>"+rs.getString(4)+"</td>"
                    + "<td>"+rs.getString(5)+"</td>"
                    + "<td>"+rs.getString(6)+"</td>"
                    + "<td>"+rs.getString(7)+"</td>"
                    + "<td>"+rs.getString(8)+"</td>"
                    + "</tr>");
         }
          out.println("</table>"
                  + "<br><form><input type='submit' name='b1' Value='Home' align='center'></form>");
          if(request.getParameter("b1")!=null)
           {
               RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
               rd.forward(request, response);
           }
         rs.close();
         c.close();
         ps.close();
         out.println("</center>");  
        }
        catch(Exception e)
        {
                out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
