
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        String ratingsfile = "ratings_short.csv";
        //String ratingsfile = "ratings.csv";
        
        String moviefile = "ratedmovies_short.csv";
        //String moviefile = "ratedmoviesfull.csv";
        
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        fr.getAverageRatings(35);
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        
        String ratingsfile = "ratings_short.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "ratedmovies_short.csv";
        //String moviefile = "ratedmoviesfull.csv";
        
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        int minRaters = 1;
        int year = 2013;
        String genre = "Drama";
        AllFilters af = new  AllFilters();
        //Filter yf = ;
        Filter gf = new  GenreFilter(genre);
        
        af.addFilter(new  YearAfterFilter(year));
        af.addFilter(gf);
        
       ArrayList<Rating> ratingsFiltered = fr.getAverageRatingsByFilter(minRaters,af);
       System.out.println("Found "+ ratingsFiltered.size()+ " movies");
       
       Collections.sort(ratingsFiltered);
       
       for(Rating r: ratingsFiltered){
           System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t"+MovieDatabase.getYear(r.getItem())+"\t "+ MovieDatabase.getGenres(r.getItem()));
       }
    }
    
    public void printSimilarRatings(){
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        System.out.println("SimilarRatings");
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        String raterID = "71";
        int numRaters = 20;
        int minRaters = 5;
        ArrayList<Rating> rec = fr.getRecommendations(raterID, numRaters, minRaters);
        
        System.out.println("Found "+rec.size()+" movies");
        for(Rating r: rec){
            System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t"+MovieDatabase.getYear(r.getItem())+"\t "+ MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre(){
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        String genre = "Mystery";
        String raterID = "964";
        int numRaters = 20;
        int minRaters = 5;
        
        ArrayList<Rating> rec = fr.getRecommendations(raterID, numRaters, minRaters, new GenreFilter(genre));
        
        System.out.println("Found "+rec.size()+" movies");
        for(Rating r: rec){
            System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t"+MovieDatabase.getYear(r.getItem())+"\t "+ MovieDatabase.getGenres(r.getItem()));
        }
    } 
    
    
    public void printSimilarRatingsByDirector(){
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        String raterID = "120";
        int numRaters = 10;
        int minRaters = 2;
        
        ArrayList<Rating> rec = fr.getRecommendations(raterID, numRaters, minRaters, new DirectorsFilter(director));
        
        System.out.println("Found "+rec.size()+" movies");
        for(Rating r: rec){
            System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t"+MovieDatabase.getYear(r.getItem())+"\t "+ MovieDatabase.getGenres(r.getItem()));
        }
    }   
    
    public void printSimilarRatingsByGenreAndMinutes(){
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        String genre = "Drama";
        String raterID = "168";
        int numRaters = 10;
        int minRaters = 3;
        int minMin = 80;
        int maxMin = 160;
        
        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter(genre));
        af.addFilter(new MinutesFilter(minMin, maxMin));
        
        ArrayList<Rating> rec = fr.getRecommendations(raterID, numRaters, minRaters, af);
        
        System.out.println("Found "+rec.size()+" movies");
        for(Rating r: rec){
            System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t"+MovieDatabase.getYear(r.getItem())+"\t "+ MovieDatabase.getGenres(r.getItem()));
        }
    } 
    
    public void printSimilarRatingsByYearAndMinutes(){
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        int year = 1975;
        String raterID = "314";
        int numRaters = 10;
        int minRaters = 5;
        int minMin = 70;
        int maxMin = 200;
        
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(year));
        af.addFilter(new MinutesFilter(minMin, maxMin));
        
        ArrayList<Rating> rec = fr.getRecommendations(raterID, numRaters, minRaters, af);
        
        System.out.println("Found "+rec.size()+" movies");
        for(Rating r: rec){
            System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t"+MovieDatabase.getYear(r.getItem())+"\t "+ MovieDatabase.getGenres(r.getItem()));
        }
    } 
    
    public void runRecommender(){
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        Recommender r = new RecommendationRunner();
        ArrayList<String> movies = r.getItemsToRate();
        System.out.println("Overall "+ movies.size());
        for(String movie: movies){
            System.out.print(movie+ "\t");
            System.out.println(MovieDatabase.getTitle(movie));
        }
    }
    
   public void testHTML(){
        /*       
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Overall "+tr.getRaterSize()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        */
               //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Overall "+RaterDatabase.size()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        String raterID = "71";
       
        RecommendationRunner rr = new RecommendationRunner();
        rr.printRecommendationsFor(raterID);
   }
    
}
