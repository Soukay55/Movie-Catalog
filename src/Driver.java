import java.lang.invoke.SwitchPoint;
import java.util.Scanner;
import java.io.*;


/**
 * Assignment 2 - COMP 249
 * Due : March 27th, 2024
 * Written by : Wissem Oumsalem (40291712)
 *              Soukayna Haitami (40280964)
 *
 */
public class Driver {

    /**
     * Array containing the valid genres for movies.
     */
    public final static String[] VALID_GENRES = {"musical", "comedy", "animation", "adventure", "drama", "crime", "biography",
            "horror", "action", "documentary", "fantasy", "mystery", "sci-fi", "family", "romance", "thriller",
            "western"};

    /**
     * Array containing the valid ratings for movies.
     */
    public final static String[] VALID_RATINGS = {"PG", "Unrated", "G", "R", "PG-13", "NC-17"};

    /**
     * Method to validate if the user's input is an integer to prevent the program from crashing
     * @param input is the input of the user that is to validate
     * @return the valid integer input
     */
    public static int validIntegerInput(Scanner input){
        int integerInput=0;
        boolean valid = false;
        do{
            if(input.hasNextInt()){
                integerInput = input.nextInt();
                valid=true;
            }else {
                System.out.print("Invalid value. Please enter an integer:");
                input.next();
            }
        }while (!valid);
        return integerInput;
    }

    /**
     * Finds the index of the nth occurrence of a specified character in a string.
     *
     * @param movie         The string (a movie record) in which to search for the character.
     * @param c             The character to find the index of.
     * @param nthRepetition The nth character to find.
     * @return              The index of the nth occurrence of the character in the string,
     *                      or 0 if the character is not found or if nthRepetition is less than 1.
     */
    public static int getIndexChar(String movie,char c,int nthRepetition) {
        int count =0;
        int index=0;

        for(int i=0;i<movie.length();i++) {
            if(movie.charAt(i)==c) {
                count++;
                if (count==nthRepetition) {
                    index=i;
                    break;
                }
            }
        }
        return index;
  }

