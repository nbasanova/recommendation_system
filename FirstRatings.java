
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            movies.add(new Movie(record.get("id"),
                                 record.get("title"),
                                 record.get("year"),
                                 record.get("genre"),
                                 record.get("director"),
                                 record.get("country"),
                                 record.get("poster"),
                                 Integer.parseInt(record.get("minutes"))));
        }
        return movies;
    }
    
    private HashMap<String, Integer> buildDirectorList(ArrayList<Movie> movies){
        HashMap<String, Integer> directors = new HashMap<String, Integer>();
        for(Movie m: movies){
            String dirs = m.getDirector();
            int pos = 0;
            while(true){
                int end = dirs.indexOf(",", pos);
                if(end==-1){
                    end = dirs.length();
                }
                String dir = dirs.substring(pos, end);
                if(!directors.containsKey(dir))
                    directors.put(dir,1);
                else
                    directors.put(dir, directors.get(dir)+1);
                pos = end+2;
                if(pos > dirs.length())
                    break;
            }
        }
        return directors;
    }
    
    private int maxMoviesPerDirector(HashMap<String, Integer> directors){
        int max = 0;
        for(String director: directors.keySet()){
            if(directors.get(director) > max)
                max = directors.get(director);
        }
        return max;
    }
    
    private ArrayList<String> directorsWithCountMovies(HashMap<String, Integer> directors, int count){
        ArrayList<String> dirs = new ArrayList<String>();
        for(String director: directors.keySet()){
            if(directors.get(director) == count)
                dirs.add(director);
        }
        return dirs;
    }
    
    public void testLoadMovies(){
        //String filename = "data/ratedmovies_short.csv";
        String filename = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies(filename);
        System.out.println("Overall "+ movies.size()+ " movies");
        ///*
        int comediesCount = 0;
        int longMovieCount = 0;
        for(Movie m: movies){
            //System.out.println(m);
            if(m.getGenres().indexOf("Comedy")!=-1)
                comediesCount++;
            if(m.getMinutes()>150)
                longMovieCount++;
                
        }
        System.out.println("Comedies count: "+comediesCount);
        System.out.println("Count of the movies longer than 150 minutes: "+longMovieCount);
        HashMap<String, Integer> directors = buildDirectorList(movies);
        int maxMovies = maxMoviesPerDirector(directors);
        ArrayList<String> productiveDirectors =  directorsWithCountMovies(directors, maxMovies);
        System.out.println("Max movies per director = "+ maxMovies+ " made by "+productiveDirectors.size()+" directors");
        System.out.println(productiveDirectors);
  
        //*/
    }
    
    private HashMap<String, ArrayList<Rating>> buildRatersMap(CSVParser parser){
                
        HashMap<String, ArrayList<Rating>> raterMap = new HashMap<String, ArrayList<Rating>>();
        
        for(CSVRecord record: parser){
            String raterID = record.get("rater_id");
            if(!raterMap.containsKey(raterID)){
                ArrayList<Rating> ratings = new ArrayList<Rating>();
                ratings.add(new Rating(record.get("movie_id"), 
                                       Double.parseDouble(record.get("rating"))));
                raterMap.put(raterID, ratings);                       
            }
            else{
                ArrayList<Rating> ratings = raterMap.get(raterID);
                ratings.add(new Rating(record.get("movie_id"), 
                                          Double.parseDouble(record.get("rating"))));
                raterMap.put(raterID, ratings);
            }
        }
        return raterMap;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        HashMap<String, ArrayList<Rating>> raterMap = buildRatersMap(parser);
        ArrayList<Rater> raters = new ArrayList<Rater>();
        
        for(String raterID: raterMap.keySet()){
            //System.out.println(raterMap.get(raterID));
            Rater tempRater = new EfficientRater(raterID);
            
            for(Rating r: raterMap.get(raterID)){
                tempRater.addRating(r.getItem(), r.getValue());
            }
            raters.add(tempRater);
        }
        
        return raters;
    }
    
    private int findNumOfRatings(ArrayList<Rater> raters, String raterID){
        for(Rater r: raters){
            if(r.getID().equals(raterID))
                return r.getItemsRated().size();
        }
        return -1;
    }
    
    private int maxNumOfRatings(ArrayList<Rater> raters){
        int max = 0;
        for(Rater r: raters){
            if(r.getItemsRated().size()>max)
                max = r.getItemsRated().size();
        }
        return max;
    }
    
    private ArrayList<String> ratersWithCountRatings(ArrayList<Rater> raters, int count){
        ArrayList<String> ratersIDs = new ArrayList<String>();
        for(Rater r: raters){
            if(r.getItemsRated().size() == count)
                ratersIDs.add(r.getID());
        }
        return ratersIDs;
    }
    
    public int countMovieRatings(ArrayList<Rater> raters, String movieID){
        int count=0;
        for(Rater r: raters){
            ArrayList<String> movies = r.getItemsRated();
            for(String m: movies){
                if(m.equals(movieID))
                    count++;
            }
        }
        return count;
    }
    
    private int countAllMovies(ArrayList<Rater> raters){
        ArrayList<String> movies = new ArrayList<String>();
        
        for(Rater r: raters){
            ArrayList<String> rm = r.getItemsRated();
            for(String movie: rm){
                if(!movies.contains(movie))
                    movies.add(movie);
            }
        }
        return movies.size();
    }
    
    public void testLoadRaters(){
        //String filename = "data/ratings_short.csv";
        String filename = "data/ratings.csv";
        ArrayList<Rater> raters = loadRaters(filename);
        System.out.println("Overall "+ raters.size()+ " raters");
        String raterID = "193";
        System.out.println("Number of ratings for user with ID="+ raterID+" is "+findNumOfRatings(raters,raterID));
        int max = maxNumOfRatings(raters);
        ArrayList<String> maxRatersIDs = ratersWithCountRatings(raters, max);
        System.out.println("Max number of ratings is "+max);
        System.out.println("Number of users with max number of ratings is "+ maxRatersIDs.size());
        System.out.println("Those users are: "+ maxRatersIDs);
        String movieID = "1798709";
        System.out.println("Number of ratings for movie with ID="+movieID+ " is "+countMovieRatings(raters, movieID));
        System.out.println("Overall "+ countAllMovies(raters)+" movies have been rated");
        /*
        for(Rater r: raters){
           // System.out.println(r.getID()+ " with "+r.getItemsRated().size()+ " ratings total");
        }
        */
    }
}
