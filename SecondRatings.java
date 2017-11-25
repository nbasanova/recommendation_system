
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        //this("ratedmoviesfull.csv", "ratings.csv");
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String movieID, int minRaters){
        FirstRatings fr = new FirstRatings();
        int ratersCount = fr.countMovieRatings(myRaters, movieID);
        if(ratersCount<minRaters)
            return 0.0;
        double count=0;
        for(Rater r: myRaters){
            if(r.hasRating(movieID)){
                count+=r.getRating(movieID);
            }
        }
        return count/ratersCount;
    }
    
    public ArrayList<Rating> getAverageRatings(int minRaters){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = new ArrayList<String>();
        
        for(Rater r: myRaters){
            ArrayList<String> rm = r.getItemsRated();
            for(String movie: rm){
                if(!movies.contains(movie))
                    movies.add(movie);
            }
        }
        
        for(String movie: movies){
            double avgRating = getAverageByID(movie, minRaters);
            if(avgRating!=0.0){
                ratings.add(new Rating(movie, avgRating));
            }
        }
        
        return ratings;
    }
    
    public String getTitle(String movieID){
        for(Movie m: myMovies){
            if(m.getID().equals(movieID))
                return m.getTitle();
        }
        return "Movie not found";
    }
    
    public String getID(String title){
        for(Movie m: myMovies){
            if(m.getTitle().equals(title))
                return m.getID();
        }
        return "Title not found";        
    }
    
    public void printAverageRatings(){
        int minRaters = 3;
        ArrayList<Rating> avgRatings = getAverageRatings(minRaters);
        Collections.sort(avgRatings);
        for(Rating r: avgRatings){
            System.out.println(r.getValue()+ "\t"+ getTitle(r.getItem()));
        }
    }
    
    
}
