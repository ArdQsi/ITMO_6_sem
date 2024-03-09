package task3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HumanTest {

    static Human securityGuard;
    static Human securityGuard2;
    static Human thief1;
    static Human thief2;
    static Human thief3;
    static Human captain;

    @BeforeEach
    public void createAll() throws Exception {
        securityGuard = new Human("Vova", Post.SECURITY_GUARD, 96);
        securityGuard2 = new Human("Petya", Post.SECURITY_GUARD, 3);
        thief1 = new Human("Kolya", Post.THIEF, 25);
        thief2 = new Human("Ilya", Post.THIEF, 40);
        thief3 = new Human("Sasha", Post.THIEF, 80);
        captain = new Human("Gena", Post.CAPTAIN, 60);
    }

    @Test
    void checkPossibleToGrab(){
        assertDoesNotThrow(() -> securityGuard.doActionWithPeople(Action.GRABBED, thief1, thief2));
    }

    @Test
    void checkImpossibleToGrab(){
        assertThrows(Exception.class, () -> securityGuard2.doActionWithPeople(Action.GRABBED, thief2, thief3));
    }

    @Test
    void checkPossibleToDrag(){
        assertDoesNotThrow(() -> securityGuard.doActionWithPeople(Action.DRAG_OUT, thief1, thief2));
    }

    @Test
    void checkImpossibleToDrag(){
        assertThrows(Exception.class, () -> securityGuard.doActionWithPeople(Action.DRAG_OUT, thief2, thief3));
    }

    @Test
    void checkMinPower(){
        Throwable exception = assertThrows(Exception.class, () -> securityGuard2.doAction(Action.BOW));
        assertEquals("Нельзя иметь отрицательное количество сил", exception.getMessage());
    }

    @Test
    void checkMaxPower(){
        Throwable exception = assertThrows(Exception.class, () -> securityGuard.doAction(Action.SWING));
        assertEquals("Нельзя иметь больше сил, т.к это опасно для здоровья", exception.getMessage());
    }

    @Test
    void checkNormalPower() throws Exception {
        securityGuard.doAction(Action.BOW);
        securityGuard.doAction(Action.BOW);
        securityGuard.doAction(Action.BOW);
        securityGuard.doAction(Action.BOW);
        securityGuard.doAction(Action.SWING);
        assertEquals(86, securityGuard.getPower());
    }

    @Test
    void closeTheOpenedDoor() throws Exception {
        Door door = new Door(Material.STEEL, false);
        assertDoesNotThrow(()->securityGuard.doActionWithDoor(Action.CLOSE, door));
    }
    @Test
    void closeTheClosedDoor() throws Exception {
        Door door = new Door(Material.STEEL, true);
        assertThrows(Exception.class, ()->securityGuard.doActionWithDoor(Action.CLOSE, door));
    }

    @Test
    void creation(){
        assertThrows(Exception.class, () -> new Human("Olya", Post.CAPTAIN, -6));
        assertDoesNotThrow(()-> new Human("Olya", Post.CAPTAIN, 50));
        assertThrows(Exception.class, () -> new Human("Olya", Post.CAPTAIN, 110));
    }
}
