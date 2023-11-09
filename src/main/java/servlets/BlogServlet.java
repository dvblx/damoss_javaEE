package servlets;

import dao.AuthorDAO;
import dao.BlogDAO;
import dao.ConnectionProperty;
import exception.DAOException;
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
    private final ConnectionProperty prop;
    private final AuthorDAO authorDAO;
    private final BlogDAO blogDAO;

    public BlogServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        authorDAO = new AuthorDAO();
        blogDAO = new BlogDAO();
    }

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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String authorId = req.getParameter("authorField").split(" ")[0];
            authorId = authorId.substring(0, authorId.length() - 1);
            Author author = authorDAO.findById(Integer.parseInt(authorId));
            blogDAO.insert(new Blog(req.getParameter("blogTitle"), req.getParameter("blogContent"), author));
        } catch (DAOException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}
