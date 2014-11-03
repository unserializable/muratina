/**
 * @(#) MenuItem.java
 */

public abstract class MenuItem
{
	private String name;
	
	private Integer price;
	
	private QualityLevel quality;

	public MenuItem setName(String name) {
		this.name = name;
		return this;
	}

	public MenuItem setPrice(Integer price) {
		this.price = price;
		return this;
	}

	public MenuItem setQuality(QualityLevel quality) {
		this.quality = quality;
		return this;
	}

	public QualityLevel getQuality() {
		return quality;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public abstract Integer getIngredientCost();

	// name and info as shown on menu
	public abstract String menuName();

	// menu representation of quality level
	public String menuQuality() {
		return getQuality().name().toUpperCase();
	}

	// menu representation of price
	public String menuPrice() {
		return getPrice() + " euros";
	}

	// menu info that is kept secret from customers :)
	public String menuSecret() {
		return "(Secret: ingredients cost " + getIngredientCost()  + (" euros, quality is ") + menuQuality() + ")";
	}
}
