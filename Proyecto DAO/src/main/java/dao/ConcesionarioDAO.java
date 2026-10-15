package dao;

import com.prog11.tarea.modelo.Concesionario;

import java.sql.*;
import java.util.ArrayList;

public class ConcesionarioDAO {

//Almacenamos en variables las consultas y las acciones que vamos a usar
    private static final String INSERT = "INSERT INTO concesionarios (id, nombre, localizacion, capacidad_max) VALUES (?, ?, ?,?)";
    private static final String SELECT_ALL = "SELECT * FROM concesionarios";
    private static final String SELECT_BY_ID = "SELECT * FROM concesionarios WHERE id = ?";
    private static final String UPDATE = "UPDATE concesionarios SET nombre = ?, localizacion = ?, capacidad_max = ? WHERE identificador = ?";
    private static final String DELETE = "DELETE FROM concesionarios WHERE id = ?";



    //Creamos un metodo para obtener el id

    public int obtenerIdSiguiente() throws SQLException {
        //declaramos la variable y los objetos de conexión,y consultas
        int identificador = 0;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        //Creamos el try para atrapar las excepciones
        try {
            //creamos la conexión, y la consulta para sacar el ultimo id y le ponga +1
            conn = conexionBD.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT MAX(id)+1 FROM concesionarios");

            //condicional que la tabla de la consulta, en este caso le indicamos que nos devuelva la primera columna de la fila.
            if (rs.next()) {
                identificador = rs.getInt(1);
            }

        //usamos finally para tanto si hay una excepción como si no, cierre los objetos.
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


        finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (conn != null)
                conexionBD.closeConnection(conn);
        }
        return identificador;
    }

    public ArrayList<Concesionario> consultarConcesionario() throws SQLException {
        ArrayList<Concesionario> lista = new ArrayList<Concesionario>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = conexionBD.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SELECT_ALL);

            while (rs.next()) {
                Concesionario c = new Concesionario();

                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setLocalizacion(rs.getString("localizacion"));
                c.setCapacidadMaxima(rs.getInt("capacidad_max"));

                lista.add(c);
            }

        }finally{
            if (st!=null)
                st.close();
            if (rs!= null)
                rs.close();
            if (conn != null)
                conexionBD.closeConnection(conn);

        }

        return lista;
    }

    public void eliminarConcesionario(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conexionBD.getConnection();
            ps = conn.prepareStatement(DELETE);

            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conexionBD.closeConnection(conn);
        }
    }

        //metodo para insertar con prepareStatement
    public void insertarConcesionario(Concesionario concesionario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conexionBD.getConnection();

            //uso de preparestatement, usando la variable declarada anteriormente de INSERT, seguidamente pasamos todos los parametros al objeto para almacenarlo
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, concesionario.getId());
            ps.setString(2, concesionario.getNombre());
            ps.setString(3, concesionario.getLocalizacion());
            ps.setInt(4, concesionario.getCapacidadMaxima());
            // Conversión de LocalDate a java.sql.Date
            // ps.setDate(4, Date.valueOf(cliente.getFecha()));

            ps.executeUpdate();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conexionBD.closeConnection(conn);
        }


    }

}
