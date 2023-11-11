package servlets;

import dao.BlogDAO;
import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/deleteblog")
public class DeleteBlogServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ConnectionProperty prop;
    private final BlogDAO dao;

    public DeleteBlogServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new BlogDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Integer deleteid = (strId != null) ? Integer.parseInt(strId) : null;
        try {
            dao.delete(deleteid);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/blog/blogs");
    }
}
