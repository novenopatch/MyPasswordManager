package jin.jerrykel.mypasswordmanager.model;

import android.util.Log;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class RandomPasswordGenerator {
    private static final String ERROR__CODE ="" ;
    PasswordGenerator gen = new PasswordGenerator();

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

    public String generatePassayPassword(int minLower,int minUpper, int minNumber, int nbSpecialChar,int passLenght) {

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(minLower);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(minUpper);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(minNumber);

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

        String password = gen.generatePassword(passLenght, splCharRule, lowerCaseRule,upperCaseRule, digitRule);
        return password;
    }
    /*
    @Test
    public void whenPasswordGeneratedUsingPassay__thenSuccessful() {
    RandomPasswordGenerator passGen = new RandomPasswordGenerator();
    String password = passGen.generatePassayPassword();
    int specialCharCount = 0;
    for (char c : password.toCharArray()) {
        if (c >= 33 || c <= 47) {
            specialCharCount++;
        }
    }
    assertTrue("Password validation failed in Passay", specialCharCount >= 2);
}
     */
}
