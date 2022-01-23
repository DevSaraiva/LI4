package com.example.minhopark.DataBase;


import com.example.minhopark.model.SSParques.Categoria;
import com.example.minhopark.model.SSParques.Horario;
import com.example.minhopark.model.SSParques.Parque;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.util.Arrays;


public class ParqueDAO implements Serializable {
    private Connection connection;
    private CategoriaDAO categoriaDAO;
    private HorarioDAO horarioDAO;

    public ParqueDAO() {
        try {
            this.connection = ConnectDB.getConnection();
            this.categoriaDAO = new CategoriaDAO();
            this.horarioDAO = new HorarioDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Categoria> getCategorias(int parqueID) {

        try {
            String sql = "SELECT * FROM Parques_has_Categorias WHERE Parques_idParque=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, parqueID);
            ResultSet rs = stmt.executeQuery();
            List<Categoria> categorias = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString(2);
                Categoria categoria = categoriaDAO.getCategoria(id);
                categorias.add(categoria);
            }

            return categorias;

        } catch(SQLException e){

            e.printStackTrace();
        }
            return null;
    }



    public Parque getParque(int parqueID) {
        String sql = "SELECT * FROM Parques WHERE iDParque=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, parqueID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                Blob imagem = rs.getBlob(3);
                String filename = "parque" + id;
                //String filePath = ConnectDB.convertToFile(imagem, filename);
                String endereco = rs.getString(4);
                String coordenadas = rs.getString(5);
                int nrCriticas = rs.getInt(6);
                float rating = rs.getFloat(7);

                List<Categoria> categorias = this.getCategorias(parqueID);
                List<Horario> horarios = this.horarioDAO.getHorarios(parqueID);

                return new Parque(id,nome, null,endereco,coordenadas,nrCriticas,rating,horarios,categorias);

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCategoriaToPark(int parque, String categoria){
        String sql = "INSERT INTO Parques_has_Categorias(Parques_idParque,Categorias_idCategoria) VALUES(?,?)";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,parque);
            stmt.setString(2,categoria);
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean addParque(Parque parque){
        String sql = "INSERT INTO Parques(idParque,Nome,Imagem,Endereco,Coordenadas,NrCriticas,Rating) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,parque.getParqueID());
            stmt.setString(2,parque.getNome());
            stmt.setBlob(3, ConnectDB.convertFileContentToBlob(parque.getImage()));
            stmt.setString(4, parque.getEndereco());
            stmt.setString(5,parque.getCoordenadas());
            stmt.setInt(6,parque.getNumCriticas());
            stmt.setFloat(7,parque.getRating());
            stmt.execute();


            for(Categoria c : parque.getCategorias()){
                this.addCategoriaToPark(parque.getParqueID(),c.getNome());
            }

            for(Horario h : parque.getHorarios()){
                this.horarioDAO.addHorario(parque.getParqueID(),h);
            }



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
            stmt.setBlob(3, (InputStream) null);
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

    public boolean removeParqueHasCategoria(int id){
        String sql ="DELETE FROM Parques_has_Categorias WHERE Parques_idParque=?";
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


    public boolean removeParque(int id){

        removeParqueHasCategoria(id);
        this.horarioDAO.removeHorario(id);

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

    public List<Parque> getParquesFiltered(String idCategoria){
        List<Parque> parques = new ArrayList<>();
        String sql = "SELECT * FROM Parques_has_Categorias WHERE Categorias_idCategoria=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,idCategoria);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int idParque = rs.getInt(1);
                Parque parque = this.getParque(idParque);
                parques.add(parque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parques;
    }


    public List<Parque> getParques(){
        List<Parque> parques = new ArrayList<>();
        String sql = "SELECT * FROM Parques";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                String nome = rs.getString(2);
                Blob imagem = rs.getBlob(3);
                String filename = "parque" + id;
                //String filePath = ConnectDB.convertToFile(imagem, filename);
                String endereco = rs.getString(4);
                String coordenadas = rs.getString(5);
                int nrCriticas = rs.getInt(6);
                float rating = rs.getFloat(7);

                List<Categoria> categorias = this.getCategorias(id);
                List<Horario> horarios = this.horarioDAO.getHorarios(id);

                Parque parque = new Parque(id,nome,null,endereco,coordenadas,nrCriticas,rating,horarios,categorias);

                parques.add(parque);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parques;
    }


    public static void main(String args[]){


        ParqueDAO dao = new ParqueDAO();


        Categoria c1 = new Categoria("Parque_De_Lazer", null);
        Categoria c2 = new Categoria("Parque_Urbano" , null);
        Categoria c3 = new Categoria("Parque_Infatil", null);
        Categoria c4 = new Categoria("Parque_Campismo", null);
        Categoria c5 = new Categoria("Parque_Diversoes", null);
        Categoria c6 = new Categoria("Parque_Ecologico", null);
        Categoria c7 = new Categoria("Parque_Cidade", null);
        Categoria c8 = new Categoria("Parque_Nacional", null);
        Categoria c9 = new Categoria("Jardim", null);
        Categoria c10 = new Categoria("Reserva_Natural", null);

        
        Parque p0 = new Parque(0,"Parque da Ponte","./app/src/main/java/com/example/imgs/p0.jpg","N101 634, Braga","41.5416871,-8.418896199999999",4296,(float)4.0,new ArrayList<Horario>(Arrays.asList(new Horario(0,LocalTime.of(00,00,00), LocalTime.of(23,59,59),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c1,c2)));
        Parque p1 = new Parque(1,"Jardim da Avenida Central","./app/src/main/java/com/example/imgs/p1.jpg","Av. Central 116, 4710-249 Braga, Portugal","41.551536,-8.4216509",4670,(float)4.6,new ArrayList<Horario>(Arrays.asList(new Horario(1,LocalTime.of(00,00,00), LocalTime.of(23,59,59),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c9,c3)));
        Parque p2 = new Parque(2,"Parque da Rodovia","./app/src/main/java/com/example/imgs/p2.jpg","4715-027 Braga, Portugal","41.5528282,-8.4031463",869,(float)4.7,new ArrayList<Horario>(Arrays.asList(new Horario(2, LocalTime.of(8,00,00), LocalTime.of(22,00,00),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c7,c2,c3,c6)));
        Parque p3 = new Parque(3,"Parque do Tribunal Judicial de Braga","./app/src/main/java/com/example/imgs/p3.jpeg","R. Dr. Francisco Duarte 106110, 4715-156 Braga, Portugal","41.5482823,-8.4124714",0,(float) 0.0,new ArrayList<Horario>(Arrays.asList(new Horario(3, LocalTime.of(00,00,00), LocalTime.of(23,59,5),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c3)));;
        Parque p4 = new Parque(4,"Parque Infantil Areal de Cima","./app/src/main/java/com/example/imgs/p4.png","R. Q.ta de Passos 127-89, 4710-426 Braga, Portugal","41.5651917,-8.408154",0,(float)0.0,new ArrayList<Horario>(Arrays.asList(new Horario(4, LocalTime.of(00,00,00), LocalTime.of(23,59,5),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c3)));;
        Parque p5 = new Parque(5,"Parque do Monte do Picoto","./app/src/main/java/com/example/imgs/p5.jpg","Monte do Picoto, 4715-213 Braga, Portugal","41.53776879999999,-8.414022",803,(float)4.5,new ArrayList<Horario>(Arrays.asList(new Horario(5, LocalTime.of(8,00,00), LocalTime.of(23,00,00), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c2,c3,c5,c6)));;
        Parque p6 = new Parque(6,"Parque Fraião","./app/src/main/java/com/example/imgs/p6.jpg","R. Victor de Sá 59, 4715-213 Braga, Portugal","41.5373447,-8.4032868",1,(float)3.0,new ArrayList<Horario>(Arrays.asList(new Horario(6, LocalTime.of(00,00,00), LocalTime.of(23,59,5),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c2,c3)));;
        Parque p7 = new Parque(7,"Parque de Street Workout das Andorinhas","./app/src/main/java/com/example/imgs/p7.jpg", "Braga, Portugal","41.55745719999999,-8.4290608",7,(float)4.3,new ArrayList<Horario>(Arrays.asList(new Horario(7, LocalTime.of(00,00,00), LocalTime.of(23,59,5),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c2,c3)));;
        Parque p8 = new Parque(8,"Parque Infantil do Pachancho","./app/src/main/java/com/example/imgs/p8.jpg", "4710-345 Braga, Portugal","41.56118012989272,-8.415635129892722",90,(float)4.2,new ArrayList<Horario>(Arrays.asList(new Horario(8, LocalTime.of(00,00,00), LocalTime.of(23,59,5), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c3)));;
        Parque p9 = new Parque(9,"Jardim da Casa dos Biscaínhos","./app/src/main/java/com/example/imgs/p10.png", "R. Andrade Corvo 54, 4700-415 Braga, Portugal","41.5508225,-8.4302463",1,(float)5.0,new ArrayList<Horario>(Arrays.asList(new Horario(9, LocalTime.of(9,30,00), LocalTime.of(17,30,00), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c9)));
        Parque p10 = new Parque(10,"Parque das Camélias","./app/src/main/java/com/example/imgs/p11.jpg", "Braga, Portugal","41.539578,-8.423485699999999",3,(float)4.3,new ArrayList<Horario>(Arrays.asList(new Horario(10, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c2)));
        Parque p11 = new Parque(11,"Parque de merendas da Amarela","./app/src/main/java/com/example/imgs/p12.png", "Braga, Portugal","41.5374288,-8.4503404",130,(float)3.9,new ArrayList<Horario>(Arrays.asList(new Horario(11, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c1)));
        Parque p12 = new Parque(12,"Parque do  Lago do Bom Jesus","./app/src/main/java/com/example/imgs/p12.JPG", "4715, Braga, Portugal","41.5549268,-8.3752076",181,(float)4.7,new ArrayList<Horario>(Arrays.asList(new Horario(12, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c1)));
        Parque p13 = new Parque(13,"Parque Temático do Arnado","./app/src/main/java/com/example/imgs/p13.jpg", "Caminho da Oliveirinha, 4990-150 Pte. de Lima, Portugal","41.771224,-8.5886332",105,(float)4.4,new ArrayList<Horario>(Arrays.asList(new Horario(13, LocalTime.of(9,00,00), LocalTime.of(19,00,00), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c2)));
        Parque p14 = new Parque(14,"Parque Campismo de Braga","./app/src/main/java/com/example/imgs/p14.jpg", "Portugal, N101 12, Braga","41.53911567460526, -8.422014984657952",395,(float)3.8,new ArrayList<Horario>(Arrays.asList(new Horario(14, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c4)));
        Parque p15 = new Parque(15,"Parque Campismo de Guimarães","./app/src/main/java/com/example/imgs/p15.jpg", "Alameda de Rosas Guimarães 4805, 4805-156 Caldelas","41.484276818172376, -8.341926146026143",560,(float)4.0,new ArrayList<Horario>(Arrays.asList(new Horario(15, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c4)));
        Parque p16 = new Parque(16,"Parque da Cidade de Guimarẽs","./app/src/main/java/com/example/imgs/p16.jpg", "Guimarães,Braga, Portugal","41.44690860635031, -8.280651430684095",1199,(float)4.6,new ArrayList<Horario>(Arrays.asList(new Horario(16, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c1,c2,c3,c7)));
        Parque p17 = new Parque(17,"Parque do Santuário da Penha","./app/src/main/java/com/example/imgs/p17.jpg", "Penha, Guimarães, Portugal","41.43085660072449, -8.268921453345442",167,(float)4.7,new ArrayList<Horario>(Arrays.asList(new Horario(17, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c1,c2)));
        Parque p18 = new Parque(18,"Jardim do Castelo de Guimarães","./app/src/main/java/com/example/imgs/p18.png", "Caminho do Castelo, 4810-245 Guimarães","41.44710828794814, -8.290222049554965",4,(float)4.8,new ArrayList<Horario>(Arrays.asList(new Horario(18, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c2)));
        Parque p19 = new Parque(19,"Parque da Vila","./app/src/main/java/com/example/imgs/p19.jpg", "Via de S. Bento (Estrada da Madalena) 4990-205 Ponte de Lima","41.45462,-8.34301",223,(float)4.6,new ArrayList<Horario>(Arrays.asList(new Horario(19, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c2)));
        Parque p20 = new Parque(20,"Parque do Festival Internacional de Jardins","./app/src/main/java/com/example/imgs/p20.jpg", "Caminho São Gonçalo, 4990-150 Pte. de Lima","41.76594512916375, -8.59145632128427",1498,(float)4.5,new ArrayList<Horario>(Arrays.asList(new Horario(20, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c2,c9)));
        Parque p21 = new Parque(21,"Parque da Sameiro","./app/src/main/java/com/example/imgs/p21.jpeg", "R. Lago do Sameiro 3475-031","41.540574300810015, -8.371669634911084",84,(float)4.8,new ArrayList<Horario>(Arrays.asList(new Horario(21, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c1,c9)));
        Parque p22 = new Parque(22,"Parque de Valinhas","./app/src/main/java/com/example/imgs/p22.jpg", "R. da Breia 598, Carvoeiro","41.66398794545511, -8.6705284716176",89,(float)4.6,new ArrayList<Horario>(Arrays.asList(new Horario(22, LocalTime.of(00,00,00), LocalTime.of(23,59,59), "TODOS"))), new ArrayList<Categoria>(Arrays.asList(c1,c2)));
        Parque p23 = new Parque(23,"Parque Aventura","./app/src/main/java/com/example/imgs/p23.jpg", "Caminho da Alegria - Facha, 4990-600 Pte. de Lima","41.66398794545511,-8.6705284716176",89,(float)4.6,new ArrayList<Horario>(Arrays.asList(new Horario(23, LocalTime.of(9,00,00), LocalTime.of(19,00,00),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c1)));
        Parque p24 = new Parque(24,"Parque da Cidade de Viana","./app/src/main/java/com/example/imgs/p24.jpg","Viana do Castelo, Ponte de Lima, Portugal","41.6968733997282,-8.818275501874414",232,(float)4.3,new ArrayList<Horario>(Arrays.asList(new Horario(24, LocalTime.of(00,00,00), LocalTime.of(23,59,59),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c2,c6,c7)));
        Parque p25 = new Parque(25,"Parque Nacional Peneda-Gerês","./app/src/main/java/com/example/imgs/p25.jpg","Terras de Bouro, Braga, Portugal","41.79636333932538,-8.15010720575464",114,(float)4.6,new ArrayList<Horario>(Arrays.asList(new Horario(25, LocalTime.of(00,00,00), LocalTime.of(23,59,59),"TODOS"))),new ArrayList<Categoria>(Arrays.asList(c8,c10)));
        

        dao.removeParque(0);
        dao.removeParque(1);
        dao.removeParque(2);
        dao.removeParque(3);
        dao.removeParque(4);
        dao.removeParque(5);
        dao.removeParque(6);
        dao.removeParque(7);
        dao.removeParque(8);
        dao.removeParque(9);
        dao.removeParque(10);
        dao.removeParque(11);
        dao.removeParque(12);
        dao.removeParque(13);
        dao.removeParque(14);
        dao.removeParque(15);
        dao.removeParque(16);
        dao.removeParque(17);
        dao.removeParque(18);
        dao.removeParque(19);
        dao.removeParque(20);
        dao.removeParque(21);
        dao.removeParque(22);
        dao.removeParque(23);
        dao.removeParque(24);
        dao.removeParque(25);


        dao.addParque(p0);
        dao.addParque(p1);
        dao.addParque(p2);
        dao.addParque(p3);
        dao.addParque(p4);
        dao.addParque(p5);
        dao.addParque(p6);
        dao.addParque(p7);
        dao.addParque(p8);
        dao.addParque(p9);
        dao.addParque(p10);
        dao.addParque(p11);
        dao.addParque(p12);
        dao.addParque(p13);
        dao.addParque(p14);
        dao.addParque(p15);
        dao.addParque(p16);
        dao.addParque(p17);
        dao.addParque(p18);
        dao.addParque(p19);
        dao.addParque(p20);
        dao.addParque(p21);
        dao.addParque(p22);
        dao.addParque(p23);
        dao.addParque(p24);
        dao.addParque(p25);


    }


}

