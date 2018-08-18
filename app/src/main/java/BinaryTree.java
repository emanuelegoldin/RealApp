import java.util.ArrayList;

public class BinaryTree {
    BinaryTree(ArrayList<Match> matches) {
        int fasi = matches.size() == 8 ? 4 : 3;
        ArrayList<Match> currMatches = matches;
        for(int k = 0; k < fasi; k++) {
            ArrayList<Match> tmp = new ArrayList<>();
            for (int i = 0; i < currMatches.size(); i += 2) {
                Match newSon = new Match();
                currMatches.get(i).son = newSon;
                currMatches.get(i + 1).son = newSon;
                newSon.firstParent = currMatches.get(i);
                newSon.secondParent = currMatches.get(i + 1);
                tmp.add(newSon);
            }
            currMatches = tmp;
        }
    }
}
