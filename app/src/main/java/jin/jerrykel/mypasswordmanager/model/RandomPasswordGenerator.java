package jin.jerrykel.mypasswordmanager.model;

import android.util.Log;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class RandomPasswordGenerator {
    private static final String ERROR__CODE ="" ;
    PasswordGenerator gen = new PasswordGenerator();

    //default
    public String generatePassayPassword() {

        //definition des alphabetical char type majuscule
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(3);


        //definition des alphabetical char type miniscule
        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

       //definition des chars type number
        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);


        //definition des caratere specila
        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR__CODE;
            }

            public String getCharacters() {
                return "[email protected]#$%^&** ()__+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);



        //CharacterData upperCaseCharC = EnglishCharacterData.Alphabetical;
      //  CharacterRule uppercaseRulesC = new CharacterRule(upperCaseCharC);
       // uppercaseRulesC.setNumberOfCharacters(2);


        // definition du pass finale
        String password = gen.generatePassword(12, splCharRule, lowerCaseRule,upperCaseRule, digitRule);
       // Log.d("**********password", password+" : "+(password.length()));
        return password;
    }
    //quand l'utilisateur modifie seulement et seulement la longeur du password
    public String generatePassayPassword(int passLenght) {

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(3);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR__CODE;
            }

            public String getCharacters() {
                return "[email protected]#$%^&** ()__+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(passLenght, splCharRule, lowerCaseRule,upperCaseRule, digitRule);
        return password;
    }
    //quand modifie les caratere speciaux
    public String generatePassayPassword(int passLenght,int nbSpecialChar,String strspecialChars) {

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(3);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR__CODE;
            }

            public String getCharacters() {

                return "[email protected]"+strspecialChars;
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(nbSpecialChar);

        String password = gen.generatePassword(passLenght, splCharRule, lowerCaseRule,upperCaseRule, digitRule);
        return password;
    }
    //quand il veut pas de upperCase
    public String generatePassayPassword(int passLenght,int minLower) {

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(minLower);



        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR__CODE;
            }

            public String getCharacters() {

                return "[email protected]#$%^&** ()__+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(passLenght, splCharRule, lowerCaseRule, digitRule);
        return password;
    }
    //quand il veut pas de lower case
    public String generatePassayPassword(int passLenght,int minUpper,Character l) {


        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(minUpper);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR__CODE;
            }

            public String getCharacters() {

                return "[email protected]#$%^&** ()__+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(passLenght, splCharRule,upperCaseRule, digitRule);
        return password;
    }

    //quand il veut pas de nombre
    public String generatePassayPassword(int passLenght,int nbSpecialChar,int minUpper) {

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(3);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);


        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR__CODE;
            }

            public String getCharacters() {

                return "[email protected]#$%^&** ()__+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(nbSpecialChar);

        String password = gen.generatePassword(passLenght, splCharRule, lowerCaseRule,upperCaseRule);
        return password;
    }
    public String generatePassayPassword(int passLength, int upperCase,int lowerCase, int nbr, int specialCharsnbr, String customSpecialChars) {

        //definition des alphabetical char type majuscule
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(lowerCase);


        //definition des alphabetical char type miniscule
        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(upperCase);

        //definition des chars type number
        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(nbr);


        //definition des caratere specila
        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR__CODE;
            }

            public String getCharacters() {
                return "[email protected]"+customSpecialChars;
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(specialCharsnbr);



        //CharacterData upperCaseCharC = EnglishCharacterData.Alphabetical;
        //  CharacterRule uppercaseRulesC = new CharacterRule(upperCaseCharC);
        // uppercaseRulesC.setNumberOfCharacters(2);


        // definition du pass finale
        String password = gen.generatePassword(12, splCharRule, lowerCaseRule,upperCaseRule, digitRule);
        // Log.d("**********password", password+" : "+(password.length()));
        return password;
    }

}
