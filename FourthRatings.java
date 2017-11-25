
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {

    public double getAverageByID(String movieID, int minRaters){
        FirstRatings fr = new FirstRatings();
        int ratersCount = fr.countMovieRatings(RaterDatabase.getRaters(), movieID);
        if(ratersCount<minRaters)
            return 0.0;
        double count=0;
        for(Rater r: RaterDatabase.getRaters()){
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
    
    private int dotProduct(Rater me, Rater r){
        ArrayList<String> myMovies = me.getItemsRated();
        int product = 0;
        
        for(String movie: myMovies){
            if(r.hasRating(movie))
                product+=(me.getRating(movie)-5)*(r.getRating(movie)-5);
        }
        
        return product;
    }
           
    private ArrayList<Rating> getSimilarities(String id){
       ArrayList<Rating> list = new ArrayList<Rating>();
       Rater me = RaterDatabase.getRater(id);
       for(Rater r: RaterDatabase.getRaters()){
           if(r!=me){
                int product = dotProduct(me, r);
                if(product>=0)
                    list.add(new Rating(r.getID(), product));
           }         
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getRecommendations(String id, int numRaters, int minimalRaters){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID: movies){ 
            double ratingSum = 0;
            int count = 0;
            for(int k=0; k< numRaters;k++){
                Rating r = list.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                if(r.getValue() > 0){
                    Rater tempRater = RaterDatabase.getRater(raterID);
                    if(tempRater.hasRating(movieID)){
                        ratingSum += tempRater.getRating(movieID)*weight;
                        count++;
                    }
                    //use rater id and weight in r to update running totals
                }
            }
            if(count>=minimalRaters){
                ret.add(new Rating(movieID, ratingSum/count));
            }
            //add rating for movie ID to ret
        }    
        Collections.sort(ret, Collections.reverseOrder());
        return ret; // sort first
    }
    
    
    public ArrayList<Rating> getRecommendations(String id, int numRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movieID: movies){ 
            double ratingSum = 0;
            int count = 0;
            for(int k=0; k< numRaters;k++){
                Rating r = list.get(k);
                String raterID = r.getItem();
                 double weight = r.getValue();
                if(r.getValue() > 0){
                    Rater tempRater = RaterDatabase.getRater(raterID);
                    if(tempRater.hasRating(movieID)){
                        ratingSum += tempRater.getRating(movieID)*weight;
                        count++;
                    }
                }
            }
            if(count>=minimalRaters){
                ret.add(new Rating(movieID, ratingSum/count));
            }
            
        }    
        Collections.sort(ret, Collections.reverseOrder());
        return ret; 
    }
}
