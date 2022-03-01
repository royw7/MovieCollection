/**
 * Class that represents a single Cereal object
 */
public class Cereal 
{
  private String name;
  private int calories;
  private int protein;
  private int fat;
  private int sodium;
  private int fiber;
  private int carbs;
  private int sugar;
  
  public Cereal(String name, int calories, int protein, int fat,
                int sodium, int fiber, int carbs, int sugar)
  {
    this.name = name;
    this.calories = calories;
    this.protein = protein;
    this.fat = fat;
    this.sodium = sodium;
    this.fiber = fiber;
    this.carbs = carbs;
    this.sugar = sugar;
  }
    
  public String getName()
  {
    return name;
  }
  
  public int getCalories()
  {
    return calories;
  }
  
  public int getProtein()
  {
    return protein;
  }
  
  public int getFat()
  {
    return fat;
  }
  
  public int getSodium()
  {
    return sodium;
  }
  
  public int getFiber()
  {
    return fiber;
  }
  
  public int getCarbs()
  {
    return carbs;
  }
  
  public int getSugar()
  {
    return sugar;
  }
  
  public String toString()
  {
    String str = "Cereal Name: " + name + ", Calories: " + calories + ", Protein: " + protein + ", Fat: " + fat;
    str += ", Sodium: " + sodium + ", Fiber: " + fiber + ", Carbs: " + carbs + ", Sugar: " + sugar;
    
    return str;
  }
}
