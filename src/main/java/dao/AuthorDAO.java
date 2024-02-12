package dao;

import exception.DAOException;
import models.Author;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AuthorDAO implements RepositoryDAO<Author>{
    private static final String SELECT_ALL = "select author_id, firstname, lastname, email, registration_date from author";

    private static final String SELECT_ONE_BY_ID = SELECT_ALL + " WHERE author_id = ?";
    private static final String INSERT_ONE = "INSERT INTO author(firstname, lastname, email, registration_date)" +
            " values(?, ?, ?, ?)";
    private static final String EDIT_ONE = "UPDATE author SET firstname = ?, lastname = ?, email = ? WHERE author_id = ?";
    private static final String DELETE_ONE = "DELETE FROM author WHERE author_id = ?";
    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();}

    public AuthorDAO() {}

    @Override
    public Long insert(Author author) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT_ONE)) {
            Long Id = -1L;
            pst.setString(1, author.getFirstName());
            pst.setString(2, author.getLastName());
            pst.setString(3, author.getEmail());
            pst.setDate(4, Date.valueOf(author.getRegistrationDate()));
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
    public void update(Author author) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(EDIT_ONE)) {
            pst.setString(1, author.getFirstName());
            pst.setString(2, author.getLastName());
            pst.setString(3, author.getEmail());
            pst.setInt(4, author.getAuthorId());
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
    public Author findById(Integer Id) throws DAOException {
        Author author = new Author();
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ONE_BY_ID);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {author = fillAuthor(rs);}
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return author;
    }

    @Override
    public List<Author> findAll() throws DAOException {
        List<Author> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {list.add(fillAuthor(rs));}
            rs.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }
    private Author fillAuthor(ResultSet rs) throws SQLException, DAOException {
        return new Author(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getDate(5).toLocalDate()
        );
    }
}
