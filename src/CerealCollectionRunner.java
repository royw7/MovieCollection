import java.util.ArrayList;

public class CerealCollectionRunner
{
  public static void main(String arg[])
  {
    CerealCollection collection = new CerealCollection("src\\cereal.csv");
    ArrayList<Cereal> cerealList = collection.getCereals();
    for (Cereal cereal : cerealList)
    {
      System.out.println(cereal);
    }
  }
}