import java.sql.*;

public class UserDao
{
    //=================================== FUN CONNESSIONE ===================================//
    /*
    * creazione di una funzione di connessione al db per non rimetere la connessione nel CRUD
    */
    public static Connection ConnectionDb()
    {
        Connection dbConnection = null;

        try
        {
            //driver di connessione al db
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creiamo la stringa di connessione
            String url = "jdbc:mysql://localhost:3306/controlloFinanze?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            // Otteniamo una connessione con username e password
            dbConnection = DriverManager.getConnection(url, "root", "1234");

        }
        catch (Exception e)
        {
            System.out.println("ERRORE CONNESSIONE");
        }
        return dbConnection;
    }

    //=================================== FUN RICERCA ===================================//
    /*
    * ricerca nel db l'esistenza di un utente e la sua password
    * restituisce TRUE se lo trova, FALSE se non lo trova
    */
    public static boolean Search(String inputUsername,String inputPassword)throws SQLException
    {
        PreparedStatement cmd = null;

        try
        {
            String qry = "SELECT * FROM users WHERE username= ? and password= ?";

            cmd = ConnectionDb().prepareStatement(qry);

            cmd.setString(1, inputUsername);
            cmd.setString(2,  inputPassword);

            try (ResultSet resultSet = cmd.executeQuery())
            {
                return resultSet.next(); // Restituisce true se esiste almeno un record
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERRORE RICERCA");
            return false; // Gestione degli errori
        }

    }

    //=================================== FUN REGISTRAZIONE ===================================//
    public static boolean Insert(String inputUsername, String inputPassword, double inputBalance) throws SQLException
    {
        //true = utente trovato
        if (Search(inputUsername,inputPassword))
        {
            //restituisce false se trova un utente gia presete e non va avanti
            return false;
        }
        else
        {
            PreparedStatement cmd = null;

            //inserimento effettivo di un nuovo record nel db
            try
            {
                String updateTableSQL = "INSERT INTO users(username, password, balance) VALUES(?,?, ?)";

                cmd = ConnectionDb().prepareStatement(updateTableSQL);

                cmd.setString(1, inputUsername);
                cmd.setString(2, inputPassword);
                cmd.setDouble(3, inputBalance);

                //esercuzione query
                cmd.executeUpdate();
            }
            catch (Exception e)
            {
                System.out.println("ERRORE INSERT");
            }
        }
        return true;
    }

    //=================================== FUN MOSTRA SALDO ===================================//
    public static String ViewBalance(String inputUsername, String inputPassword) throws SQLException
    {
        //se trova un utente
        if (Search(inputUsername, inputPassword))
        {
            PreparedStatement cmd = null;

            try
            {
                String qry = "SELECT * FROM users WHERE username= ? and password= ?";

                cmd = ConnectionDb().prepareStatement(qry);

                cmd.setString(1, inputUsername);
                cmd.setString(2, inputPassword);

                try (ResultSet resultSet = cmd.executeQuery())
                {
                    // Se c'è un risultato, returno il valore
                    if (resultSet.next())
                    {
                        return resultSet.getString("balance");
                    }
                }
            }
            catch (Exception e)
            {
                return "ERRORE VISUALIZZA BILANCIO";
            }
        }
        return "ERRORE, utente non trovato nella funzione mostra saldo di UserDao";
    }
}
