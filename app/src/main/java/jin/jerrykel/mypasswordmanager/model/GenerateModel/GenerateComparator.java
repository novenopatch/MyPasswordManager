package jin.jerrykel.mypasswordmanager.model.GenerateModel;

import java.util.Comparator;

/**
 * Created by JerrykelDEV on 14/10/2020 16:28
 */
public class GenerateComparator implements Comparator<GeneratePassword> {

    @Override
    public int compare(GeneratePassword o1, GeneratePassword o2) {
        return  o2.getIdGeneratePassword() - o1.getIdGeneratePassword();
    }
}
