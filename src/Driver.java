import java.lang.invoke.SwitchPoint;
import java.util.Scanner;
import java.io.*;
public class Driver {

    final static String [] VALID_GENRES= {"musical","comedy","animation","adventure","drama","crime","biography",
    "horror","action","documentary","fantasy","mystery","sci-fi","family","romance","thriller",
    "western"};

    final static String []VALID_RATINGS ={"PG","Unrated","G","R","PG-13","NC-17"};


  static int getIndexChar(String movie,char c,int nbRepetitions)
  {
      int count =0;
      int index=0;

      for(int i=0;i<movie.length();i++)
      {
          if(movie.charAt(i)==c)
          {
              count++;
              if (count==nbRepetitions)
              {
                  index=i;
                  break;
              }

          }
      }
      return index;
  }

  static int getNumberRepetitionChar(String movie,char c)
  {
      int count=0;
      for(int i=0;i<movie.length();i++) {
          if (movie.charAt(i) == c) {
              count++;
          }
      }
      return count;
  }

  static boolean indexEmptyField(String movie)
  {
      boolean yes = false;
      int index=0;
      for (int i=1;i<movie.length();i++)
      {
          index= getIndexChar(movie,',',i);
          if(index+1<movie.length()) {
              if (movie.charAt(index) == movie.charAt(index + 1)) {
                  yes = true;
                  break;
              }
          }
      }
      return yes;
  }

    static boolean containsOnlyNumbers(String str)
    {
        boolean yes = true;
        for (int i=0;i<str.length();i++)
        {
            if(str.charAt(i)<48||str.charAt(i)>57)
            {
                yes = false;
                break;
            }
        }
        return yes;
    }

    static boolean containsOnlyNumbersAndPoint(String str)
    {
        boolean yes = true;
        for (int i=0;i<str.length();i++)
        {
            if(str.charAt(i)<46||str.charAt(i)>57||str.charAt(i)=='/')
            {
                yes = false;
                break;
            }
        }
        return yes;
    }

    static int getNbIgnoredCommas(String movie)
    {
        int ignoreCommas =0;
        int firstIndexOfQuotes = getIndexChar(movie,'"',1);
        int lastIndexOfQuotes = getIndexChar(movie,'"',2);

        //getting right number of commas
        String titleInQuotes = movie.substring(firstIndexOfQuotes,lastIndexOfQuotes+1);
        if(titleInQuotes.contains(",")) {
            ignoreCommas = getNumberRepetitionChar(titleInQuotes, ',');
        }
        return ignoreCommas;
    }


    static int getYear(String movie)
    {
        String year = movie.substring(0,getIndexChar(movie,',',1));

        return Integer.parseInt(year);
    }

    static String getTitle(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        String title = movie.substring(getIndexChar(movie,',',1)+1,getIndexChar(movie,',',2+ignoreCommas));
        return title;
    }

    static int getDuration(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int indexDuration1 = getIndexChar(movie,',',ignoreCommas+2)+1;
        int indexDuration2 = getIndexChar(movie,',',ignoreCommas+3);
        String duration = movie.substring(indexDuration1,indexDuration2);
        return Integer.parseInt(duration);
    }

    static String getRating(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int indexRating1 = getIndexChar(movie,',',4+ignoreCommas)+1;
        int indexRating2 = getIndexChar(movie,',',5+ignoreCommas);
        String rating = movie.substring(indexRating1,indexRating2);
        return rating;
    }

    static double getScore(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int indexScore1 = getIndexChar(movie,',',5+ignoreCommas)+1;
        int indexScore2 = getIndexChar(movie,',',6+ignoreCommas);
        String score = movie.substring(indexScore1,indexScore2);
        return Double.parseDouble(score);
    }

    static String getGenre(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        //this is the genre of the movie
        int firstIndexOfGenre=getIndexChar(movie,',',3+ignoreCommas)+1;
        int lastIndexOfGenre=getIndexChar(movie,',',4+ignoreCommas);
        String genre = movie.substring(firstIndexOfGenre,lastIndexOfGenre);
        return genre;
    }

