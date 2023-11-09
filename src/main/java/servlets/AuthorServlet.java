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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    private static final long serialVersionUID = 4L;
    private final ConnectionProperty prop;
    private final AuthorDAO dao;

    public AuthorServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new AuthorDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors;
        try {
            authors = dao.findAll();
            req.setAttribute("authors", authors);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("views/author.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dao.insert(new Author(req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("email")));
        } catch (DAOException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}

