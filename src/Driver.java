import java.util.Scanner;
import java.io.*;
public class Driver {

    public String do_part1(String path)
    {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(path));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot open the file for input.");
            System.exit(0);
        }
        return path;
    }

    public static void main (String[]args)
    {
        Movie m = new Movie(2004,"I, Robot",115,"Action","PG-13",7.1,"Alex Proyas","Will Smith","Chi Greenwood","Taylor swift");
        Movie q = new Movie(2004,"I, Robot",115,"Action","PG-13",7.1,"Alex Proyas","Will Smith","Chi Greenwood","Taylor swift");
        System.out.println(m);

        String part1_manifest = "part1_manifest.txt";



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
