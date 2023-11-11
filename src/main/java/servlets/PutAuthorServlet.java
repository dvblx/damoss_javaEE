package servlets;

import dao.AuthorDAO;
import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Author;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/editauthor")
public class PutAuthorServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 5L;
    private final ConnectionProperty prop;
    private final AuthorDAO dao;

    public PutAuthorServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new AuthorDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        List<Author> authors;
        try {
            authors = dao.findAll();
            request.setAttribute("authors", authors);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        String strId = request.getParameter("id");
        Integer editAuthorId = (strId != null) ? Integer.parseInt(strId) : null;
        Author editAuthor;
        try {
            editAuthor = dao.findById(editAuthorId);
            request.setAttribute("authorEdit", editAuthor);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/editauthor.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Integer editAuthorId = (strId != null) ? Integer.parseInt(strId) : null;
        Author updateAuthor = new Author(editAuthorId, request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"));
        try {
            dao.update(updateAuthor);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/blog/authors");
    }
}

