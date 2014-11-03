
/**
 * @(#) MenuItem.java
 */

public abstract class MenuItem {
	public String name;

	public double price;

	public QualityLevel quality;
	
	public static double ingredientCost;
	
	public double getIngredientCost() {
		return ingredientCost;
	}

	public static void setIngredientCost(double ingredientCost) {
		MenuItem.ingredientCost = ingredientCost;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public QualityLevel getQuality() {
		return quality;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public MenuItem() {
		
	}

	public void setQuality(QualityLevel quality) {
		this.quality = quality;
	}

	public MenuItem(String name, double price, QualityLevel quality, double ingredientCost) {
		this.name = name;
		this.price = price;
		this.quality = quality;
		this.ingredientCost = ingredientCost;
	}

}
