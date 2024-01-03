import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/add"})
public class add extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        out.println("<html>"
                + "<body><center><form> <br>"
                + "<h1> Add Client Data </h1><br><br>"
                + "ID      <input type='text' name='t1'><br><br>"
                + "Name    <input type='text' name='t2'><br><br>"
                + "Mobile No <input type='number' name='t7'><br><br>"
                + "Address <input type='text' name='t3'><br><br>"
                + "Joining Date<input type='date' name='d1'><br><br>"
                +"Fees  <select id='s3' name='s3'>"
                + "<option>Paid"
                + "<option>Pending"
                + "</select><br><br>"
                + "Duration  <select id='s1' name='s1'>"
                + "<option>1 Month"
                + "<option>3 Months"
                + "<option>6 Months"
                + "<option>12 months"
                + "</select><br><br>"
                + "Status  <select id='s2' name='s2'>"
                + "<option>Left"
                + "<option>Active"
                + "</select><br><br>"
                + "<input type='submit' name='b1' value='ADD CLIENT'><br><br>"
                + "<input type='submit' name='b2' Value='Home' align='center'>"
                + "</center></form>"
                + "</body>"
                + "</html>");
        if(request.getParameter("b2")!=null)
           {
               RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
               rd.forward(request, response);
           }
        if(request.getParameter("b1")!=null)
        {
        String id=request.getParameter("t1");
        String name=request.getParameter("t2");
        String add=request.getParameter("t3");
        String date=request.getParameter("d1");
        String dur=request.getParameter("s1");
        String fee=request.getParameter("s3");
        String sta=request.getParameter("s2");
        String no=request.getParameter("t7");
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection c=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
        PreparedStatement ps=c.prepareStatement("Insert into IRONMAN(ID,NAME,ADDRESS,JOINING_DATE,FEES,DURATION,STATUS,MOBILE_NUMBER) values(?,?,?,?,?,?,?,?)");
        ps.setString(1,id);
        ps.setString(2,name);
        ps.setString(3,add);
        ps.setString(4,date);
        ps.setString(5,fee);
        ps.setString(6,dur);
        ps.setString(7,sta);
        ps.setString(8, no);
        int i=ps.executeUpdate();
        if(i>0)
        {
            out.println("<script>alert('Client Data Added Success');window.location.href='login.jsp';</script>");
          
        }
        else
        {
            out.println("<script>alert('Oops ! an error occured');</script>");
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
