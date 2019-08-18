package poker;

import java.util.ArrayList;
import java.util.List;

public class PlayerPokers {

    List<Poker> pokers = new ArrayList<>();
    int status;

    public PlayerPokers() {
        status = 0;
    }

    public List<Poker> getPokers() {
        return pokers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
