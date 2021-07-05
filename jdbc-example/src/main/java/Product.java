
public class Product {
	private long id;
	private String name;
	private String name2;
	private String description;
	private float price;
	private long sellerId;

	public Product() {
	}

	public Product(long id, String name, String name2, String description, float price,long sellerId) {
		this.id = id;
		this.name = name;
		this.name2 = name2;
		this.description = description;
		this.price = price;
		this.sellerId = sellerId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
}


