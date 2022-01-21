package DataBase;

import model.Categoria;
import model.Horario;
import model.Parque;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO() {
        try{
            this.connection = ConnectDB .getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Categoria getCategoria(int idCategoria) {
        String sql = "SELECT * FROM Categorias WHERE idCategoria=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                Blob image = rs.getBlob(3);
                String descricao = rs.getString(4);

                return new Categoria(idCategoria, null,descricao);

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCategoria(int idParque,Categoria categoria){
        String sql = "INSERT INTO Categorias(idCategoria,Parques_idParque,Icone,Descricao) VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoria.getIdCategoria());
            stmt.setInt(2, idParque);
            stmt.setBlob(3, InputStream.nullInputStream());
            stmt.setString(4,categoria.getDescricao());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setCategoria(int idParque,Categoria categoria){
        String sql = "UPDATE Categorias Parques_idParque=?, Icone=?, Descricao=? WHERE idCategoria=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,categoria.getIdCategoria());
            stmt.setInt(2,idParque);
            stmt.setBlob(3, InputStream.nullInputStream());
            stmt.setString(4, categoria.getDescricao());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeCategoria(int id){
        String sql ="DELETE FROM Categorias WHERE idCategoria=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Categoria> getCategorias(int idParque) {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categorias WHERE Parques_idParque=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idParque);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int idCategoria = rs.getInt(1);
                Blob image = rs.getBlob(3);
                String descricao = rs.getString(4);


                Categoria categoria = new Categoria(idCategoria, null, descricao);
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public static void main(String args[]){
        CategoriaDAO dao = new CategoriaDAO();

        Categoria categoria = new Categoria(1,null,"bom lugar");


        dao.removeCategoria(1);

        System.out.println(dao.getCategorias(1));




    }

}