    static String getDirector(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int indexDir = getIndexChar(movie,',',6+ignoreCommas)+1;
        int indexDir2 = getIndexChar(movie,',',7+ignoreCommas);
        return movie.substring(indexDir,indexDir2);

    }

    static String getActor1(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int index1 = getIndexChar(movie,',',7+ignoreCommas)+1;
        int index2 = getIndexChar(movie,',',8+ignoreCommas);
        return movie.substring(index1,index2);

    }
    static String getActor2(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int index1 = getIndexChar(movie,',',8+ignoreCommas)+1;
        int index2 = getIndexChar(movie,',',9+ignoreCommas);
        return movie.substring(index1,index2);

    }
    static String getActor3(String movie)
    {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int index1 = getIndexChar(movie,',',9+ignoreCommas)+1;
        return movie.substring(index1);

    }



    public static String do_part1(String path)
    {
        File part2_manifest = new File("part2_manifest.txt");
        File bad_movie_records = new File("bad_movie_records.txt");
        File comedy = new File("comedy.csv");
        File action = new File("action.csv");
        File musical = new File("musical.csv");
        File fantasy = new File("fantasy.csv");
        File crime = new File("crime.csv");
        File adventure = new File("adventure.csv");
        File drama = new File("drama.csv");
        File biography = new File("biography.csv");
        File animation = new File("animation.csv");
        File thriller = new File("thriller.csv");
        File mystery = new File("mystery.csv");
        File sci_fi = new File("sci-fi.csv");
        File documentary = new File("documentary.csv");
        File romance = new File("romance.csv");
        File western = new File("western.csv");
        File horror = new File("horror.csv");
        File family = new File("family.csv");
        File [] files = {comedy,action,musical,fantasy,crime,adventure,drama,biography,animation,thriller,mystery,sci_fi,documentary,romance,
        western,horror,family};

        Scanner scannerPart1_manifest = null;
        Scanner scannerInputFiles=null;
        PrintWriter writerPart2_manifest=null;
        PrintWriter writerBadMovie=null;
        PrintWriter writerComedy=null;
        PrintWriter writerAction=null;
        PrintWriter writerMusical=null;
        PrintWriter writerFantasy=null;
        PrintWriter writerCrime=null;
        PrintWriter writerAdventure=null;
        PrintWriter writerDrama=null;
        PrintWriter writerBiography=null;
        PrintWriter writerAnimation=null;
        PrintWriter writerThriller=null;
        PrintWriter writerMystery=null;
        PrintWriter writerSci_fi=null;
        PrintWriter writerDocumentary=null;
        PrintWriter writerRomance=null;
        PrintWriter writerWestern=null;
        PrintWriter writerHorror=null;
        PrintWriter writerFamily=null;
        PrintWriter writerGenre = null;
        try
        {
            writerPart2_manifest = new PrintWriter(new FileOutputStream("part2_manifest.txt"));
            writerBadMovie = new PrintWriter(new FileOutputStream("bad_movie_records.txt"));
            writerComedy = new PrintWriter(new FileOutputStream(comedy.getPath()));
            writerAction = new PrintWriter(new FileOutputStream(action.getPath()));
            writerMusical = new PrintWriter(new FileOutputStream(musical.getPath()));
            writerFantasy=new PrintWriter(new FileOutputStream(fantasy.getPath()));
            writerCrime =new PrintWriter(new FileOutputStream(crime.getPath()));
            writerAdventure = new PrintWriter(new FileOutputStream(adventure.getPath()));
            writerDrama = new PrintWriter(new FileOutputStream(drama.getPath()));
            writerBiography= new PrintWriter(new FileOutputStream(biography.getPath()));
            writerAnimation = new PrintWriter(new FileOutputStream(animation.getPath()));
            writerThriller = new PrintWriter(new FileOutputStream(thriller.getPath()));
            writerMystery = new PrintWriter(new FileOutputStream(mystery.getPath()));
            writerSci_fi = new PrintWriter(new FileOutputStream(sci_fi.getPath()));
            writerDocumentary = new PrintWriter(new FileOutputStream(documentary.getPath()));
            writerRomance = new PrintWriter(new FileOutputStream(romance.getPath()));
            writerWestern = new PrintWriter(new FileOutputStream(western.getPath()));
            writerHorror = new PrintWriter(new FileOutputStream(horror.getPath()));
            writerFamily = new PrintWriter(new FileOutputStream(family.getPath()));


            //writing the file names in manifest 2
            for (int i=0;i<files.length;i++)
            {
                writerPart2_manifest.println(files[i].getPath());
            }


        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot open the file.");
            System.exit(0);
        }

        try {
            scannerPart1_manifest= new Scanner(new FileInputStream(path));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot open the file for input.");
            System.exit(0);
        }

        //big loop starts here of reading each line of each doc
        while (scannerPart1_manifest.hasNextLine())
        {
            //counts the position of the movie in his own file
            int countOfPositionMovie =0;
            String file ="";
            try
            {
                file = scannerPart1_manifest.nextLine();
                scannerInputFiles = new Scanner(new FileInputStream(file));
            }catch (FileNotFoundException fnfe) {
                System.out.println("Cannot open the file for input.");
                System.exit(0);
            }
            while (scannerInputFiles.hasNextLine())
            {
                String movie = scannerInputFiles.nextLine();
                int firstIndexOfGenre=0;
                int lastIndexOfGenre=0;
                int countOfCommas=0;
                try {
                    countOfCommas=getNumberRepetitionChar(movie,',');
                    int ignoreCommas= 0;
                    int firstIndexOfQuotes = getIndexChar(movie,'"',1);
                    int lastIndexOfQuotes = getIndexChar(movie,'"',2);

                    //getting right number of commas
                    String titleInQuotes = movie.substring(firstIndexOfQuotes,lastIndexOfQuotes+1);
                    if(titleInQuotes.contains(",")) {
                        ignoreCommas = getNumberRepetitionChar(titleInQuotes, ',');
                        countOfCommas -= ignoreCommas;
                    }

                    //all types of titles
                    String title = movie.substring(getIndexChar(movie,',',1)+1,getIndexChar(movie,',',2+ignoreCommas));

                    //this is the genre of the movie
                    firstIndexOfGenre=getIndexChar(movie,',',3+ignoreCommas)+1;
                    lastIndexOfGenre=getIndexChar(movie,',',4+ignoreCommas);
                    String genre = movie.substring(firstIndexOfGenre,lastIndexOfGenre);

                    //excess fields exception
                    if (countOfCommas>9)
                    {
                        throw new ExcessFieldsException();
                    }

                    //missing fields exception
                    if(countOfCommas<9)
                    {
                        throw new MissingFieldsException();
                    }

                    //missing quotes
                    if(getNumberRepetitionChar(movie,'"')==1)
                    {
                        throw new MissingQuotesException();
                    }

                    //invalid or missing year
                    String year = movie.substring(0,getIndexChar(movie,',',1));
                    if(year==null||year.equals(""))
                    {
                        throw new BadYearException();

                    }
                    for (int i=0;i<year.length();i++)
                    {
                        if(year.charAt(i)<48||year.charAt(i)>57)
                        {
                            throw new BadYearException();
                        }
                    }
                        int yearInt = Integer.valueOf(year);
                        if(yearInt<1990||yearInt>1999)
                        {
                            throw new BadYearException();
                        }

                    //invalid duration
                    int indexDuration1 = getIndexChar(movie,',',ignoreCommas+2)+1;
                    int indexDuration2 = getIndexChar(movie,',',ignoreCommas+3);
                    String duration = movie.substring(indexDuration1,indexDuration2);
                    if(duration.equals("")||!containsOnlyNumbers(duration))
                    {
                        throw new BadDurationException();
                    }
                    else {
                        int durationInt = Integer.valueOf(duration);
                        if(durationInt<30||durationInt>300)
                        {
                            throw new BadDurationException();
                        }
                    }
                    //invalid rating
                    int indexRating1 = getIndexChar(movie,',',4+ignoreCommas)+1;
                    int indexRating2 = getIndexChar(movie,',',5+ignoreCommas);
                    String rating = movie.substring(indexRating1,indexRating2);
                    boolean sameRating = false;
                    if(rating.equals(""))
                    {
                        throw new BadDurationException();
                    }
                    for (int i=0;i< VALID_RATINGS.length;i++)
                    {
                        if (rating.equals(VALID_RATINGS[i]))
                        {
                            sameRating = true;
                            break;
                        }
                    }
                    if(sameRating==false)
                    {
                        throw new BadRatingException();
                    }

                    //invalid score
                    int indexScore1 = getIndexChar(movie,',',5+ignoreCommas)+1;
                    int indexScore2 = getIndexChar(movie,',',6+ignoreCommas);
                    String score = movie.substring(indexScore1,indexScore2);
                    if(score.equals("")||!containsOnlyNumbersAndPoint(score))
                    {
                        throw new BadScoreException();
                    }
                    else {
                       double scoreDouble = Double.parseDouble(score);
                        if(scoreDouble<0||scoreDouble>10)
                        {
                            throw new BadScoreException();
                        }
                    }
                    //missing title
                    if(movie.charAt(getIndexChar(movie,',',1))==movie.charAt(getIndexChar(movie,',',1)+1))
                    {
                        throw new BadTitleException();
                    }
                    //missing name
                    String names = movie.substring(getIndexChar(movie,',',6+ignoreCommas));
                    if(indexEmptyField(names)==true||names.charAt(names.length()-1)==',')
                    {
                        throw new BadNameException();
                    }
                    //invalid genre
                    if(genre.equals(""))
                    {
                        throw new BadGenreException();
                    }
                    boolean sameGenre=false;
                    for(int i=0;i< VALID_GENRES.length;i++)
                    {
                        //genre valid
                        if (genre.equalsIgnoreCase(VALID_GENRES[i]))
                        {
                            switch (genre){
                                case "Comedy":{writerComedy.write(movie + "\n");break;}
                                case "Action":{writerAction.write(movie + "\n");break;}
                                case "Adventure":{writerAdventure.write(movie + "\n");break;}
                                case "Animation":{writerAnimation.write(movie + "\n");break;}
                                case "Biography":{writerBiography.write(movie + "\n");break;}
                                case "Crime":{writerCrime.write(movie + "\n");break;}
                                case "Documentary":{writerDocumentary.write(movie + "\n");break;}
                                case "Drama":{writerDrama.write(movie + "\n");break;}
                                case "Family":{writerFamily.write(movie + "\n");break;}
                                case "Fantasy":{writerFantasy.write(movie + "\n");break;}
                                case "Horror":{writerHorror.write(movie + "\n");break;}
                                case "Musical":{writerMusical.write(movie + "\n");break;}
                                case "Mystery":{writerMystery.write(movie + "\n");break;}
                                case "Romance":{writerRomance.write(movie + "\n");break;}
                                case "Sci-fi":{writerSci_fi.write(movie + "\n");break;}
                                case "Thriller":{writerThriller.write(movie + "\n");break;}
                                case "Western":{writerWestern.write(movie + "\n");break;}
                            }
                            sameGenre=true;
                            break;
                        }
                    }
                    if (sameGenre==false)
                    {
                        throw new BadGenreException();
                    }
                }
                catch (ExcessFieldsException efe)
                {
                    writerBadMovie.println("Syntax error: Excess fields: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (MissingFieldsException mfe)
                {
                    writerBadMovie.println("Syntax error: Missing fields: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (MissingQuotesException mfe) {
                    writerBadMovie.println("Syntax error: Missing quotes: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (BadYearException bye)
                {
                    writerBadMovie.println("Semantic error: Invalid year: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (BadDurationException bde)
                {
                    writerBadMovie.println("Semantic error: Invalid duration: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (BadRatingException bre)
                {
                    writerBadMovie.println("Semantic error: Invalid rating: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (BadScoreException bse)
                {
                    writerBadMovie.println("Semantic error: Invalid score: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (BadTitleException bte)
                {
                    writerBadMovie.println("Semantic error: Invalid title: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (BadNameException bte)
                {
                    writerBadMovie.println("Semantic error: Invalid name: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                catch (BadGenreException bge)
                {
                    writerBadMovie.println("Semantic error: Invalid genre: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }
                countOfPositionMovie++;

            }
        }
        writerPart2_manifest.close();
        writerBadMovie.close();
        writerComedy.close();
        writerAction.close();
        writerAdventure.close();
        writerAnimation.close();
        writerBiography.close();
        writerCrime.close();
        writerDocumentary.close();
        writerDrama.close();
        writerFamily.close();
        writerFantasy.close();
        writerHorror.close();
        writerMusical.close();
        writerMystery.close();
        writerRomance.close();
        writerSci_fi.close();
        writerThriller.close();
        writerWestern.close();
        return part2_manifest.getPath();
    }

    public static String do_part2(String path)
    {
        PrintWriter writer = null;
        Scanner scannerPart2_manifest = null;
        Scanner genreFile = null;
        FileOutputStream fos = null;
        ObjectOutputStream obs = null;
        String fileGenre=null;
        File serFile =null;
        File part3_manifest=null;
        try {
            scannerPart2_manifest= new Scanner(new FileInputStream(path));
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File not found");
            System.exit(0);
        }
        while (scannerPart2_manifest.hasNextLine())
        {

            int countMovies=0;
            int indexArray=0;
            String movie="";
            String genreSerForSerFile =null;
            fileGenre = scannerPart2_manifest.nextLine();
            try{
                genreFile = new Scanner(new FileInputStream(fileGenre));
            }
            catch (FileNotFoundException fnfe) {
                System.out.println("File not found");
                System.exit(0);
            }
            while (genreFile.hasNextLine())
            {
                genreFile.nextLine();
                countMovies++;
            }
            //System.out.println(countMovies);
            genreFile.close();
            Movie [] movies = new Movie[countMovies];

            try{
                genreFile = new Scanner(new FileInputStream(fileGenre));
                genreSerForSerFile = fileGenre.substring(0,getIndexChar(fileGenre,'.',1));
                serFile = new File(genreSerForSerFile+".ser");
                serFile.createNewFile();
                part3_manifest = new File("part3_manifest.txt");
                part3_manifest.createNewFile();
            }
            catch (FileNotFoundException fnfe) {
                System.out.println("File not found");
                System.exit(0);
            }
            catch (IOException fnfe) {
                System.out.println("Cant create");
                System.exit(0);
            }
            for (int i=0;i<movies.length;i++) {
                movie = genreFile.nextLine();

                movies[i] = new Movie(getYear(movie), getTitle(movie), getDuration(movie), getGenre(movie), getRating(movie), getScore(movie)
                        , getDirector(movie), getActor1(movie), getActor2(movie), getActor3(movie));

            }
            genreFile.close();
            //serialize the array HERE this code works but the extension .ser doesnt exist in intellij
            try {
                fos = new FileOutputStream (genreSerForSerFile.toLowerCase()+".ser");
                obs = new ObjectOutputStream(fos);
                    for (int i = 0; i < movies.length; i++) {
                        obs.writeObject(movies[i]);
                    }
                obs.close();
                fos.close();
                writer = new PrintWriter(new FileOutputStream(part3_manifest.getPath()));
            }
            catch(FileNotFoundException fnfe) {

                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println("IO exception");
                System.exit(0);
            }
            writer.println("musical.ser");
            writer.println("comedy.ser");
            writer.println("animation.ser");
            writer.println("adventure.ser");
            writer.println("drama.ser");
            writer.println("crime.ser");
            writer.println("biography.ser");
            writer.println("horror.ser");
            writer.println("action.ser");
            writer.println("documentary.ser");
            writer.println("fantasy.ser");
            writer.println("mystery.ser");
            writer.println("sci-fi.ser");
            writer.println("family.ser");
            writer.println("western.ser");
            writer.println("romance.ser");
            writer.println("thriller.ser");
            writer.close();
        }
        scannerPart2_manifest.close();
        return part3_manifest.getPath();
    }

    static Movie [][] do_part3(String path)
    {
        Movie [][] allMovies = new Movie[17][173];
        Scanner scannerManifest = null;
        ObjectInputStream ois = null;
        String serFile=null;
        int countForArray =0;
        try {
            scannerManifest = new Scanner(new FileInputStream(path));
        }catch (FileNotFoundException fnfe)
        {
            System.out.println("Cant open");
            System.exit(0);
        }
        while (scannerManifest.hasNextLine())
        {
            serFile = scannerManifest.nextLine();
            int countOfMovies=0;
            try {
                ois = new ObjectInputStream(new FileInputStream(serFile));

            }catch (FileNotFoundException e)
            {
                System.out.println("Cant open");
                System.exit(0);
            }
            catch (IOException e)
            {
                System.out.println(" exception 1");
            }

            try
            {
                    for (int i = 0; i < allMovies[0].length; i++) {
                        allMovies[countForArray][i] = (Movie) ois.readObject();
                    }
                ois.close();
            }
            catch (ClassNotFoundException e)
            {
                System.out.println("exception");
                System.exit(0);
            }
            catch (IOException e){}
            countForArray++;
        }
        return allMovies;
    }

    public static void main (String[]args)
    {

        String part1_manifest = "part1_manifest.txt"; // creates path for part1_manifest

        String part2_manifest= do_part1(part1_manifest); // sorts the movies and gives the path to part2_manifest

        String part3_manifest = do_part2(part2_manifest); //serializes the movies in genre binary files

        allMovies = do_part3(part3_manifest); //deserializes the movies and returns a 2D array of all the movies

        menu();//the menu operations

    }
    static Movie [][] allMovies;

    static int savedIndexComedy=0;
    static int savedIndexAnimation=0;
    static int savedIndexAdventure=0;
    static int savedIndexDrama=0;
    static int savedIndexCrime=0;
    static int savedIndexBiography=0;
    static int savedIndexHorror=0;
    static int savedIndexAction=0;
    static int savedIndexDocumentary=0;
    static int savedIndexFantasy=0;
    static int savedIndexFamily=0;

    static int [] allSavedIndexes = {0,savedIndexComedy,savedIndexAnimation,savedIndexAdventure,savedIndexDrama,savedIndexCrime
            ,savedIndexBiography,savedIndexHorror,savedIndexAction,savedIndexDocumentary,savedIndexFantasy,0,0,savedIndexFamily,0,0};

    static String previousGenreChoice = "musical";
    static  int previousRow = 0;
    static String choiceMainMenu="";
    static int numberOfMovieRecordTodisplay =0;

    public static void menu()
    {
        Scanner input = new Scanner(System.in);

        do {
            choiceMainMenu = getValidChoiceMainMenu(input);
            switch (choiceMainMenu) {

                //CASE S FINISHED
                case "s": {
                    int choiceSubMenu = getValidChoiceSubMenu(input);
                    previousGenreChoice = VALID_GENRES[choiceSubMenu - 1];
                    previousRow = choiceSubMenu - 1;
                    break;

                }
                //TO DO
                case "n": {
                    if(getNumberOfMovies(previousRow)==0)
                    {
                        System.out.println("There are no movies of this genre");
                    }
                    else {
                        System.out.println("Navigating " + previousGenreChoice + " movies (" + getNumberOfMovies(previousRow) + ")");
                        System.out.print("Enter your choice: ");
                        int n = input.nextInt();
                        if (n == 0) {
                            break;
                        }
                        else if (n > 0) {

                            navigatePositiveN(n);
                        }
                        else {
                            navigateNegativeN(n);
                        }
                    }
                    break;
                }
                case "x": {
                    System.out.println("Thank you for using the online movie catalog goodbye!");
                    System.exit(0);
                }
            }
        }while (choiceMainMenu.equalsIgnoreCase("x")==false);
    }

    static void navigatePositiveN(int n)
    {
        if (n - 1 < allMovies[previousRow].length) {
            numberOfMovieRecordTodisplay += (allSavedIndexes[previousRow] + (n - 1)); //position in array
            if (numberOfMovieRecordTodisplay >= 173) {
                numberOfMovieRecordTodisplay = allSavedIndexes[previousRow];
            }
            System.out.println(numberOfMovieRecordTodisplay);
            for (int i = allSavedIndexes[previousRow]; i <= numberOfMovieRecordTodisplay; i++) {
                System.out.println("The movie at position: " + (i + 1) + ": " + allMovies[previousRow][i]);
            }
            allSavedIndexes[previousRow] = numberOfMovieRecordTodisplay;
            numberOfMovieRecordTodisplay = 0;
        }
        else {
            System.out.println("EOF has been reached");
            for (int i = allSavedIndexes[previousRow]; i < allMovies[previousRow].length; i++) {
                System.out.println("The movie at position: " + (i + 1) + ": " + allMovies[previousRow][i]);
            }
            allSavedIndexes[previousRow] = allMovies[previousRow].length - 1;
        }
    }

    static void navigateNegativeN(int n)
    {
        int nAbs = Math.abs(n);

        if (nAbs - 1 > allSavedIndexes[previousRow]) {
            for (int i = 0; i <= allSavedIndexes[previousRow]; i++) {
                System.out.println("The movie at position: " + (i + 1) + ": " + allMovies[previousRow][i]);
            }
            allSavedIndexes[previousRow] = 0;
        } else {

            int start = allSavedIndexes[previousRow] - (nAbs - 1);

            for (int i = start; i <= allSavedIndexes[previousRow]; i++) {
                System.out.println("The movie at position: " + (i + 1) + ": " + allMovies[previousRow][i]);
            }
            allSavedIndexes[previousRow] = start;
        }
    }
    static void displayMenu()
    {
        System.out.print("--------------------------------------------\n\t\t\t\tMain Menu\n--------------------------------------------"+
                "\ns Select a movie\nn navigate "+previousGenreChoice+" movies ("+getNumberOfMovies(previousRow)+" movies)"+"\nx exit\n--------------------------------------------\n\n"
        +"Enter your choice: ");
    }
    static void displaySubMenu()
    {
        System.out.println("--------------------------------------------\n\t\t\t\tGenre Sub-Menu\n--------------------------------------------");
        //each row
        for (int i =0;i<allMovies.length;i++)
        {
            System.out.println(i+1+"\t\t"+VALID_GENRES[i]+"\t\t\t ("+getNumberOfMovies(i)+" movies)");
        }
        System.out.println("--------------------------------------------\nEnter your choice: ");

    }
    static int getNumberOfMovies(int previousRow)
    {

        int count=0;
        for (int i=0;i<allMovies[previousRow].length;i++)
        {
            if (allMovies[previousRow][i]!=null)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        return count;
    }

    static String getValidChoiceMainMenu(Scanner input)
    {
        displayMenu();
        String choice = input.next();
        while (choice.equalsIgnoreCase("s")==false&&choice.equalsIgnoreCase("n")==false&&choice.equalsIgnoreCase("x")==false)
        {
            System.out.print("\nThis is not a valid choice please try again: ");
            choice = input.next();
        }
        return choice.toLowerCase();
    }

    static int getValidChoiceSubMenu(Scanner input)
    {
        displaySubMenu();
        int choice =input.nextInt();
        while (choice>17||choice<1)
        {
            System.out.print("\nThis is not a valid choice please try again: ");
            choice = input.nextInt();
        }
        return choice;
    }
}
