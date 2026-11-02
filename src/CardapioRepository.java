import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;

public class CardapioRepository
{
    private static Database database;
    private static Dao<Produto, Integer> dao;
    private List<Produto> loadedCardapio;
    private Produto loadedProduto; 
    
    public CardapioRepository(Database database) {
        CardapioRepository.setDatabase(database);
        loadedCardapio = new ArrayList<Produto>();
    }
    
    public static void setDatabase(Database database) {
        CardapioRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Produto.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Produto.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    }
    
    public Produto create(Produto produto) {
        int nrows = 0;
        try {
            nrows = dao.create(produto);
            if ( nrows == 0 )
                throw new SQLException("Error: object not saved");
            this.loadedProduto = produto;
            loadedCardapio.add(produto);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return produto;
    }    

    public void update(Produto produto) {
        int nrows = 0;
        try {
            nrows = dao.update(produto);
            if (nrows == 0)
                throw new SQLException("Error: object not updated");
            this.loadedProduto = produto;
            
            int index = loadedCardapio.indexOf(produto);
            if (index != -1) {
                loadedCardapio.set(index, produto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Produto produto) {
        int nrows = 0;
        try {
            nrows = dao.delete(produto);
            if (nrows == 0)
                throw new SQLException("Error: object not deleted");
                
            if (this.loadedProduto != null && this.loadedProduto.equals(produto)) {
                this.loadedProduto = null;
            }
            loadedCardapio.remove(produto);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Produto loadFromId(int id) {
        try {
            this.loadedProduto = dao.queryForId(id);
            if (this.loadedProduto != null)
                this.loadedCardapio.add(this.loadedProduto);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedProduto;
    }    
    
    public List<Produto> loadAll() {
        try {
            this.loadedCardapio =  dao.queryForAll();
            if (this.loadedCardapio.size() != 0)
                this.loadedProduto = this.loadedCardapio.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedCardapio;
    }      
}
