import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Author;
import models.Blog;

import java.io.IOException;

@WebServlet("/blogs")
public class BlogServlet extends HttpServlet {
    private static final long serialVersionUID = 3L;

    public BlogServlet() {super();}

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author[] authors = new Author[]{
                new Author("Имя", "Фамилия", "foo1@bar.com"),
                new Author("Иван", "Иванов", "foo2@bar.com"),
                new Author("Петр", "Петров", "foo3@bar.com"),
                new Author("Сергей", "Сергеев", "foo4@bar.com"),
                new Author("Андрей", "Андреев", "foo5@bar.com")
        };
        Blog[] blogs = new Blog[]{
                new Blog("blog1", "text1", authors[0]),
                new Blog("blog2", "text2", authors[1]),
                new Blog("blog3", "text3", authors[2]),
                new Blog("blog4", "text4", authors[3]),
                new Blog("blog5", "text5", authors[4]),
        };
        req.setAttribute("authors", authors);
        req.setAttribute("blogs", blogs);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/blog.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {doGet(req, resp);}
}
