
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        String ratingsfile = "data/ratings_short.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        //String moviefile = "data/ratedmoviesfull.csv";
        
        SecondRatings sr = new SecondRatings(moviefile,ratingsfile);
        System.out.println("Overall "+sr.getMovieSize()+" movies");
        System.out.println("Overall "+sr.getRaterSize()+" raters");
    }
    
    public void getAverageRatingOneMovie(){
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        
       // String moviefile = "data/ratedmovies_short.csv";
        String moviefile = "data/ratedmoviesfull.csv";
        
        String movieTitle = "Vacation";
        int minRaters = 2;
        
        SecondRatings sr = new SecondRatings(moviefile,ratingsfile);
        System.out.println("Overall "+sr.getMovieSize()+" movies");
        System.out.println("Overall "+sr.getRaterSize()+" raters");
        
        String movieID = sr.getID(movieTitle);
        System.out.println("The avg rating for movie "+movieTitle+" is "+ sr.getAverageByID(movieID, minRaters));
        
        ArrayList<Rating> avgRatings = sr.getAverageRatings(12);
        Rating r = avgRatings.get(0);
        for(Rating temp: avgRatings){
            if(temp.compareTo(r)==-1)
                r = temp;
        }
        System.out.println("The movie with the lowest rating: "+sr.getTitle(r.getItem()));
        
        //System.out.println("Number of movies with more than 50 ratings: "+avgRatings.size());
    }
}
