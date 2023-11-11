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

@WebServlet("/authors")
public class GetPostAuthorServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 3L;
    private final ConnectionProperty prop;
    private final AuthorDAO dao;

    public GetPostAuthorServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new AuthorDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authors;
        try {
            authors = dao.findAll();
            request.setAttribute("authors", authors);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("views/author.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            dao.insert(new Author(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email")));
        } catch (DAOException e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }
}

