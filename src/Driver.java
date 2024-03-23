import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.nio.channels.ScatteringByteChannel;
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

//  static void writeToFile(String movie,String path)
//  {
//      PrintWriter writer =null;
//
//      try {
//          writer = new PrintWriter(new FileOutputStream(path));
//          writer.write(movie+"\n");
//      }
//      catch (FileNotFoundException fnfe)
//      {
//          System.out.println("file not found");
//          System.exit(0);
//      }
//  }

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

    public static String do_part2Souk(String path)
    {
        Scanner scannerPart2_manifest = null;
        Scanner genreFile = null;
        String fileGenre=null;
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

            int countMovies=0;//?
            int indexArray=0;//yes
            String movie="";//yes
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
            System.out.println(countMovies);
            genreFile.close();
            Movie [] movies = new Movie[countMovies];
            String genre ="";

            try{
                genreFile = new Scanner(new FileInputStream(fileGenre));
            }
            catch (FileNotFoundException fnfe) {
                System.out.println("File not found");
                System.exit(0);
            }
            for (int i=0;i<movies.length;i++) {
                movie = genreFile.nextLine();
                genre = getGenre(movie);

                movies[i] = new Movie(getYear(movie), getTitle(movie), getDuration(movie), getGenre(movie), getRating(movie), getScore(movie)
                        , getDirector(movie), getActor1(movie), getActor2(movie), getActor3(movie));

            }

            //serialize the array HERE

            genreFile.close();
        }
        return "part3_manifest";
    }
//-------------------------SECTION WISSEM DEBUT----------------------------

    public static void do_part2(){
        File manifest = new File("part3_manifest.txt");
        try{
            manifest.createNewFile();
        }catch(IOException ioe){
            System.out.println("oups");
        }
    }
//-------------------------SECTION WISSEM FIN----------------------------
    public static void main (String[]args)
    {
        Movie m = new Movie(2004,"I, Robot",115,"Action","PG-13",7.1,"Alex Proyas","Will Smith","Chi Greenwood","Taylor swift");

        String part1_manifest = "part1_manifest.txt";

        String part2_manifest= do_part1(part1_manifest);

        String part3_manifest = do_part2Souk(part2_manifest);
    }
}
