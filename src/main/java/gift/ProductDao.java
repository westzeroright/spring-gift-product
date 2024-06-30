
package gift;

import java.util.ArrayList;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcClient jdbcClient;

    public ProductDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void createProductTable() {
        var sql = """
            create table product(
                id long not null,
                name varchar(255),
                price int,
                url varchar(255),
                primary key (id)
            )
            """;
        jdbcClient.sql(sql).update();
    }

    public void insertProduct(Product product) {
        var sql = "insert into product (id, name, price, url) values (?, ?, ?, ?)";
        jdbcClient.sql(sql)
            .param(product.getId())
            .param(product.getName())
            .param(product.getPrice())
            .param(product.getUrl())
            .update();
    }

    public Product selectProduct(Long id) {
        var sql = "select id, name, price, url from product where id = ?";
        return jdbcClient.sql(sql)
            .param(id)
            .query(resultSet -> {
                if (resultSet.next()) {
                    return new Product(
                        //resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")
                    );
                }
                return null;
            });
    }

    public List<Product> findAllProducts() {
        var sql = "select id, name, price, url from product";
        return jdbcClient.sql(sql)
            .query(resultSet -> {
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    products.add(new Product(
                        //resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")
                    ));
                }
                return products;
            });
    }

    public void updateProduct(Product product) {
        var sql = "update product set name = ?, price = ?, url = ? where id = ?";
        jdbcClient.sql(sql)
            .param(product.getName())
            .param(product.getPrice())
            .param(product.getUrl())
            .param(product.getId())
            .update();
    }

    public void deleteProduct(Long id) {
        var sql = "delete from product where id = ?";
        jdbcClient.sql(sql)
            .param(id)
            .update();
    }
}
