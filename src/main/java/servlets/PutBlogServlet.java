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
import java.io.Serial;
import java.util.List;

@WebServlet("/editblog")
public class PutBlogServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 6L;
    private final ConnectionProperty prop;
    private final AuthorDAO authorDAO;
    private final BlogDAO blogDAO;

    public PutBlogServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        authorDAO = new AuthorDAO();
        blogDAO = new BlogDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authors;
        List<Blog> blogs;
        AuthorDAO authorDAO = new AuthorDAO();
        BlogDAO blogDAO = new BlogDAO();
        try {
            authors = authorDAO.findAll();
            request.setAttribute("authors", authors);
            blogs = blogDAO.findAll();
            request.setAttribute("blogs", blogs);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        String strId = request.getParameter("id");
        Integer editBlogId = (strId != null) ? Integer.parseInt(strId) : null;
        Blog editBlog;
        try {
            editBlog = blogDAO.findById(editBlogId);
            request.setAttribute("blogEdit", editBlog);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/editblog.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            Integer editBlogId = (strId != null) ? Integer.parseInt(strId) : null;
            String authorId = request.getParameter("authorField").split(" ")[0];
            authorId = authorId.substring(0, authorId.length() - 1);
            Author author = authorDAO.findById(Integer.parseInt(authorId));
            blogDAO.update(new Blog(editBlogId, request.getParameter("blogTitle"), request.getParameter("blogContent"), author));
        } catch (DAOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/blog/blogs");
    }
}
