/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Barrientos
 */
public class Empleado extends Persona {

    private String  fecha_inicio_labores, fecha_ingreso;
    private int id_puesto;
    private Conexion cn;

    public Empleado() {
    }

    
     public Empleado(int id, String nombres, String apellidos, String direccion, String telefono, String dpi, int genero,
             String fecha_nacimiento, int id_puesto, String fecha_inicio_labores, String fecha_ingreso) {
        super(id, genero, nombres, apellidos, direccion, telefono, dpi, fecha_nacimiento);
        this.fecha_inicio_labores = fecha_inicio_labores;
        this.fecha_ingreso = fecha_ingreso;
        this.id_puesto = id_puesto;
    }


   public String getFecha_inicio_labores() {
        return fecha_inicio_labores;
    }

    public void setFecha_inicio_labores(String fecha_inicio_labores) {
        this.fecha_inicio_labores = fecha_inicio_labores;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

 

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT a.id_empleado as id, a.nombres, a.apellidos, a.direccion, a.telefono, a.dpi,(CASE WHEN a.genero = 1 THEN 'Hombre' WHEN a.genero = 0 THEN 'Mujer' END) AS sexo, a.genero, a.fecha_nacimiento, b.puesto, a.fecha_inicio_labores , a.fecha_ingreso, a.id_puesto FROM empleados a INNER JOIN puestos b ON a.id_puesto = b.id_puesto ORDER BY a.id_empleado;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"Id", "Nombres", "Apellidos", "Dirección", "Teléfono", "DPI", "Genero", "Fecha_Nacimiento", "Puesto", "Fecha_Inicio_Labores", "Fecha_Ingreso", "Id_Puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[13];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                datos[5] = consulta.getString("dpi");
                datos[6] = consulta.getString("sexo");
                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getString("puesto");
                datos[9] = consulta.getString("fecha_inicio_labores");
                datos[10] = consulta.getString("fecha_ingreso");
                datos[11] = consulta.getString("id_puesto");
                datos[12] = consulta.getString("genero");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;
    }

    @Override
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO empleados (nombres, apellidos, direccion, telefono, dpi, genero, fecha_nacimiento, id_puesto, fecha_inicio_labores, fecha_ingreso) VALUES(?,?,?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDpi());
            parametro.setInt(6, getGenero());
            parametro.setString(7, getFecha_nacimiento());
            parametro.setInt(8, getId_puesto());
            parametro.setString(9,getFecha_inicio_labores());
            parametro.setString(10,getFecha_ingreso());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE empleados SET nombres  = ? , apellidos = ? , direccion = ? , telefono = ? , dpi = ? , genero = ? , fecha_nacimiento = ? , id_puesto = ? ,fecha_ingreso = ?,fecha_inicio_labores = ?  WHERE id_empleado = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDpi());
            parametro.setInt(6, getGenero());
            parametro.setString(7, getFecha_nacimiento());
            parametro.setInt(8, getId_puesto());
            parametro.setString(9,getFecha_inicio_labores());
            parametro.setString(10,getFecha_ingreso());
            parametro.setInt(11,getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM empleados WHERE id_empleado = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

 

}
