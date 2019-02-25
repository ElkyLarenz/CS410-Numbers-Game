package network;

import java.util.ArrayList;

class Lobby {
    private ArrayList<Game.Player> players;
    private Boolean full;

    Lobby() {
        full = false;
    }

    boolean addPlayer(Game.Player player){
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

    boolean removePlayer(Game.Player player){
        return players.remove(player);
    }

    public ArrayList<Game.Player> getPlayers() {
        return players;
    }

    public Boolean getState() {
        return full;
    }

    public void setState(Boolean state) {
        this.full = state;
    }
}