    /**
     * Counts the number of occurrences of a specified character in a string.
     *
     * @param movie The string (a movie record) in which to count occurrences of the character.
     * @param c     The character to count occurrences of.
     * @return      The number of times the character was repeated in the string.
     */
  public static int getNumberRepetitionChar(String movie,char c) {
        int count=0;
        for(int i=0;i<movie.length();i++) {
            if (movie.charAt(i) == c) {
                count++;
            }
        }
        return count;
  }
    /**
     * Checks if there is an empty field between two consecutive commas in a string.
     *
     * @param movie The string (movie record) to check for empty fields.
     * @return      True if there is an empty field between two consecutive commas,
     *              otherwise false.
     */
  public static boolean indexEmptyField(String movie) {
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

    /**
     * Checks if a string contains only numeric characters.
     *
     * @param str The string (movie record) to check.
     * @return    True if the string contains only numeric characters, otherwise false.
     */
    public static boolean containsOnlyNumbers(String str) {
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

    /**
     * Checks if a string contains only numeric characters and '.' character.
     *
     * @param str The string (movie record) to check.
     * @return    True if the string contains only numeric characters and '.', otherwise false.
     */
    public static boolean containsOnlyNumbersAndPoint(String str) {
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

    /**
     * Counts the number of commas to ignore within two quotes in a string.
     *
     * @param movie The string (movie record) to search for quoted substrings.
     * @return      The number of commas that are ignored within the quoted substring.
     */
    public static int getNbIgnoredCommas(String movie) {
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
    //------------------------------------------------------------------------------------------
    //---------------------------GET THE MOVIE RECORDS ATTRIBUTES-------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * Get the year from a movie string.
     *
     * @param movie The movie string (movie record) containing the year.
     * @return      The year from the movie string.
     */
    public static int getYear(String movie) {
        String year = movie.substring(0,getIndexChar(movie,',',1));

        return Integer.parseInt(year);
    }

    /**
     * Get the title from a movie string.
     *
     * @param movie The movie string (movie record) containing the title.
     * @return      The title from the movie string.
     */
    public static String getTitle(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        String title = movie.substring(getIndexChar(movie,',',1)+1,getIndexChar(movie,',',2+ignoreCommas));
        return title;
    }

    /**
     * Get the duration from a movie string.
     *
     * @param movie The movie string (movie record) containing the duration.
     * @return      The duration from the movie string.
     */
    public static int getDuration(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int indexDuration1 = getIndexChar(movie,',',ignoreCommas+2)+1;
        int indexDuration2 = getIndexChar(movie,',',ignoreCommas+3);
        String duration = movie.substring(indexDuration1,indexDuration2);
        return Integer.parseInt(duration);
    }

    /**
     * Get the rating from a movie string.
     *
     * @param movie The movie string (movie record) containing the rating.
     * @return      The rating from the movie string.
     */
    public static String getRating(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int indexRating1 = getIndexChar(movie,',',4+ignoreCommas)+1;
        int indexRating2 = getIndexChar(movie,',',5+ignoreCommas);
        String rating = movie.substring(indexRating1,indexRating2);
        return rating;
    }

    /**
     * Get the score from a movie string.
     *
     * @param movie The movie string (movie record) containing the score.
     * @return      The score from the movie string.
     */
    public static double getScore(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int indexScore1 = getIndexChar(movie,',',5+ignoreCommas)+1;
        int indexScore2 = getIndexChar(movie,',',6+ignoreCommas);
        String score = movie.substring(indexScore1,indexScore2);
        return Double.parseDouble(score);
    }

    /**
     * Get the genre from a movie string.
     *
     * @param movie The movie string (movie record) containing the genre.
     * @return      The genre from the movie string.
     */
    public static String getGenre(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        //this is the genre of the movie
        int firstIndexOfGenre=getIndexChar(movie,',',3+ignoreCommas)+1;
        int lastIndexOfGenre=getIndexChar(movie,',',4+ignoreCommas);
        String genre = movie.substring(firstIndexOfGenre,lastIndexOfGenre);
        return genre;
    }

    /**
     * Get the director from a movie string.
     *
     * @param movie The movie string (movie record) containing the director.
     * @return      The director from the movie string.
     */
    public static String getDirector(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int indexDir = getIndexChar(movie,',',6+ignoreCommas)+1;
        int indexDir2 = getIndexChar(movie,',',7+ignoreCommas);
        return movie.substring(indexDir,indexDir2);

    }

    /**
     * Get the first actor from a movie string.
     *
     * @param movie The movie string (movie record) containing the first actor.
     * @return      The first actor from the movie string.
     */
    public static String getActor1(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int index1 = getIndexChar(movie,',',7+ignoreCommas)+1;
        int index2 = getIndexChar(movie,',',8+ignoreCommas);
        return movie.substring(index1,index2);
    }

    /**
     * Get the second actor from a movie string.
     *
     * @param movie The movie string (movie record) containing the second actor.
     * @return      The second actor from the movie string.
     */
    public static String getActor2(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int index1 = getIndexChar(movie,',',8+ignoreCommas)+1;
        int index2 = getIndexChar(movie,',',9+ignoreCommas);
        return movie.substring(index1,index2);
    }

    /**
     * Get the third actor from a movie string.
     *
     * @param movie The movie string (movie record) containing the third actor.
     * @return      The third actor from the movie string.
     */
    public static String getActor3(String movie) {
        int ignoreCommas = getNbIgnoredCommas(movie);
        int index1 = getIndexChar(movie,',',9+ignoreCommas)+1;
        return movie.substring(index1);

    }
    //------------------------------------------------------------------------------------------
    //------------------------------------DO_PART1 METHOD-------------------------------------
    //------------------------------------------------------------------------------------------


    /**
     * Partitions movie records from input files specified in the manifest file into CSV files, each storing
     * valid movie records from the same genre. This method creates a total of 19 text files:
     * - A file named bad_movie_records.txt storing the movie records with syntax or semantic errors
     *     (i.e., the invalid records)
     * - 17 CSV files, each storing valid movie records in a specific genre, to be used as input in Part 2.
     *     The file names are created by appending the text ".csv" to each of the 17 possible genres.
     * - A manifest file named part2_manifest.txt storing the names of the CSV files produced above,
     *     also to be used as input in Part 2.
     *
     * @param path The path to the manifest file storing the names of the input files supplied by Mr. Filmbuff.
     * @return The path of the manifest file (part2_manifest.txt) storing the names of the CSV files produced.

     */
    public static String do_part1(String path) {
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

    //------------------------------------------------------------------------------------------
    //------------------------------------DO_PART2 METHOD-------------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * Serializes arrays of Movie records loaded from CSV files into binary files.
     * For each CSV file named in part2_manifest.txt, this method copy the records
     * from the file ia an array of Movie objects to then serialize it
     * into a binary file (gender.ser)
     *
     * @param path The path to the part2_manifest.txt file containing the names of
     *             all the CSV files to be analyzed.
     * @return The path of the manifest file (part3_manifest.txt) storing the names
     *         of the binary files produced.
     */
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

    //------------------------------------------------------------------------------------------
    //------------------------------------DO_PART3 METHOD-------------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * Deserializes arrays of Movie objects from binary files specified in the part3_manifest.txt file.
     * which will creates and returns a 2D array of type Movie[][] (movie objects) for each
     * binary files named in part3_manifest.txt.
     * All_movies[i][j], where the i'th is the genre (0-16) and the j'th is the movie object
     * within the array of the genre
     *
     * @param path The path to the part3_manifest.txt file containing the names of binary files to be deserialized.
     * @return A 2D array of Movie objects, where all_movies[i][j] represents the j'th Movie object within the
     *         array of all movies of genre i.
     */
    public static Movie [][] do_part3(String path) {
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

    //------------------------------------------------------------------------------------------
    //---------------------------------------MAIN-----------------------------------------------
    //------------------------------------------------------------------------------------------

    public static void main (String[]args)
    {

        String part1_manifest = "part1_manifest.txt"; // creates path for part1_manifest

        String part2_manifest= do_part1(part1_manifest); // sorts the movies and gives the path to part2_manifest

        String part3_manifest = do_part2(part2_manifest); //serializes the movies in genre binary files

        allMovies = do_part3(part3_manifest); //deserializes the movies and returns a 2D array of all the movies

        menu();//the menu operations

    }
    /**
     * A 2D array containing Movie objects. Each element allMovies[i][j] represents the j'th Movie object within
     * the array of all movies of genre i.
     */
    public static Movie [][] allMovies;

    /**
     * Static variable to store the saved index of comedy movies.
     */
    public static int savedIndexComedy = 0;

    /**
     * Static variable to store the saved index of animation movies.
     */
    public static int savedIndexAnimation = 0;

    /**
     * Static variable to store the saved index of adventure movies.
     */
    public static int savedIndexAdventure = 0;

    /**
     * Static variable to store the saved index of drama movies.
     */
    public static int savedIndexDrama = 0;

    /**
     * Static variable to store the saved index of crime movies.
     */
    public static int savedIndexCrime = 0;

    /**
     * Static variable to store the saved index of biography movies.
     */
    public static int savedIndexBiography = 0;

    /**
     * Static variable to store the saved index of horror movies.
     */
    public static int savedIndexHorror = 0;

    /**
     * Static variable to store the saved index of action movies.
     */
    public static int savedIndexAction = 0;

    /**
     * Static variable to store the saved index of documentary movies.
     */
    public static int savedIndexDocumentary = 0;

    /**
     * Static variable to store the saved index of fantasy movies.
     */
    public static int savedIndexFantasy = 0;

    /**
     * Static variable to store the saved index of family movies.
     */
    public static int savedIndexFamily = 0;

    /**
     * Array to store all saved indexes of different movie genres.
     */
    public static int[] allSavedIndexes = {0, savedIndexComedy, savedIndexAnimation, savedIndexAdventure,
            savedIndexDrama, savedIndexCrime, savedIndexBiography, savedIndexHorror, savedIndexAction,
            savedIndexDocumentary, savedIndexFantasy, 0, 0, savedIndexFamily, 0, 0};

    /**
     * Variable to store the previous genre choice.
     */
    public static String previousGenreChoice = "musical";

    /**
     * Variable to store the previous row.
     */
    public static int previousRow = 0;

    /**
     * Variable to store the choice made in the main menu.
     */
    public static String choiceMainMenu = "";

    /**
     * Variable to store the number of movie records to display.
     */
    public static int numberOfMovieRecordTodisplay = 0;


    /**
     * Displays the main menu and handles user input.
     */
    public static void menu() {
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
                        int n = validIntegerInput(input);
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

    /**
     * Navigates to movie records below the current position.
     *
     * @param n The number of records to navigate below the current position.
     */
    public static void navigatePositiveN(int n) {
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

    /**
     * Navigates to movie records above the current position.
     *
     * @param n The number of records to navigate above the current position.
     */
    public static void navigateNegativeN(int n) {
        int nAbs = Math.abs(n);

        if (nAbs - 1 > allSavedIndexes[previousRow]) {

            System.out.println("BOF has been reached");
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
    /**
     * Displays the main menu.
     */
    public static void displayMenu() {
        System.out.print("--------------------------------------------\n\t\t\t\tMain Menu\n--------------------------------------------"+
                "\ns Select a movie\nn navigate "+previousGenreChoice+" movies ("+getNumberOfMovies(previousRow)+" movies)"+"\nx exit\n--------------------------------------------\n\n"
        +"Enter your choice: ");
    }
    /**
     * Displays the genre sub-menu.
     */
    public static void displaySubMenu() {
        System.out.println("--------------------------------------------\n\t\t\t\tGenre Sub-Menu\n--------------------------------------------");
        //each row
        for (int i =0;i<allMovies.length;i++)
        {
            System.out.println(i+1+"\t\t"+VALID_GENRES[i]+"\t\t\t ("+getNumberOfMovies(i)+" movies)");
        }
        System.out.println("--------------------------------------------\nEnter your choice: ");

    }
    /**
     * Gets the number of movies in a genre array.
     *
     * @param previousRow The index of the genre array.
     * @return The number of movies in the genre array.
     */
    public static int getNumberOfMovies(int previousRow) {
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
    /**
     * Validates and retrieves a valid choice from the main menu input.
     *
     * @param input The Scanner object for user input.
     * @return A valid choice from the main menu input.
     */
    public static String getValidChoiceMainMenu(Scanner input) {
        displayMenu();
        String choice = input.next();
        while (choice.equalsIgnoreCase("s")==false&&choice.equalsIgnoreCase("n")==false&&choice.equalsIgnoreCase("x")==false)
        {
            System.out.print("\nThis is not a valid choice please try again: ");
            choice = input.next();
        }
        return choice.toLowerCase();
    }
    /**
     * Validates and retrieves a valid choice from the sub-menu input.
     *
     * @param input The Scanner object for user input.
     * @return A valid choice from the sub-menu input.
     */
    public static int getValidChoiceSubMenu(Scanner input) {
        displaySubMenu();
        int choice =validIntegerInput(input);
        while (choice>17||choice<1)
        {
            System.out.print("\nThis is not a valid choice please try again: ");
            choice = input.nextInt();
        }
        return choice;
    }
}
