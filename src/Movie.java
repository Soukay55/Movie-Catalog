import java.io.Serializable;

/**
 * The Movie class represents a movie with various attributes such as year, title, duration, genre, rating,
 * score, director, and actors. It implements the Serializable interface to allow objects of this class to be
 * serialized and deserialized.
 */

/**
 * Wissem Oumsalem (40291712) <br>
 * Soukayna Haitami (40280964) <br>
 * COMP 249 <br>
 * Assignment #2 <br>
 * Due : March 27th, 2024
 *
 */
public class Movie implements Serializable {

    /**
     * The year of the movie.
     */
    private int year;
    /**
     * The title of the movie.
     */
    private String title;
    /**
     * The duration of the movie in minutes.
     */
    private int duration;

    /**
     * The score of the movie.
     */
    private double score;

    /**
     * The rating of the movie.
     */
    private String rating;

    /**
     * The genre of the movie.
     */
    private String genre;

    /**
     * The director of the movie.
     */
    private String director;

    /**
     * The first actor of the movie.
     */
    private String actor1;

    /**
     * The second actor of the movie.
     */
    private String actor2;

    /**
     * The third actor of the movie.
     */
    private String actor3;

    /**
     * Constructs a Movie object with the given attributes.
     *
     * @param year     The year of the movie.
     * @param title    The title of the movie.
     * @param duration The duration of the movie in minutes.
     * @param genre    The genre of the movie.
     * @param rating   The rating of the movie.
     * @param score    The score of the movie.
     * @param director The director of the movie.
     * @param actor1   The first actor of the movie.
     * @param actor2   The second actor of the movie.
     * @param actor3   The third actor of the movie.
     */

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

    /**
     * Sets the year of the movie.
     *
     * @param year The year of the movie.
     */
    public void setYear(int year)
    {
        this.year=year;
    }
    /**
     * Gets the year of the movie.
     *
     * @return The year of the movie.
     */
    public int getYear()
    {
        return this.year;
    }
    /**
     * Sets the title of the movie.
     *
     * @param title The title of the movie.
     */
    public void setTitle(String title)
    {
        this.title=title;
    }
    /**
     * Gets the title of the movie.
     *
     * @return The title of the movie.
     */
    public String getTitle()
    {
        return this.title;
    }
    /**
     * Sets the duration of the movie in minutes.
     *
     * @param duration The duration of the movie in minutes.
     */
    public void setDuration(int duration)
    {
        this.duration=duration;
    }
    /**
     * Gets the duration of the movie in minutes.
     *
     * @return The duration of the movie in minutes.
     */
    public int getDuration()
    {
        return this.duration;
    }
    /**
     * Sets the genre of the movie.
     *
     * @param genre The genre of the movie.
     */
    public void setGenre(String genre)
    {
        this.genre=genre;
    }
    /**
     * Gets the genre of the movie.
     *
     * @return The genre of the movie.
     */
    public String getGenre()
    {
        return this.genre;
    }
     /** Sets the rating of the movie.
      *
      * @param rating The rating of the movie.
      */
    public void setRating(String rating)
    {
        this.rating=rating;
    }
    /**
     * Gets the rating of the movie.
     *
     * @return The rating of the movie.
     */
    public String getRating()
    {
        return this.rating;
    }
    /**
     * Sets the score of the movie.
     *
     * @param score The score of the movie.
     */
    public void setScore(double score)
    {
        this.score=score;
    }
    /**
     * Gets the score of the movie.
     *
     * @return The score of the movie.
     */
    public double getScore()
    {
        return this.score;
    }
    /**
     * Sets the director of the movie.
     *
     * @param director The director of the movie.
     */
    public void setDirector(String director)
    {
        this.director=director;
    }
    /**
     * Gets the director of the movie.
     *
     * @return The director of the movie.
     */
    public String getDirector()
    {
        return this.director;
    }
    /**
     * Sets the first actor of the movie.
     *
     * @param actor1 The first actor of the movie.
     */
    public void setActor1(String actor1)
    {
        this.actor1=actor1;
    }
    /**
     * Gets the first actor of the movie.
     *
     * @return The first actor of the movie.
     */
    public String getActor1() {return this.actor1;}
    /**
     * Sets the second actor of the movie.
     *
     * @param actor2 The second actor of the movie.
     */
    public void setActor2(String actor2)
    {
        this.actor2=actor2;
    }
    /**
     * Gets the second actor of the movie.
     *
     * @return The second actor of the movie.
     */
    public String getActor2()
    {
        return this.actor2;
    }
    /**
     * Sets the third actor of the movie.
     *
     * @param actor3 The third actor of the movie.
     */
    public void setActor3(String actor3)
    {
        this.actor3=actor3;
    }
    /**
     * Gets the third actor of the movie.
     *
     * @return The third actor of the movie.
     */
    public String getActor3()
    {
        return this.actor3;
    }

    /**
     * Returns a string representation of the Movie object.
     *
     * @return A string containing the movie attributes separated by commas.
     */
    public String toString()
    {
        return this.year+","+this.title+","+this.duration+","+this.genre+","+this.rating+","+this.score+","+this.director+","+this.actor1+","
                +this.actor2+","+this.actor3;
    }

    /**
     * Checks if this Movie object is equal to another object.
     * @param other The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
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
