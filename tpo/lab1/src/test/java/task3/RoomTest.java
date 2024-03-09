package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    static Room room;
    static Human securityGuard;
    static Human thief1;
    static Human thief2;
    static Human captain;
    @BeforeEach
    void create() throws Exception {
        room = new Room();
        securityGuard = new Human("Vova", Post.SECURITY_GUARD, 96);
        thief1 = new Human("Kolya", Post.THIEF, 25);
        thief2 = new Human("Ilya", Post.THIEF, 40);
        captain = new Human("Gena", Post.CAPTAIN, 60);
    }

    @Test
    void checkAddHumanToRoom() throws Exception {
        room.addHumanToRoom(captain);
        room.addHumanToRoom(securityGuard);
        room.addHumanToRoom(thief1);
        assertEquals(3, room.getHumans().size());
    }

    @Test
    void checkAddExistHumanToRoom() throws Exception {
        room.addHumanToRoom(captain);
        assertThrows(Exception.class, () -> room.addHumanToRoom(captain));
    }

    @Test
    void checkDelHumanFromRoom() throws Exception {
        room.addHumanToRoom(captain);
        room.addHumanToRoom(securityGuard);
        room.delHumanFromRoom(captain);
        assertEquals(1, room.getHumans().size());
    }

    @Test
    void checkDelHumanFromFreeRoom() throws Exception {
        assertThrows(Exception.class, () ->room.delHumanFromRoom(captain));
    }

    @Test
    void checkImpossibleReadForNotCaptain() throws Exception {
        room.addHumanToRoom(securityGuard);
        assertThrows(Exception.class, () -> securityGuard.read(room));
    }

    @Test
    void checkPossibleReadForCaptain() throws Exception {
        room.addHumanToRoom(captain);
        assertDoesNotThrow(() -> captain.read(room));
    }

    @Test
    void checkImpossibleReadForCaptainWithPeopleInRoom() throws Exception {
        room.addHumanToRoom(securityGuard);
        room.addHumanToRoom(thief1);
        assertThrows(Exception.class, () -> captain.read(room));
    }
}
