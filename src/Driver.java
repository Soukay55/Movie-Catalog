import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.Scanner;
import java.io.*;
public class Driver {

    final static String [] VALID_GENRES= {"musical","comedy","animation","adventure","drama","crime","biography",
    "horror","action","documentary","fantasy","mystery","sci-fi","family","romance","thriller",
    "western"};
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

  static int indexEmptyField(String movie)
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
      return index;
  }

//  static boolean containsQuotes(String movie)
//  {
//      int index1 = getIndexChar(movie,'"',1);
//      int index2=getIndexChar(movie,'"',2);
//      if(index1==0||index2==0)
//      {
//          return false;
//      } else if (movie.charAt()) {
//
//      }
//  }

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

        Scanner scanner = null;
        Scanner scanner2=null;
        PrintWriter writerPart2_manifest=null;
        PrintWriter writerBadMovie=null;
        PrintWriter writerGenre=null;

        try
        {
            for (int i=0;i<files.length;i++)
            {
                files[i].createNewFile();
            }

            writerPart2_manifest = new PrintWriter(new FileOutputStream("part2_manifest.txt", true));
            writerBadMovie = new PrintWriter(new FileOutputStream("bad_movie_records.txt", true));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot open the file.");
            System.exit(0);
        }
        catch (IOException ioe)
        {
            System.out.println("Cannot create the file.");
            System.exit(0);
        }

        try {
            scanner = new Scanner(new FileInputStream(path));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot open the file for input.");
            System.exit(0);
        }

        //big loop starts here of reading each line of each doc
        while (scanner.hasNextLine())
        {
            //counts the position of the movie in his own file
            int countOfPositionMovie =0;
            String file ="";
            try
            {
                file = scanner.nextLine();
                scanner2 = new Scanner(new FileInputStream(file));
            }catch (FileNotFoundException fnfe) {
                System.out.println("Cannot open the file for input.");
                System.exit(0);
            }
            while (scanner2.hasNextLine())
            {
                String movie = scanner2.nextLine();
                int firstIndexOfGenre=0;
                int lastIndexOfGenre=0;
                int countOfCommas=0;
                try {
                    firstIndexOfGenre=getIndexChar(movie,',',3)+1;
                    lastIndexOfGenre=getIndexChar(movie,',',4);
                    countOfCommas=getNumberRepetitionChar(movie,',');
                    int ignoreCommas=0;
                    int firstIndexOfApostrophe = getIndexChar(movie,'"',1);
                    int lastIndexOfApostrophe = getIndexChar(movie,'"',2);

                    //getting right number of commas
                    String titleInQuotes = movie.substring(firstIndexOfApostrophe,lastIndexOfApostrophe+1);
                    if(titleInQuotes.contains(",")) {
                        ignoreCommas = getNumberRepetitionChar(titleInQuotes, ',');
                        countOfCommas -= ignoreCommas;
                    }

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

                    //invalid duration A FAIRE NE MARCHE PAS
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
                    //invalid genre this one should be the last
                    if(genre.equals(""))
                    {
                        throw new BadGenreException();
                    }
                    boolean same=false;
                    for(int i=0;i< VALID_GENRES.length;i++)
                    {
                        if (genre.equalsIgnoreCase(VALID_GENRES[i]))
                        {
                            writerPart2_manifest.println(VALID_GENRES[i]+".csv");
                            //rajouter le movie a son file
                            same=true;
                            break;
                        }
                    }
                    if (same==false)
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
                catch (BadGenreException bge)
                {
                    writerBadMovie.println("Semantic error: Invalid genre: "+movie+"\nin file: "+file+" position: "+(countOfPositionMovie+1));
                }


                countOfPositionMovie++;

            }
        }
        writerPart2_manifest.close();
        writerBadMovie.close();
        return "part2_manifest";
    }

    public static void main (String[]args)
    {
        Movie m = new Movie(2004,"I, Robot",115,"Action","PG-13",7.1,"Alex Proyas","Will Smith","Chi Greenwood","Taylor swift");
        System.out.println(m);

        String part1_manifest = "part1_manifest.txt";

        String part2_manifest= do_part1(part1_manifest);









    }
}
