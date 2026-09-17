package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.employee.dao.EmployeeDao;
import com.employee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private EmployeeDao dao = new EmployeeDao();

    // ---------------- GET: list / edit / delete ----------------
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.html");
            return;
        }

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(req, res);
                break;
            case "delete":
                deleteEmployee(req, res);
                break;
            default:
                showList(req, res);
        }
    }

    // ---------------- POST: add / update ----------------
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.html");
            return;
        }

        String action = req.getParameter("action");
        if ("update".equals(action)) {
            updateEmployee(req, res);
        } else {
            addEmployee(req, res);
        }
    }

    // ================= ADD =================
    private void addEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Employee e = new Employee();
        e.setName(req.getParameter("name"));
        e.setSalary(Double.parseDouble(req.getParameter("salary")));
        e.setDepartment(req.getParameter("department"));
        e.setCity(req.getParameter("city"));
        e.setMobile_number(req.getParameter("mobileNumber"));
        e.setEmail(req.getParameter("email"));
        e.setAge(Integer.parseInt(req.getParameter("age")));

        dao.addEmployee(e);
        res.sendRedirect("employee?action=list");
    }

    // ================= UPDATE =================
    private void updateEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Employee e = new Employee();
        e.setId(Integer.parseInt(req.getParameter("empId")));
        e.setName(req.getParameter("name"));
        e.setSalary(Double.parseDouble(req.getParameter("salary")));
        e.setDepartment(req.getParameter("department"));
        e.setCity(req.getParameter("city"));
        e.setMobile_number(req.getParameter("mobileNumber"));
        e.setEmail(req.getParameter("email"));
        e.setAge(Integer.parseInt(req.getParameter("age")));

        dao.updateEmployee(e);
        res.sendRedirect("employee?action=list");
    }

    // ================= DELETE =================
    private void deleteEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        dao.deleteEmployee(id);
        res.sendRedirect("employee?action=list");
    }

    // ================= LIST =================
    private void showList(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        ArrayList<Employee> list = dao.viewAllEmployee();

        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>All Employees</title>");
        out.println("<link rel='stylesheet' href='style.css'>");
        out.println("<style>");
        out.println("body { display:block; padding:30px; }");
        out.println(".wide { max-width:1100px; margin:0 auto; background:#fff; padding:25px 30px; border-radius:12px; box-shadow:0 6px 18px rgba(0,0,0,.08); }");
        out.println("table { width:100%; border-collapse:collapse; margin-top:20px; }");
        out.println("th, td { padding:10px; border-bottom:1px solid #e5e7eb; text-align:left; font-size:14px; }");
        out.println("th { background:#23395d; color:#fff; }");
        out.println("tr:hover { background:#f9fafb; }");
        out.println(".btn-sm { padding:6px 12px; font-size:12px; margin-right:6px; }");
        out.println(".btn-red { background:#ef4444; }");
        out.println(".btn-red:hover { background:#dc2626; }");
        out.println(".top-bar { display:flex; gap:10px; }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<div class='wide'>");
        out.println("<h2>All Employees</h2>");
        out.println("<div class='top-bar'>");
        out.println("<a href='addEmployee.html' class='btn'>+ Add New</a>");
        out.println("<a href='index.html' class='btn grey'>Home</a>");
        out.println("<a href='logout' class='btn grey'>Logout</a>");
        out.println("</div>");

        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th><th>Name</th><th>Salary</th><th>Dept</th>");
        out.println("<th>City</th><th>Mobile</th><th>Email</th><th>Age</th><th>Actions</th>");
        out.println("</tr>");

        if (list.isEmpty()) {
            out.println("<tr><td colspan='9' style='text-align:center;color:#888;'>No employees yet</td></tr>");
        } else {
            for (Employee e : list) {
                out.println("<tr>");
                out.println("<td>" + e.getId() + "</td>");
                out.println("<td>" + e.getName() + "</td>");
                out.println("<td>" + e.getSalary() + "</td>");
                out.println("<td>" + e.getDepartment() + "</td>");
                out.println("<td>" + e.getCity() + "</td>");
                out.println("<td>" + e.getMobile_number() + "</td>");
                out.println("<td>" + e.getEmail() + "</td>");
                out.println("<td>" + e.getAge() + "</td>");
                out.println("<td>");
                out.println("<a href='employee?action=edit&id=" + e.getId() + "' class='btn btn-sm'>Edit</a>");
                out.println("<a href='employee?action=delete&id=" + e.getId() + "' "
                        + "class='btn btn-sm btn-red' onclick=\"return confirm('Delete this employee?')\">Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }
        }

        out.println("</table>");
        out.println("</div>");
        out.println("</body></html>");
    }

    // ================= EDIT FORM =================
    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee e = dao.getEmployeeById(id);

        if (e == null) {
            res.sendRedirect("employee?action=list");
            return;
        }

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Edit Employee</title>");
        out.println("<link rel='stylesheet' href='style.css'>");
        out.println("</head><body>");
        out.println("<div class='card'>");
        out.println("<h2>Edit Employee</h2>");
        out.println("<form action='employee' method='post'>");
        out.println("<input type='hidden' name='action' value='update'>");
        out.println("<input type='hidden' name='empId' value='" + e.getId() + "'>");

        out.println("<label>Name</label>");
        out.println("<input type='text' name='name' value='" + safe(e.getName()) + "' required>");

        out.println("<label>Salary</label>");
        out.println("<input type='number' step='0.01' name='salary' value='" + e.getSalary() + "' required>");

        out.println("<label>Department</label>");
        out.println("<select name='department' required>");
        String[] depts = {"HR", "IT", "Finance", "Sales", "Marketing", "Operations"};
        for (String d : depts) {
            String sel = d.equals(e.getDepartment()) ? " selected" : "";
            out.println("<option value='" + d + "'" + sel + ">" + d + "</option>");
        }
        out.println("</select>");

        out.println("<label>City</label>");
        out.println("<input type='text' name='city' value='" + safe(e.getCity()) + "'>");

        out.println("<label>Mobile</label>");
        out.println("<input type='text' name='mobileNumber' value='" + safe(e.getMobile_number()) + "'>");

        out.println("<label>Email</label>");
        out.println("<input type='email' name='email' value='" + safe(e.getEmail()) + "'>");

        out.println("<label>Age</label>");
        out.println("<input type='number' name='age' value='" + e.getAge() + "'>");

        out.println("<div class='actions'>");
        out.println("<button type='submit'>Update</button>");
        out.println("<a href='employee?action=list' class='btn grey'>Cancel</a>");
        out.println("</div>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
    }

    // escapes apostrophes so a name like "O'Brien" doesn't break the HTML
    private String safe(String s) {
        return s == null ? "" : s.replace("'", "&#39;");
    }
}