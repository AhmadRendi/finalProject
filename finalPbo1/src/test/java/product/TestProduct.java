package product;

import org.example.entity.Product;
import org.example.repository.RepositoryProduct;
import org.example.repository.RepositoryProductImpl;
import org.example.service.ServiceProductImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestProduct {

    RepositoryProduct repositoryProduct = new RepositoryProductImpl();

    ServiceProductImpl serviceProduct = new ServiceProductImpl();
    @Test
    void testInsert() throws SQLException {

        String namaBarang = "Gula";
        Integer Harga = 15_000;
        Integer stock = 20;

        Product product = new Product();
        product.setName(namaBarang);
        product.setHarga(Harga);
        product.setStock(stock);

        repositoryProduct.addProduct(product);
    }

    @Test
    void textKurangBarang(){
        repositoryProduct.resetStock(1, 5);
    }
}
