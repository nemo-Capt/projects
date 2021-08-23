package qq.qq.manager.players;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StudentPlayer {

    private Player player;
    private boolean isAnswering;
    private List<String> doneQuestions;

    public StudentPlayer(Player player) {
        this.player = player;
        this.isAnswering = false;
        this.doneQuestions = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isAnswering() {
        return isAnswering;
    }

    public void setAnswering(boolean answering) {
        isAnswering = answering;
    }

    public List<String> getDoneQuestions() {
        return doneQuestions;
    }

    public void setDoneQuestions(List<String> doneQuestions) {
        this.doneQuestions = doneQuestions;
    }

}
