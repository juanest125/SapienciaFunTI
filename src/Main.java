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
                System.out.println("3. Crear una persona");
                System.out.println("4. Editar una persona");
                System.out.println("5. Mostrar los comentarios de una persona");
                System.out.println("0. Salir");

                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        mostrarPersonas(statement);
                        break;
                    case 2:
                        borrarPersona(statement, leer);
                        break;
                    case 3:
                        crearPersona(statement, leer);
                        break;
                    case 4:
                        editarPersona(statement, leer);
                        break;
                    case 5:
                        mostrarComentarios(statement, leer);
                        break;
                    case 0:
                        statement.close();
                        connection.close();
                        leer.close();
                        return;
                }
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void mostrarComentarios(Statement statement, Scanner leer) throws SQLException{
        System.out.println("Ingrese el id de la persona propietaria de los comentarios");
        int id = leer.nextInt();

        String sql = "SELECT Comentarios.id, nombre, comentario, Comentarios.timestamp " +
                "FROM Comentarios INNER JOIN Personas ON Comentarios.persona_id = Personas.id " +
                "WHERE Personas.id = " + id;

        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("\nResultado:");
        while(resultSet.next()){
            System.out.println(resultSet.getInt("id") + " - " +
                    resultSet.getString("nombre") + " - " +
                    resultSet.getString("comentario") + " - " +
                    resultSet.getString("timestamp")  );
        }
    }

    private static void editarPersona(Statement statement, Scanner leer)  throws SQLException{
        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese el id de la persona que desea editar");
        int id = scan.nextInt();
        scan.nextLine();

        System.out.println("Ingrese el nombre de la persona que desea editar");
        scan.useDelimiter("\\n");
        String nombre = scan.nextLine();

        String sql = "UPDATE personas SET nombre = '" + nombre + "' WHERE id = " + id;
        statement.executeUpdate(sql);
        System.out.println("La persona fue editada con exito");
    }

    private static void crearPersona(Statement statement, Scanner leer) throws SQLException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la persona a crear");

        scan.useDelimiter("\\n");
        String nombre = scan.nextLine();

        String sql = "INSERT INTO Personas (nombre)" +
                "VALUES ('" + nombre + "')";
        statement.executeUpdate(sql);
        System.out.println("Persona creada satisfactoriamente");
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