
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DirectorsFilter implements Filter{
    private ArrayList<String> directors;
    
    public DirectorsFilter(String dir){
        directors = new ArrayList<String>();
        int pos = 0;
        while(true){
            int index = dir.indexOf(",", pos);
            if(index ==-1)
                index = dir.length();
            directors.add(dir.substring(pos,index));
            pos = index+1;
            if(pos > dir.length()) 
                break;
        }
    }
    
    public boolean satisfies(String id){
        String tempDirectors = MovieDatabase.getDirector(id);
        for(String director: directors)
            if(tempDirectors.indexOf(director)!=-1)
                return true;
        return false;
    }
}
