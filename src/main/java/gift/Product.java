package gift;

public class Product {
    private Long id;
    private String name;
    private int price;
    private String url;

    private static Long makeId = 1L;

    public Product(String name, int price, String url) {
        this.id = makeId++;
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

