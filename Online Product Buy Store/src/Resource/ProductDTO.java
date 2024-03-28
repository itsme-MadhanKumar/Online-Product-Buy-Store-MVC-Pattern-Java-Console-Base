package Resource;
public class ProductDTO
{
    private String serialno;
    private String productname;
    private long price;
    private long availabe_quantity;
    private String ordered;

    public ProductDTO(String ordered)
    {
        this.ordered = ordered;
    }

    public String getOrdered()
    {
        return ordered;
    }

    public ProductDTO()
    {
    }
    public ProductDTO(String serialno,String productname,long price,long availabe_quantity)
    {
        this.serialno = serialno;
        this.price=  price;
        this.productname = productname;
        this.availabe_quantity = availabe_quantity;
    }

    public String getProductname() {
        return productname;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getAvailabe_quantity() {
        return availabe_quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
}