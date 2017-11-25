
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter{
    String myGenre;
    
    public GenreFilter(String genre){
        myGenre = genre;
    }
    
    public boolean satisfies(String id){
        
        String genres = MovieDatabase.getGenres(id);
        if(genres.indexOf(myGenre)!=-1)
            return true;
            
        return false;
    }
}
