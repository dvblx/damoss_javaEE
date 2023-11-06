package servlets;

import dao.AuthorDAO;
import dao.BlogDAO;
import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Author;
import models.Blog;

import java.io.IOException;
import java.util.List;

@WebServlet("/blogs")
public class BlogServlet extends HttpServlet {
    private static final long serialVersionUID = 3L;
    ConnectionProperty prop;
    public BlogServlet() throws IOException {super();prop = new ConnectionProperty();}

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors;
        List<Blog> blogs;
        AuthorDAO authorDAO = new AuthorDAO();
        BlogDAO blogDAO = new BlogDAO();
        try {
            authors = authorDAO.findAll();
            req.setAttribute("authors", authors);
            blogs = blogDAO.findAll();
            req.setAttribute("blogs", blogs);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("views/blog.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {doGet(req, resp);}
}
