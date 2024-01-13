import java.util.ArrayList;

public class User
{
    //=================================== ATTRIBUTI ===================================//
    private String username;
    private String password;
    private Double balance;

    //=================================== COSTRUTTORE ===================================//
    public User(String username, String password , Double balance)
    {
        this.username =username;
        this.password=password;
        this.balance =balance;
    }

    //=================================== FUN AGGIUNGI SALDO ===================================//
    public void AddToBalance(double amount)
    {
        balance += amount;
    }

    //=================================== FUN SOTTRAI SALDO ===================================//
    public void SubFromBalance(double amount)
    {
        balance -= amount;
    }

    //=================================== GETTER ===================================//
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public Double getBalance()
    {
        return balance;
    }

    //=================================== SETTER ===================================//
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
