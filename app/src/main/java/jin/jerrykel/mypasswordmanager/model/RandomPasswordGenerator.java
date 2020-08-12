package jin.jerrykel.mypasswordmanager.model;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class RandomPasswordGenerator {
    private static final String ERROR__CODE ="" ;

    public String generatePassayPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

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

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,upperCaseRule, digitRule);
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
