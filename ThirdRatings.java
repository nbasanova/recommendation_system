
/**
 * Write a description of ThirdRratings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        //this("ratedmoviesfull.csv", "ratings.csv");
        this("data/ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for(String movie: movies){
            double avgRating = getAverageByID(movie, minRaters);
            if(avgRating!=0.0){
                ratings.add(new Rating(movie, avgRating));
            }
        }
        
        return ratings;
    }

    public void printAverageRatings(int minRaters){
        ArrayList<Rating> avgRatings = getAverageRatings(minRaters);
        Collections.sort(avgRatings);
        for(Rating r: avgRatings){
            //System.out.println(r.getValue()+ "\t"+ MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minRaters, Filter filterCriteria){
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> moviesFiltered = MovieDatabase.filterBy(filterCriteria);
        
        for(String movie: moviesFiltered){
            double avgRating = getAverageByID(movie, minRaters);
            if(avgRating!=0.0){
                avgRatings.add(new Rating(movie, avgRating));
            }
        }
        
        return avgRatings;
    }
}
