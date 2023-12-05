/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.robynng.conexionbd;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Robyn
 */
public class ConexionBD {
    
    //establecemos las constantes para la conexión
    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "robynng";
    static final String PASS = "1234";
    static final String QUERY = "SELECT * FROM videojuegos;";

    public static void main(String[] args) {
        try 
            //creamos la conexión con el DriverManager y los datos
            (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QUERY);) {
            //llamadas a métodos
            System.out.println("¿Existe el juego Minecraft? " + buscaNombre("Minecraft"));
            lanzaConsulta("SELECT * FROM videojuegos WHERE Compañia = 'Riot';");
            nuevoRegistro("Mother 3", "JRPG", "2006-04-20", "HAL Laboratory", 10);
            nuevoRegistro();
            boolean b = eliminarRegistro("Fifa23");
            if (b) //mostraremos que se ha eliminado si es true
                System.out.println("Se ha eliminado el registro.");
            else System.out.println("No se ha eliminado el registro.");
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean buscaNombre(String nombre) { //método que busca un nombre en la tabla por parámetro y devuelve true si existe
        int result = 0;
        try 
            (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); //creamos conexión de nuevo
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM videojuegos WHERE Nombre = '" +nombre+ "';");)
            //creamos un Statement y le asignamos la ejecución de una consulta con ResultSet
        {
            
            while (rs.next()){
                result++; //incrementamos al encontrar una coincidencia
            }
            connection.close(); //cerramos conexión
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result > 0; //devolverá true si es mayor y false si es 0
    }
    
    public static void lanzaConsulta(String query) { //lanza una consulta por parámetro
        //variables que se usan en la consulta
        int id;
        String nombre = "", genero = "", compania = "";
        float precio = 0;
        Date fecha;
        try 
            (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); //creamos conexión de nuevo
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query))
            //creamos un Statement y le asignamos la ejecución de una consulta con ResultSet
        {
            System.out.println("ID\tNombre\t\t\t\tGénero\t\tFechaLanzamiento\tCompañía\tPrecio"); //columna header
            String tabulado = ""; //solución chapucera para la tabla
            while (rs.next()){ ////por cada fila encontrada obtenemos sus valores
                id = rs.getInt("Id");
                nombre = rs.getString("Nombre");
                if (nombre.length() < 8) //tabulados
                    tabulado = "\t\t\t\t";
                else if (nombre.length() < 16)
                    tabulado = "\t\t\t";
                else if (nombre.length() < 24)
                    tabulado = "\t\t";
                else    
                    tabulado = "\t";
                genero = rs.getString("Genero");
                fecha = rs.getDate("FechaLanzamiento");
                compania = rs.getString("Compañia");
                precio = rs.getFloat("Precio");
                System.out.println("--\t------\t\t\t\t------\t\t----------------\t--------\t-------"); //separación
                System.out.println(id+"\t"+nombre+tabulado+genero+"\t\t"+fecha+"\t\t"+compania+"\t\t"+precio); //fila con datos
            }
            connection.close(); //cerramos conexión
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
    
    //método que introduce un nuevo registro por parámetros
    public static void nuevoRegistro(String nombre, String genero, String fecha, String compania, float precio){
        //creamos el INSERT INTO con los valores pasados por parámetro
        String query = "INSERT INTO videojuegos(Nombre, Genero, FechaLanzamiento, Compañia, Precio)"
                + "VALUES ('"+nombre+"', '"+genero+"', '"+fecha+"', '"+compania+"', "+precio+");";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); //creamos conexión de nuevo
            Statement statement = connection.createStatement();) {
                statement.executeUpdate(query); //creamos un Statement al que asignamos la ejecución del INSERT INTO
                connection.close(); //cerramos conexión
                System.out.println("Registro añadido con éxito.");
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (InputMismatchException ex){
            ex.printStackTrace();
        }
    }
    
    public static void nuevoRegistro(){ //método que introduce un nuevo registro por pantalla
        //variables que se usan en la consulta
        String nombre = "", genero = "", compania = "", fecha = "";
        float precio = 0;
        
        Scanner scanner = new Scanner(System.in); //scanner para que el usuario introduzca por teclado
        System.out.println("-- Introduciendo nuevo registro --");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); //creamos conexión de nuevo
            Statement statement = connection.createStatement();){
            //pedimos por teclado los valores
                System.out.println("Nombre: ");
                nombre = scanner.nextLine();
                System.out.println("Género: ");
                genero = scanner.nextLine();
                System.out.println("Fecha (Formato YYYY-MM-DD): ");
                fecha = scanner.nextLine();
                System.out.println("Compañía: ");
                compania = scanner.nextLine();
                System.out.println("Precio: ");
                precio = scanner.nextFloat();
                //creamos el INSERT INTO con los valores especificados por el usuario
                String query = "INSERT INTO videojuegos(Nombre, Genero, FechaLanzamiento, Compañia, Precio)"
                + "VALUES ('"+nombre+"', '"+genero+"', '"+fecha+"', '"+compania+"', "+precio+");";
                //ejecutamos el INSERT INTO a través del Statement
                statement.executeUpdate(query);
                connection.close(); //cerramos conexión
                System.out.println("Registro añadido con éxito.");
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (InputMismatchException ex){
            ex.printStackTrace();
        }
    }
    
    public static boolean eliminarRegistro(String nombre){ //método que elimina un registro por parámetro
        //creamos DELETE con el nombre pasado por parámetro
        String query = "DELETE FROM videojuegos WHERE Nombre = '" +nombre+"';";
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); //creamos conexión de nuevo
            Statement statement = connection.createStatement();) {
            //creamos un Statement al que asignamos la ejecución del DELETE, y se lo asignamos a result
            //para que aumente si se ha eliminado algo
                result = statement.executeUpdate(query);
                connection.close(); //cerramos conexión
                //System.out.println("Registro eliminado con éxito.");                
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return result > 0; //será true si se ha eliminado el registro
    }
}
