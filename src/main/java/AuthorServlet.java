import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Author;

import java.io.IOException;
@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    private static final long serialVersionUID = 4L;

    public AuthorServlet() {super();}

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author[] authors = new Author[]{
                new Author("Имя", "Фамилия", "foo1@bar.com"),
                new Author("Иван", "Иванов", "foo2@bar.com"),
                new Author("Петр", "Петров", "foo3@bar.com"),
                new Author("Сергей", "Сергеев", "foo4@bar.com"),
                new Author("Андрей", "Андреев", "foo5@bar.com")
        };
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("views/author.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
