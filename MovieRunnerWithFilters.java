
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Overall "+tr.getRaterSize()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        tr.printAverageRatings(35);
    }
    
    
    public void printAverageRatingsByYear(){
        
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Overall "+tr.getRaterSize()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        int minRaters = 20;
        int year = 2000;
        Filter f = new  YearAfterFilter(year);
        
       ArrayList<Rating> ratingsFiltered = tr.getAverageRatingsByFilter(minRaters,f);
       System.out.println("Found "+ ratingsFiltered.size()+ " movies");
       
       Collections.sort(ratingsFiltered);
       
       for(Rating r: ratingsFiltered){
           //System.out.println(r.getValue()+"\t"+MovieDatabase.getYear(r.getItem())+ "\t"+ MovieDatabase.getTitle(r.getItem()));
       }
    }
    
    public void printAverageRatingsByGenre(){
        
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Overall "+tr.getRaterSize()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        int minRaters = 20;
        String genre = "Comedy";
        Filter f = new  GenreFilter(genre);
        
       ArrayList<Rating> ratingsFiltered = tr.getAverageRatingsByFilter(minRaters,f);
       System.out.println("Found "+ ratingsFiltered.size()+ " movies");
       
       Collections.sort(ratingsFiltered);
       
       for(Rating r: ratingsFiltered){
           //System.out.println(r.getValue()+"\t"+MovieDatabase.getYear(r.getItem())+ "\t"+ MovieDatabase.getTitle(r.getItem())+"\t"+ MovieDatabase.getGenres(r.getItem()));
       }
    }
    
    public void printAverageRatingsByMinutes(){
        
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Overall "+tr.getRaterSize()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        int minRaters = 5;
        int min = 105;
        int max = 135;
        Filter f = new  MinutesFilter(min,max);
        
       ArrayList<Rating> ratingsFiltered = tr.getAverageRatingsByFilter(minRaters,f);
       System.out.println("Found "+ ratingsFiltered.size()+ " movies");
       
       Collections.sort(ratingsFiltered);
       
       for(Rating r: ratingsFiltered){
           //System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t Time: "+ MovieDatabase.getMinutes(r.getItem())+" minutes");
       }
    }
    
    
        public void printAverageRatingsByDirector(){
        
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Overall "+tr.getRaterSize()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        int minRaters = 4;
        String director = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter f = new  DirectorsFilter(director);
        
       ArrayList<Rating> ratingsFiltered = tr.getAverageRatingsByFilter(minRaters,f);
       System.out.println("Found "+ ratingsFiltered.size()+ " movies");
       
       Collections.sort(ratingsFiltered);
       
       for(Rating r: ratingsFiltered){
           //System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t "+ MovieDatabase.getDirector(r.getItem()));
       }
    }
    
   public void printAverageRatingsByYearAfterAndGenre(){
        
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Overall "+tr.getRaterSize()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        int minRaters = 8;
        int year = 1990;
        String genre = "Drama";
        AllFilters af = new  AllFilters();
        //Filter yf = ;
        Filter gf = new  GenreFilter(genre);
        
        af.addFilter(new  YearAfterFilter(year));
        af.addFilter(gf);
        
       ArrayList<Rating> ratingsFiltered = tr.getAverageRatingsByFilter(minRaters,af);
       System.out.println("Found "+ ratingsFiltered.size()+ " movies");
       
       Collections.sort(ratingsFiltered);
       
       for(Rating r: ratingsFiltered){
           //System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\t"+MovieDatabase.getYear(r.getItem())+"\t "+ MovieDatabase.getGenres(r.getItem()));
       }
    }
   
   public void printAverageRatingsByDirectorsAndMinutes(){
        
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Overall "+tr.getRaterSize()+" raters");
        MovieDatabase.initialize(moviefile);
        System.out.println("Overall "+MovieDatabase.size()+" movies");
        
        int minRaters = 3;
        int minMinutes = 90;
        int maxMinutes = 180;
        String director = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        AllFilters af = new  AllFilters();

        af.addFilter(new  MinutesFilter(minMinutes, maxMinutes));
        af.addFilter(new  DirectorsFilter(director));
        
       ArrayList<Rating> ratingsFiltered = tr.getAverageRatingsByFilter(minRaters,af);
       System.out.println("Found "+ ratingsFiltered.size()+ " movies");
       
       Collections.sort(ratingsFiltered);
       
       for(Rating r: ratingsFiltered){
           //System.out.println(r.getValue()+"\t"+ MovieDatabase.getTitle(r.getItem())+"\tTime: "+MovieDatabase.getMinutes(r.getItem())+"\t "+ MovieDatabase.getDirector(r.getItem()));
       }
   }
   

}    
