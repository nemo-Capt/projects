package qq.qq.manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import qq.qq.SignQuestions;
import qq.qq.manager.players.StudentPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class SignManager implements Listener {

    private static final Map<String, String> QUESTIONS_MAP = new HashMap<>();

    public SignManager() {

        QUESTIONS_MAP.put("q1", "1");
        QUESTIONS_MAP.put("q2", "2");
        QUESTIONS_MAP.put("q3", "3");
        QUESTIONS_MAP.put("q4", "4");
    }

    @EventHandler
    private void onSignInteract(PlayerInteractEvent playerInteractEvent) {

        if ((playerInteractEvent.getAction().equals(Action.LEFT_CLICK_BLOCK) || playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK))
                && Objects.requireNonNull(playerInteractEvent.getClickedBlock()).getType().equals(Material.OAK_WALL_SIGN)) {
            StudentPlayer studentPlayer = getStudentPlayer(playerInteractEvent.getPlayer());
            assert studentPlayer != null;
            if (!studentPlayer.isAnswering() && studentPlayer.getDoneQuestions().size() < 4) {
                List<String> questions = new ArrayList<>(QUESTIONS_MAP.keySet());
                final Random random = new Random();
                String question = questions.get(random.nextInt(questions.size()));
                while (studentPlayer.getDoneQuestions().contains(question)) {
                    question = questions.get(random.nextInt(questions.size()));
                }
                Location signLocation = playerInteractEvent.getClickedBlock().getLocation();
                String signLocationString = String.valueOf(signLocation.getBlockX())
                        + signLocation.getBlockY()
                        + signLocation.getBlockZ();
                if ("1872-207".equals(signLocationString)//TODO подставить правильные координаты
                        || "1872-205".equals(signLocationString)
                        || "1872-206".equals(signLocationString)
                        || "1872-208".equals(signLocationString)) {
                    studentPlayer.setAnswering(true);
                    studentPlayer.getDoneQuestions().add(question);
                }
            }

            if (studentPlayer.isAnswering()) {
                studentPlayer.getPlayer().sendMessage(studentPlayer.getDoneQuestions().get(studentPlayer.getDoneQuestions().size() - 1));
            }

        }
    }

    //если чел выходит с испытанеия тогда нужно ему setAnswering false и удалить последнее значение из getDoneQuestions
    @EventHandler
    private void onPlayerAnswer(AsyncPlayerChatEvent asyncPlayerChatEvent) {
        StudentPlayer studentPlayer = getStudentPlayer(asyncPlayerChatEvent.getPlayer());
        assert studentPlayer != null;
        if (studentPlayer.isAnswering()) {
            final String answer = asyncPlayerChatEvent.getMessage();
            final String question = studentPlayer.getDoneQuestions().get(studentPlayer.getDoneQuestions().size() - 1);
            if (QUESTIONS_MAP.get(question).equalsIgnoreCase(answer)) {
                studentPlayer.getPlayer().sendMessage("Congrats");
                studentPlayer.setAnswering(false);
                asyncPlayerChatEvent.setCancelled(true);
                //TODO тпнуть с испытания и выдать ключ этажа
            } else {
                studentPlayer.getPlayer().sendMessage("Wrong");
                asyncPlayerChatEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent playerJoinEvent) {
        SignQuestions.studentPlayers.add(new StudentPlayer(playerJoinEvent.getPlayer()));
    }

    private static StudentPlayer getStudentPlayer(Player player) {

        for (StudentPlayer studentPlayer : SignQuestions.studentPlayers) {
            if (studentPlayer.getPlayer() == player) {
                return studentPlayer;
            }
        }
        return null;
    }
}
