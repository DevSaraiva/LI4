package DataBase;

import model.Categoria;
import model.Horario;
import model.Parque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParqueDAO {
    private Connection connection;

    public ParqueDAO() {
        try{
            this.connection = ConnectDB .getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Parque getParque(int parqueID) {
        String sql = "SELECT * FROM PARQUES WHERE iDParque=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, parqueID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                Blob imagem = rs.getBlob(3);
                String endereço = rs.getString(4);
                String coordenadas = rs.getString(5);
                int nrCriticas = rs.getInt(6);
                int rating = rs.getInt(7);


            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addParque(Parque parque){
        String sql = "INSERT INTO Parques(idParque,Nome,Imagem,Endereco,Coordenadas,NrCriticas,Rating) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,parque.getParqueID());
            stmt.setString(2,parque.getNome());
            stmt.setBlob(3, parque.getImage());
            stmt.setString(4, parque.getEndereco());
            stmt.setString(5,parque.getCoordenadas());
            stmt.setInt(6,parque.getNumCriticas());
            stmt.setFloat(7,parque.getRating());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setParque(Parque parque){
        String sql = "UPDATE Parques Nome=?, Imagem=?, Endereco=?, Coordenadas=?, NrCriticas=?, Rating=?,  WHERE idParque=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,parque.getParqueID());
            stmt.setString(2,parque.getNome());
            stmt.setBlob(3, parque.getImage());
            stmt.setString(4, parque.getEndereco());
            stmt.setString(5,parque.getCoordenadas());
            stmt.setInt(6,parque.getNumCriticas());
            stmt.setFloat(7,parque.getRating());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeParque(int id){
        String sql ="DELETE FROM Parques WHERE idParque=?";
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

    public List<Parque> getParques(){
        List<Parque> parques = new ArrayList<>();
        String sql = "SELECT * FROM Parques";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int parqueID = rs.getInt(1);
                String nome = rs.getString(2);
                Blob image = rs.getBlob(3);
                String endereco = rs.getString(4);
                String coordenadas = rs.getString(5);
                int numCriticas = rs.getInt(6);
                int rating = rs.getInt(7);
                Parque parque = new Parque(parqueID, nome,image,endereco,coordenadas,numCriticas,rating);
                parques.add(parque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parques;
    }

    public static void main(String args[]){
        ParqueDAO dao = new ParqueDAO();

        Parque p = new Parque(1,"Florestal",null,"rua matos","x:13,y:14,z:50",2,5);

        dao.addParque(p);
    }

}

