package DataBase;

import model.Horario;
import model.Parque;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioDAO {
    private Connection connection;

    public HorarioDAO() {
        try {
            this.connection = ConnectDB.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Horario getHorario(int idHorario) {
        String sql = "SELECT * FROM Horarios WHERE idHorario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idHorario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LocalTime abertura = rs.getTime(3).toLocalTime();
                LocalTime encerramento = rs.getTime(4).toLocalTime();
                String dia = rs.getString(5);

                return new Horario(idHorario, abertura, encerramento, dia);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addHorario(int idParque, Horario horario) {
        String sql = "INSERT INTO Horarios(idHorario,Parques_idParque,HoraDeAbertura,HoraDeEncerramento,Dia) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, horario.getIdHorario());
            stmt.setInt(2, idParque);
            stmt.setTime(3, Time.valueOf(horario.getAbertura()));
            stmt.setTime(4, Time.valueOf(horario.getEncerramento()));
            stmt.setString(5, horario.getDia());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setHorario(int idParque, Horario horario) {
        String sql = "UPDATE Horarios Parques_idParque=?, HoraDeAbertura=?, HoraDeEncerramento=? WHERE idHorario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, horario.getIdHorario());
            stmt.setInt(2, idParque);
            stmt.setTime(3, Time.valueOf(horario.getAbertura()));
            stmt.setTime(4, Time.valueOf(horario.getEncerramento()));

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeHorario(int id) {
        String sql = "DELETE FROM Horarios WHERE idHorario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Horario> getHorarios(int idParque) {
        List<Horario> horarios = new ArrayList<>();
        String sql = "SELECT * FROM Horarios WHERE Parques_idParque=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idParque);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int idHorario = rs.getInt(1);
                LocalTime abertura = rs.getTime(3).toLocalTime();
                LocalTime encerramento = rs.getTime(4).toLocalTime();
                String dia = rs.getString(5);


                Horario horario = new Horario(idHorario, abertura, encerramento, dia);
                horarios.add(horario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horarios;
    }

    public static void main(String args[]){
        HorarioDAO dao = new HorarioDAO();

        Horario h = new Horario(1,LocalTime.of(8,0), LocalTime.of(19,20), "segunda");

        System.out.println(dao.getHorarios(1));


    }

}



