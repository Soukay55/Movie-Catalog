import java.io.Serializable;
public class Movie implements Serializable {

    private int year;
    private String title;
    private int duration;
    private double score;
    private String rating;
    private String genre;
    private String director;
    private String actor1;
    private String actor2;
    private String actor3;

    public Movie(int year,String title,int duration,String genre,String rating,double score,String director,String actor1,String actor2,String actor3)
    {
        this.year=year;
        this.title=title;
        this.duration=duration;
        this.genre=genre;
        this.rating=rating;
        this.score=score;
        this.director=director;
        this.actor1=actor1;
        this.actor2=actor2;
        this.actor3=actor3;
    }

    public void setYear(int year)
    {
        this.year=year;
    }
    public int getYear()
    {
        return this.year;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public String getTitle()
    {
        return this.title;
    }
    public void setDuration(int duration)
    {
        this.duration=duration;
    }
    public int getDuration()
    {
        return this.duration;
    }

    public void setGenre(String genre)
    {
        this.genre=genre;
    }
    public String getGenre()
    {
        return this.genre;
    }
    public void setRating(String rating)
    {
        this.rating=rating;
    }
    public String getRating()
    {
        return this.rating;
    }
    public void setScore(double score)
    {
        this.score=score;
    }
    public double getScore()
    {
        return this.score;
    }
    public void setDirector(String director)
    {
        this.director=director;
    }
    public String getDirector()
    {
        return this.director;
    }
    public void setActor1(String actor1)
    {
        this.actor1=actor1;
    }
    public String getActor1()
    {
        return this.actor1;
    }
    public void setActor2(String actor2)
    {
        this.actor2=actor2;
    }
    public String getActor2()
    {
        return this.actor2;
    }
    public void setActor3(String actor3)
    {
        this.actor3=actor3;
    }
    public String getActor3()
    {
        return this.actor3;
    }

    public String toString()
    {
        return this.year+",\""+this.title+"\","+this.duration+","+this.genre+","+this.rating+","+this.score+","+this.director+","+this.actor1+","
                +this.actor2+","+this.actor3;
    }

    public boolean equals(Object other)
    {
        if(other==null)
            return false;

        if(other.getClass()!=getClass())
            return false;
        else
        {
            Movie otherM = (Movie)other;

            return this.year==otherM.year&&this.title==otherM.title&&this.duration==otherM.duration&&this.genre==otherM.genre&&this.rating==otherM.rating
                    &&this.score==otherM.score&&this.director==otherM.director&&this.actor1==otherM.actor1&&this.actor2==otherM.actor2&&this.actor3==otherM.actor3;
        }
    }






}
