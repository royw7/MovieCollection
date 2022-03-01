import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CerealCollection
{
  private ArrayList<Cereal> cereals;

  public CerealCollection(String fileName)
  {
    importCerealList(fileName);
  }

  public ArrayList<Cereal> getCereals()
  {
    return cereals;
  }
  
  private void importCerealList(String fileName)
  {
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();
      
      cereals = new ArrayList<Cereal>();
      
      while ((line = bufferedReader.readLine()) != null) 
      {
        // import all cells for a single row as an array of Strings,
        // then convert to ints as needed
        String[] cerealFromCSV = line.split(",");
        
        // pull out the data for this cereal
        String name = cerealFromCSV[0];
        int calories = Integer.parseInt(cerealFromCSV[1]);
        int protein = Integer.parseInt(cerealFromCSV[2]);
        int fat = Integer.parseInt(cerealFromCSV[3]);
        int sodium = Integer.parseInt(cerealFromCSV[4]);
        int fiber = Integer.parseInt(cerealFromCSV[5]);
        int carbs = Integer.parseInt(cerealFromCSV[6]);
        int sugar = Integer.parseInt(cerealFromCSV[7]);
        
        // create Cereal object to store values
        Cereal nextCereal = new Cereal(name, calories, protein, fat, sodium, fiber, carbs, sugar);

        // adding Cereal object to the arraylist  
        cereals.add(nextCereal);  
      }
      bufferedReader.close();
    }
    catch(IOException exception)
    {
      // Print out the exception that occurred
      System.out.println("Unable to access " + exception.getMessage());              
    }
  }
}