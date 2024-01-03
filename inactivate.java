
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static sun.security.jgss.GSSUtil.login;

@WebServlet(urlPatterns = {"/inactivate"})
public class inactivate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>"
                    + "<head>"
                    + "<title>Login Page</title>" 
                    + "<body><center><form><h1> IRONMAN GYM </h1><br><br>"
                    + "ID    <input type='text' name='t1'><br><br>"
                    + "Status  <select id='s1' name='s1'>"
                    + "<option>Active"
                    + "<option>Left"
                    + "</select><br><br>"
                    + "<input type='submit' name='b1' value='UPDATE CLIENT'><br><br>"
                    + "<input type='submit' name='b2' Value='Home' align='center'>"
                    + "</form></center></body>");
            if(request.getParameter("b2")!=null)
           {
               RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
               rd.forward(request, response);
           }
            if(request.getParameter("b1")!=null)
            {
                String id=request.getParameter("t1");
                String sta=request.getParameter("s1");
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection c=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
                PreparedStatement ps=c.prepareStatement("update IRONMAN set STATUS=? where ID=?");
                
                ps.setString(1,sta);
                ps.setString(2,id);
                
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
