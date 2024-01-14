import java.io.IOException;
import java.util.ArrayList;

public class Operation
{
    //=================================== ATTRIBUTI ===================================//

    //primary key
    private String username;
    private boolean operationType;      //TRUE = entrata , FALSE = uscita
    private double amount;
    private String note;                // NOTE: forse non utile e andra trasformato in categoria

    //=================================== COSTRUTTORE ===================================
    public Operation(String username, boolean operationType, double  amount , String note)
    {
        this.username = username;
        this.operationType = operationType;
        this.amount = amount;
        this.note = note;
    }
    //=================================== GETTER ===================================//
    public String getUsername() {
        return username;
    }
    public boolean getOperationType() {
        return operationType;
    }
    public double getAmount() {
        return amount;
    }
    public String getNote() {
        return note;
    }

    //=================================== SETTER ===================================//
    public void setUsername(String username) {
        this.username = username;
    }
    public void setOperationType(boolean operationType) {
        this.operationType = operationType;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setNote(String note) {
        this.note = note;
    }
}




