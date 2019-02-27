import java.util.ArrayList;

class Lobby {
    private ArrayList<Player> players;
    private Boolean full;

    Lobby() {
        full = false;
    }

    boolean addPlayer(Player player){
        if(!full) {
            players.add(player);
            checkFull();
            return true;
        }
        else
            return false;
    }

    private void checkFull(){
        full = players.size() < 4;
    }

    boolean removePlayer(Player player){
        return players.remove(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Boolean getState() {
        return full;
    }

    public void setState(Boolean state) {
        this.full = state;
    }
}
