package utb.attendancebook.aidClasses;

import android.text.TextUtils;

/**
 * Created by daniela on 23/07/15.
 */
public class WordManagement {

    public static WordManagement WM;

    public String toTitleCase(String str){
        String[] words = str.split("[\\s]+");

        for(int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0,1).toUpperCase()
                    + words[i].substring(1).toLowerCase();
        }

        String titleCase = TextUtils.join(" ", words);
        return titleCase;
    }

    public String toLowerCase(String str){
        String[] words = str.split("[\\s]+");

        for(int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0).toLowerCase();
        }

        String lowerCase = TextUtils.join(" ", words);
        return lowerCase;
    }

    public String firstNameFirstLastname(String names, String lastnames){
        String[] words = names.split("[\\s]+");
        String firstName = words[0];
        words = lastnames.split("[\\s]+");
        String firstLastName = words[0];
        return firstName+" "+firstLastName;
    }

    public String firstNameLastnames(String names, String lastnames){
        String[] words = names.split("[\\s]+");
        String firstName = words[0];
        return firstName+" "+lastnames;
    }

}
