import java.util.Scanner;
import java.io.*;
public class Driver {

    public static String do_part1(String path)
    {
        File part2_manifest = new File("part2_manifest.txt");
        File bad_movie_records = new File("bad_movie_records.txt");
        Scanner scanner = null;
        Scanner scanner2=null;
        PrintWriter writerPart2_manifest=null;
        PrintWriter writerBadMovie=null;

        try
        {
            part2_manifest.createNewFile();
            bad_movie_records.createNewFile();
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

        while (scanner.hasNextLine())
        {
            try
            {
                String file = scanner.nextLine();
                scanner2 = new Scanner(new FileInputStream(file));
            }catch (FileNotFoundException fnfe) {
                System.out.println("Cannot open the file for input.");
                System.exit(0);
            }
            while (scanner2.hasNextLine())
            {
                String movie = scanner2.nextLine();
                try {
                    int count=0;
                    int firstIndexOfGenre=0;
                    int lastIndexOfGenre=0;
                    for(int i=0;i<movie.length();i++)
                    {
                        if(movie.charAt(i)==',')
                        {
                            count++;
                            if(count==3)
                            {
                                firstIndexOfGenre=i+1;
                            }
                            if(count==4)
                            {
                                lastIndexOfGenre=i;
                            }
                        }
                    }
                    System.out.println(count);
                    if (count>9) {
                        throw new ExcessFieldsException();
                    }
                    else
                    {
                        writerPart2_manifest.println(movie.substring(firstIndexOfGenre,lastIndexOfGenre)+".csv");
                    }
                    //System.out.println(movie);
                }catch (ExcessFieldsException efe)
                {
                    System.out.println(movie);
                    writerBadMovie.println(movie);
                }

            }


        }
        writerPart2_manifest.close();
        writerBadMovie.close();
        return "part2_manifest";
    }

    public static void main (String[]args)
    {
        Movie m = new Movie(2004,"I, Robot",115,"Action","PG-13",7.1,"Alex Proyas","Will Smith","Chi Greenwood","Taylor swift");
        Movie q = new Movie(2004,"I, Robot",115,"Action","PG-13",7.1,"Alex Proyas","Will Smith","Chi Greenwood","Taylor swift");
        System.out.println(m);

        String part1_manifest = "part1_manifest.txt";

        String part2_manifest= do_part1(part1_manifest);



//        PrintWriter writer=null;
//        try
//        {
//            writer = new PrintWriter(new FileOutputStream(part1_manifest, true));
//        } catch (FileNotFoundException fnfe) {
//            System.out.println("Cannot open the file.");
//            System.exit(0);
//        }
//        writer.write("Movies1990.csv\n");
//        writer.write("Movies1991.csv\n");
//        writer.write("Movies1992.csv\n");
//        writer.write("Movies1993.csv\n");
//        writer.write("Movies1994.csv\n");
//        writer.write("Movies1995.csv\n");
//        writer.write("Movies1996.csv\n");
//        writer.write("Movies1997.csv\n");
//        writer.write("Movies1998.csv\n");
//        writer.write("Movies1999.csv\n");
//        writer.close();









    }
}
