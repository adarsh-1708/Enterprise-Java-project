<%-- 
    Document   : ogin
    Created on : 12 Sep, 2023, 1:10:01 PM
    Author     : aadar
--%>


<%@page import="java.time.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%LocalDate cd=LocalDate.now();
  LocalTime ct=LocalTime.now();%>
  <div style='display: flex; justify-content: space-between;'>
            <h4>Date :<%=cd%></h4>
            <h4>Time :<%=ct%></h4>
            </div>
       
 <center> <br>
 <form action='add' method='post'>
 <input type='submit' name='s1' value='Add New Client'><br><br>
 </form>
 <form action='inactivate' method='post'>
 <input type='submit' name='s2' value='In-Acticate Client'><br><br>
 </form>
 <form action='update' method='post'>
 <input type='submit' name='s3' value='Update Client Data'><br><br>
 </form>
 <form action='display' method='post'>
 <input type='submit' name='s4' value='Display Client Data'><br><br>
 </form>
 </center>