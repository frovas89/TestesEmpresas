package br.com.frovas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseController {

	 private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

	public static void createMemoryDatabase() throws SQLException {

		Connection connection = getConnection();
        PreparedStatement createPS = null;
//        PreparedStatement insertPreparedStatement = null;
//        PreparedStatement selectPreparedStatement = null;

//        String CreateQuery = "CREATE TABLE MOVIE(id int primary key, year varchar(255), tilte varchar(255), winner boolean)";

        String query = "CREATE TABLE tb_movie(id INT PRIMARY KEY AUTO_INCREMENT, "
        										+ "m_year VARCHAR(4), "
        										+ "m_title VARCHAR(255), "
        										+ "m_winner BOOLEAN);";
        query += "CREATE SEQUENCE tb_movie_pk_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999;";
//        query += "CREATE SEQUENCE tb_movie_pk_sequence START WITH 1 INCREMENT BY 1;";
//        query += "CREATE SEQUENCE tb_movie_pk_sequence;";

        query += "CREATE TABLE tb_producer(id INT PRIMARY KEY AUTO_INCREMENT, p_name VARCHAR(255));";

        query += "CREATE SEQUENCE tb_producer_pk_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999;";
//        query += "CREATE SEQUENCE tb_producer_pk_sequence START WITH 1 INCREMENT BY 1;";
//        query += "CREATE SEQUENCE tb_producer_pk_sequence;";

        query += "CREATE TABLE rl_movie_producer(id_movie INTEGER,"
        										+ "id_producer INTEGER ,"
        										+ "FOREIGN KEY(id_movie) REFERENCES tb_movie,"
        										+ "FOREIGN KEY(id_producer) REFERENCES tb_producer );";



        try {
            connection.setAutoCommit(false);

            createPS = connection.prepareStatement(query);
            createPS.executeUpdate();
            createPS.close();

            System.out.println("Base de dados criada com sucesso!");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
	}


//	public static void insertMovie(Movie movie) throws SQLException {
//
//		Connection connection = getConnection();
//        PreparedStatement insertPS = null;
//
//        String query = "INSERT INTO tb_movie" + "(m_year, m_title, m_winner) values (?, ?, ?)";
//
//        try {
//            connection.setAutoCommit(false);
//
//
//            insertPS = connection.prepareStatement(query);
//            insertPS.setInt(1, movie.getYear());
//            insertPS.setString(2, movie.getTitle());
//            insertPS.setBoolean(3, movie.getWinner());
////            if(movie.getWinner() == null) {
////                insertPS.setBoolean(3, Boolean.FALSE);
////            } else {
////                insertPS.setBoolean(3, Boolean.TRUE);
////            }
//            insertPS.executeUpdate();
//            insertPS.close();
//
//            System.out.println("Objeto Movie inserido na tabela: "+movie.toString());
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connection.close();
//        }
//	}
//
//	public static List<Movie> listMoviesByYear(Integer year) throws SQLException {
//
//		Connection connection = getConnection();
//        PreparedStatement selectPS = null;
//
//        String sql = "SELECT * FROM tb_movie WHERE m_year = ?";
//
//        try {
//            connection.setAutoCommit(false);
//
//            selectPS = connection.prepareStatement(sql);
//            selectPS.setInt(1, year);
//            ResultSet rs = selectPS.executeQuery();
//
//            List<Movie> movies = new ArrayList<Movie>();
//            while (rs.next()) {
//    			Movie movie = new Movie();
//    			movie.setId(Long.valueOf(rs.getLong(1)));
//    			movie.setYear(Integer.valueOf(rs.getInt(2)));
//    			movie.setTitle(rs.getString(3));
//    			movie.setWinner(rs.getBoolean(4));
//
//    			movies.add(movie);
//            }
//            selectPS.close();
//
//            connection.commit();
//
//            return movies;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connection.close();
//        }
//		return null;
//	}
//
//	public static List<Movie> listWinnerMovies() throws SQLException {
//
//		Connection connection = getConnection();
//        PreparedStatement selectPS = null;
//
//        String sql = "SELECT * FROM tb_movie WHERE m_winner IS TRUE";
//
//        try {
//            connection.setAutoCommit(false);
//
//            selectPS = connection.prepareStatement(sql);
//            ResultSet rs = selectPS.executeQuery();
//
//            List<Movie> movies = new ArrayList<Movie>();
//            while (rs.next()) {
//    			Movie movie = new Movie();
//    			movie.setId(Long.valueOf(rs.getLong(1)));
//    			movie.setYear(Integer.valueOf(rs.getInt(2)));
//    			movie.setTitle(rs.getString(3));
//    			movie.setWinner(rs.getBoolean(4));
//
//    			movies.add(movie);
//            }
//            selectPS.close();
//
//            connection.commit();
//
//            return movies;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connection.close();
//        }
//		return null;
//	}

    private static Connection getConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

}
