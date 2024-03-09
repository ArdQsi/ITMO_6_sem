package task3;

import java.util.HashSet;
import java.util.Set;

public class Room {

    private final Set<Human> humans = new HashSet<>();
    public void addHumanToRoom(Human human) throws Exception {
        if (this.humans.contains(human)) throw new Exception("Этот человек уже в комнате");
        this.humans.add(human);
    }
    public void delHumanFromRoom(Human human) throws Exception {
        if (!this.humans.contains(human)) throw new Exception("Этого человека нет в комнате");
        this.humans.remove(human);
    }

    public Set<Human> getHumans() {
        return humans;
    }
}
