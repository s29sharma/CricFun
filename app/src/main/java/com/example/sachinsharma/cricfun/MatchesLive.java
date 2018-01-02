package com.example.sachinsharma.cricfun;


import java.util.ArrayList;
import java.util.List;
/**
 * Created by sachinsharma on 1/1/18.
 */

public class MatchesLive {

    int dateofmatch;
    List<MatchView> matches;

    public MatchesLive(){
        matches=MatchView.listAll(MatchView.class);
        if(matches.size()==0) {
            MatchView matchView1 = new MatchView("Ind \uD83C\uDDEE\uD83C\uDDF3", "\uD83C\uDDFF\uD83C\uDDE6 SA ", "10:00", "1");
            matchView1.save();
            MatchView matchView2 = new MatchView("Aus \uD83C\uDDE6\uD83C\uDDFA", "\uD83C\uDDEC\uD83C\uDDE7 Eng", "10:00", "1");
            matchView2.save();
            MatchView matchView3 = new MatchView("NZ \uD83C\uDDF3\uD83C\uDDFF", "\uD83C\uDDF9\uD83C\uDDF9 WI ", "10:00", "3");
            matchView3.save();
            MatchView matchView4 = new MatchView("Pak \uD83C\uDDF5\uD83C\uDDF0", "\uD83C\uDDEE\uD83C\uDDEA Ire ", "10:00", "2");
            matchView4.save();
        }
    }


    public List<MatchView> returnMatches(String date){
        matches=MatchView.findWithQuery(MatchView.class,"select * from Match_View where date=?",date);
        return matches;
    }

    public int getSize(){
        matches=MatchView.listAll(MatchView.class);
        return matches.size();
    }


    public String toString(){
        return "MatchesLive{"+
                "matchesLive="+matches+
                '}';
    }

}
