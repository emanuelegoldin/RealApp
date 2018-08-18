import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Tournament {

    private ArrayList<String> teams;
    private Map<Character, ArrayList<String>> groups;
    private BinaryTree finalStage;

    public Tournament(Integer format, ArrayList<String> teams) {
        this.teams = teams;
        Integer index = format;
        int currLetter = 65;
        for (int i = 0; i < format / 4; i++, currLetter++) {
            ArrayList<String> tmp = new ArrayList<>();
            for (int j = 0; j < 4; j++, index--) {
                tmp.add(teams.remove(new Random().nextInt(index)));
            }
            groups.put((char) currLetter, tmp);
        }
        finalStage = new BinaryTree(new ArrayList<Match>(8));
    }

    public void update(){

    }
}
