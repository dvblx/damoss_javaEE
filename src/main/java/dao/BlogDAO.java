package dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import exception.DAOException;
import models.Author;
import models.Blog;

public class BlogDAO implements RepositoryDAO<Blog> {
    private static final String SELECT_ALL = "select b.blog_id, b.blog_title, b.blog_content, b.creation_date, b.last_update_date," +
            "a.author_id, a.firstname, a.lastname, a.email, a.registration_date  from blog b join author a on b.author_id = a.author_id";

    private static final String SELECT_ONE_BY_ID = SELECT_ALL + " WHERE blog_id = ?";
    private static final String INSERT_ONE = "INSERT INTO BLOG(blog_title, blog_content, creation_date," +
            " last_update_date, author_id) values(?, ?, ?, ?, ?)";
    private static final String EDIT_ONE = "UPDATE blog SET blog_title = ?, blog_content = ?, last_update_date = ?, author_id = ? WHERE blog_id = ?";
    private static final String DELETE_ONE = "DELETE FROM blog WHERE blog_id = ?";
    // Создание соединения с базой данных
    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {return builder.getConnection();}

    public BlogDAO() {}

    @Override
    public Long insert(Blog blog) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT_ONE)) {
            Long Id = -1L;
            pst.setString(1, blog.getTitle());
            pst.setString(2, blog.getContent());
            pst.setDate(3, Date.valueOf(blog.getCreationDate()));
            pst.setDate(4, Date.valueOf(blog.getLastUpdateDate()));
            pst.setInt(5, blog.getAuthor().getAuthorId());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("id");
            }
            gk.close();
            return Id;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Blog blog) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(EDIT_ONE)) {
            pst.setString(1, blog.getTitle());
            pst.setString(2, blog.getContent());
            pst.setDate(3, Date.valueOf(blog.getLastUpdateDate()));
            pst.setInt(4, blog.getAuthor().getAuthorId());
            pst.setInt(5, blog.getBlogId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Integer Id) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(DELETE_ONE)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Blog findById(Integer Id) throws DAOException {
        Blog blog = new Blog();
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ONE_BY_ID);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {blog = fillBlog(rs);}
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return blog;
    }

    @Override
    public List<Blog> findAll() throws DAOException {
        List<Blog> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {list.add(fillBlog(rs));}
            rs.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    private Blog fillBlog(ResultSet rs) throws SQLException, DAOException {
        return new Blog(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDate(4).toLocalDate(),
                rs.getDate(5).toLocalDate(),
                new Author(
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10).toLocalDate()
                )
        );
    }
}
