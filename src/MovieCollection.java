import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }

  private void searchTitles()
  {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();

      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }

  private void sortPeople(ArrayList<String> listToSort)
  {
    for(int i = 1; i < listToSort.size(); i++){
      String objectToSort = listToSort.get(i);
      int j = i;
      while(j > 0 && objectToSort.compareTo(listToSort.get(j - 1)) < 0){
        listToSort.set(j, listToSort.get(j - 1) );
        j--;
      }
      listToSort.set(j, objectToSort);

    }
  }
  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast()
  {
    System.out.print("Enter a person to search for: ");
    String searchTerm = scanner.nextLine();
    ArrayList<String> actors = new ArrayList<>();
    for (Movie movie : movies) {
      String cast = movie.getCast();
      String[] castArray = cast.split("\\|");
      for (String castMember : castArray) {
        if (!actors.contains(castMember)) {
          actors.add(castMember);
        }
      }
    }
    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();
    ArrayList<String> similarActors = new ArrayList<>();
    for(int i = 0; i < actors.size(); i++){
      if(actors.get(i).toLowerCase().contains(searchTerm)){
        similarActors.add(actors.get(i));
      }
    }

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();
    sortPeople(similarActors);
    String actorChoice = "";
    for (int i = 0; i < similarActors.size(); i++)
    {
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + similarActors.get(i));
    }
    System.out.println("which person would you like to know more about");
    int userChoice = scanner.nextInt();
    actorChoice = similarActors.get(userChoice - 1);
    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieCast = movies.get(i).getCast();
      String[] movieActorList = movieCast.split("\\|");
      for(int k = 0; k < movieActorList.length; k++){
        if(movieActorList[k].equals(actorChoice)){
          results.add(movies.get(i));
        }
      }
    }

    // sort the results by title


    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void searchKeywords()
  {
    System.out.print("Enter a keyword search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieKeyword = movies.get(i).getKeywords();
      movieKeyword = movieKeyword.toLowerCase();

      if (movieKeyword.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listGenres()
  {
    ArrayList<String> genres = new ArrayList<>();
    for(Movie movie : movies) {
      String movieGenresString = movie.getGenres();
      String[] movieGenres = movieGenresString.split("\\|");
      for (int i = 0; i < movieGenres.length; i++) {
        if (genres.indexOf(movieGenres[i]) == -1) {
          genres.add(movieGenres[i]);
        }
      }
    }
      sortPeople(genres);
      for (int i = 0; i < genres.size(); i++)
      {
        int choiceNum = i + 1;
        System.out.println("" + choiceNum + ". " + genres.get(i));
      }

      System.out.println("which genre would you like to know more about");
      int userChoice = scanner.nextInt();
      String genreChoice = genres.get(userChoice - 1);
      ArrayList<Movie> results = new ArrayList<Movie>();

      for (int i = 0; i < movies.size(); i++)
      {
        String movieGenre = movies.get(i).getGenres();
        String[] movieGenreList = movieGenre.split("\\|");
        for(int k = 0; k < movieGenreList.length; k++){
          if(movieGenreList[k].equals(genreChoice)){
            results.add(movies.get(i));
          }
        }
      }

      // sort the results by title
      sortResults(results);

      // now, display them all to the user
      for (int i = 0; i < results.size(); i++)
      {
        String title = results.get(i).getTitle();

        // this will print index 0 as choice 1 in the results list; better for user!
        int choiceNum = i + 1;

        System.out.println("" + choiceNum + ". " + title);
      }

      System.out.println("Which movie would you like to learn more about?");
      System.out.print("Enter number: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      Movie selectedMovie = results.get(choice - 1);

      displayMovieInfo(selectedMovie);

      System.out.println("\n ** Press Enter to Return to Main Menu **");
      scanner.nextLine();

    }

  private void listHighestRated()
  {
    
  }
  
  private void listHighestRevenue()
  {
    /* TASK 6: IMPLEMENT ME! */
  }

  private void importMovieList(String fileName)
  {
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();

      movies = new ArrayList<Movie>();

      while ((line = bufferedReader.readLine()) != null)
      {
        // import all cells for a single row as an array of Strings,
        // then convert to ints as needed
        String[] movieFromCSV = line.split(",");

        // pull out the data for this cereal
        String title = movieFromCSV[0];
        String cast = movieFromCSV[1];
        String director = movieFromCSV[2];
        String tagline = movieFromCSV[3];
        String keywords = movieFromCSV[4];
        String overview = movieFromCSV[5];
        int runtime = Integer.parseInt(movieFromCSV[6]);
        String genres = movieFromCSV[7];
        double userRating = Double.parseDouble(movieFromCSV[8]);
        int year =  Integer.parseInt(movieFromCSV[9]);
        int revenue = Integer.parseInt(movieFromCSV[10]);

        // create Cereal object to store values
       Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);

        // adding Cereal object to the arraylist
        movies.add(nextMovie);
      }
      bufferedReader.close();
    }
    catch(IOException exception)
    {
      // Print out the exception that occurred
      System.out.println("Unable to access " + exception.getMessage());
    }
  }


  // ADD ANY ADDITIONAL PRIVATE HELPER METHODS you deem necessary

}