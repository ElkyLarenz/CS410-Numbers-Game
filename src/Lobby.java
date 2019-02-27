import java.util.ArrayList;

class Lobby {
    private ArrayList<Player> players;
    private Boolean notFull;

    Lobby() {
        notFull = true;
    }

    void addPlayer(Player player){
        if(notFull) {
            players.add(player);
            checkFull();
        }
    }

    private void checkFull(){
        notFull = players.size() < 4;
    }

    boolean removePlayer(Player player){
        return players.remove(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    Boolean getState() {
        return notFull;
    }
}
