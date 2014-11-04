import java.util.Random;


/**
 * @(#) Beverage.java
 */

public class Beverage extends MenuItem
{
	public Integer volume;
	
	private Menu menu;
	
	public Beverage(String name, double price, QualityLevel quality) {
		super(name, price, quality,ingredientCost);
		// TODO Auto-generated constructor stub
	}
	
	public Beverage(String name, double price, QualityLevel quality,
			Integer volume) {
		super(name, price, quality,ingredientCost);
		this.volume = volume;
	}
	public Beverage() {
		generateDishData();
	}

	/**
	 * @param beverage
	 * @param quality
	 * @return 
	 */
	public QualityLevel calculateIngredientCost(String quality) {
		if (quality.contains("1")) {
			super.quality = QualityLevel.High;
			ingredientCost = 3;
		}else{
			super.quality = QualityLevel.Low;
			ingredientCost = 1;
		}
		return super.quality;
	}
	
	public void generateDishData() {
		Random r = new Random();
		String[] names = { "Wine", "Beer", "Juice", "Muratina", "Water",
				"Cider", "Coke", "fanta", "Sprite", "Ginger", "Tea",
				"Coffee", "Soup" };

		int nindex = (int) (Math.random() * names.length);
		name = names[nindex];
		String qindex = Integer.toString((int)(Math.random() * 2));
		volume = r.nextInt(300);
		quality = calculateIngredientCost(qindex);

	}


}
