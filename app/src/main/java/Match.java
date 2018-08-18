public class Match {
    public Match firstParent;
    public Match secondParent;
    public Match son;
    private String firstTeam;
    private String secondTeam;
    private Integer result[] = new Integer[2];

    Match(){
        this.firstParent = null;
        this.secondParent = null;
        this.son = null;
        this.firstTeam = null;
        this.secondTeam = null;
        this.result[0] = -1;
        this.result[1] = -1;
    }

    Match(String firstTeam, String secondTeam){
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.result[0] = -1;
        this.result[1] = -1;
    }

    Match(String team, int pos){
        if(pos == 1)
            this.firstTeam = team;
        else if (pos == 2)
            this.secondTeam = team;
        this.result[0] = -1;
        this.result[1] = -1;
    }

    public String getWinner(){
        if(result[0] > result[1])
            return firstTeam;
        else if(result[0] < result[1])
            return secondTeam;
        else
            return "Draw";
    }

    public boolean played(){
        return result[0] != -1 && result[1] != -1;
    }

    public void setResult(Integer[] result) {
        this.result = result;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public Integer[] getResult() {
        return result;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }
}
