import java.sql.*;
import java.util.ArrayList;

public class OperationDao
{
    //=================================== FUN CONNESSIONE ===================================//
    /*
     * creazione di una funzione di connessione al db per non rimettere la connessione nel CRUD
     */

    public static Connection ConnectionDb()
    {
        Connection dbConnectionOperation = null;

        try
        {
            //driver di connessione al db
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creiamo la stringa di connessione
            String url = "jdbc:mysql://localhost:3306/controlloFinanze?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            // Otteniamo una connessione con username e password
            dbConnectionOperation = DriverManager.getConnection(url, "root", "1234");

        }
        catch (Exception e)
        {
            System.out.println("ERRORE CONNESSIONE");
        }
        return dbConnectionOperation;
    }

    //=================================== FUN INSERIMENTO OPERAZIONE ===================================//
    public static boolean InsertOperation(String username, boolean operationType, double amount, String note) throws SQLException
    {
        PreparedStatement cmd = null;

        //inserimento effettivo di una nuovo record nel db
        try {
            String updateTableSQL = "INSERT INTO operation(username, operationType, amount, note) VALUES(?,?, ?, ?)";
            cmd = ConnectionDb().prepareStatement(updateTableSQL);

            cmd.setString(1, username);
            cmd.setBoolean(2, operationType);
            cmd.setDouble(3, amount);
            cmd.setString(4, note);

            //esercuzione query
            cmd.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("ERRORE INSERT");
        }
        return true;
    }

    public static void ViewOperation(String username) throws SQLException
    {
        PreparedStatement cmd = null;

        try
        {

            String qry = "SELECT * FROM operation WHERE username= ?";
            cmd = ConnectionDb().prepareStatement(qry);

            cmd.setString(1, username);

            //Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet
            ResultSet res = cmd.executeQuery();

            boolean esiste = res.next();// ---> la prima riga
            // --> seconda riga

            // Stampiamo i risultati riga per riga
            while (esiste)
            {
                /*
                * se operazione positiva, stampa un + davanti
                * altrimenti stampa un -
                */
                if(res.getBoolean("operationType"))
                {
                    System.out.println("+ "+res.getDouble("amount") + " " +res.getString("note"));
                }
                else
                {
                    System.out.println("- "+res.getDouble("amount") + " " +res.getString("note"));
                }

                esiste = res.next();
            }
        }
        catch (Exception e)
        {
            System.out.println("ERRORE VIEW OPERATION");
        }
    }

}
