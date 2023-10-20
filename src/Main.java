import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String JDBC_URL = "jdbc:postgresql://localhost:5432/sapiencia";
    static final String USER = "scotia";
    static final String PASS = "scotiapass";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
            Statement statement = connection.createStatement();

            Scanner leer = new Scanner(System.in);
            int opcion;

            while(true) {
                System.out.println("\nMenu:");
                System.out.println("1. Mostrar todas las personas");
                System.out.println("2. Borrar una persona");
                System.out.println("0. Salir");

                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        mostrarPersonas(statement);
                        break;
                    case 2:
                        borrarPersona(statement, leer);
                        break;
                    case 0:
                        statement.close();
                        connection.close();
                        return;
                }
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void borrarPersona(Statement statement, Scanner leer) throws SQLException{
        System.out.println("Ingrese el id de la persona que desea borrar");
        int id = leer.nextInt();

        String sql = "DELETE FROM personas WHERE id = " + id;
        statement.executeUpdate(sql);
        System.out.println("La persona con id " + id + " fue borrado con exito.");
    }

    private static void mostrarPersonas(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM personas");

        System.out.println("\nResultado:");

        while(resultSet.next()){
            System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("nombre") + " - " + resultSet.getString("timestamp"));
        }
    }
}