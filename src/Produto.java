import java.util.Date;
import java.text.SimpleDateFormat;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName = "cardápio")
public class Produto
{   
    @DatabaseField(generatedId = true)
    private int codigoBarras;
    
    @DatabaseField
    private String produto;
    
    @DatabaseField(dataType=DataType.DATE)
    public Date validade;    
    
    public String printValidade() {
        SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
        return dateFor.format(validade);
    }

    //Métodos getters e setters 

    public int getId(){
        return this.codigoBarras;
    }

    public void setId(int codigoBarras){
        this.codigoBarras = codigoBarras;
    }

    public String getFullName(){
        return this.produto;
    }

    public void setFullName(String produto){
        this.produto = produto;
    }

    public Date getValidade(){
        return this.validade;
    }

    public void setValidade(Date validade){
        this.validade = validade;
    }
}
