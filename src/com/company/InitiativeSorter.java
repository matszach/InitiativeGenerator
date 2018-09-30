package com.company;

import java.util.*;

public class InitiativeSorter {

   public ArrayList<CreaturePanel> sortByInitScore (ArrayList<CreaturePanel> crts){

       Collections.sort(crts, new Comparator<CreaturePanel>() {

           @Override
           public int compare(CreaturePanel o1, CreaturePanel o2) {

               if (o1.getInitScore() == o2.getInitScore()){

                   if (o1.getInitBonus() == o2.getInitBonus()){

                       Random rand = new Random();

                       int decide = rand.nextInt(2);

                       if (decide == 0){
                           return 1;
                       } else {
                           return -1;
                       } // if 3

                   } else {
                       return o2.getInitBonus() - o1.getInitBonus();
                   } // if 2

               } else {
                   return o2.getInitScore() - o1.getInitScore();
               } // if 1

           } // compare
       }); // sort


        /*
        for (int i = 0; i < crts.size(); i++){

            for (int j = 0; j < crts.size(); j++){

                if (crts.get(i).getInitScore() < crts.get(j).getInitScore()) {

                    CreaturePanel temp;
                    temp = crts.get(i);
                    crts.add(i,crts.get(j));
                    crts.add(j,temp);

                } else if (crts.get(i).getInitScore() == crts.get(i).getInitScore()
                        && crts.get(i).getInitBonus() < crts.get(j).getInitBonus()){

                    CreaturePanel temp;
                    temp = crts.get(i);
                    crts.add(i,crts.get(j));
                    crts.add(j,temp);

                } // if

            } // for j

        } // for i

        */

        return crts;
    }

}
