package task3;

public class Human {
    public static final int MIN_POWER = 0;
    public static final int MAX_POWER = 100;
    String name;
    Post post;
    Action action;
    Integer power;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(int power) throws Exception {
        if (power < MIN_POWER)
            throw new Exception("Нельзя иметь отрицательное количество сил");
        if (power > MAX_POWER)
            throw new Exception("Нельзя иметь больше сил, т.к это опасно для здоровья");
        this.power = power;
    }

    public Human(String name, Post post, Integer power) throws Exception {
        this.name = name;
        this.post = post;
        setPower(power);
    }

    public void reduceStrength(int power) throws Exception {
        setPower(getPower() - power);
    }

    public void addStrength(int power) throws Exception {
        setPower(getPower() + power);
    }


    public void doActionWithPeople(Action action, Human human1, Human human2) throws Exception {
        switch (action) {
            case GRABBED:
                if(getPower() >= (human1.getPower() + human2.getPower())/2) {
                    reduceStrength((human1.getPower() + human2.getPower()) / 2);
                } else {
                    throw new Exception("Не получилось схватить людей");
                }

                break;
            case DRAG_OUT:
                if(getPower() >= human1.getPower() + human2.getPower()) {
                    reduceStrength(human1.getPower() + human2.getPower());
                } else{
                    throw new Exception("Не хватает сил, чтобы вынести людей");
                }
                break;
        }
    }

    public void doAction(Action action) throws Exception {
        switch (action) {
            case BOW:
                reduceStrength(5);
                break;
            case SWING:
                addStrength(10);
                break;
        }
    }
    public void doActionWithDoor(Action action, Door door) throws Exception {
        switch (action) {
            case CLOSE:
                if(door.close)
                    throw new Exception("Door already closed");
                if(door.material == Material.STEEL){
                    reduceStrength(3);
                } else {
                    reduceStrength(1);
                }
                break;
            case OPEN:
                if(!door.close)
                    throw new Exception("Door already opened");
                if(door.material == Material.STEEL){
                    reduceStrength(3);
                } else {
                    reduceStrength(1);
                }
                break;
        }
    }

    public String read(Room room) throws Exception {
        if (getPost()!=Post.CAPTAIN)
            throw new Exception("Только капитан может листать записную книжку");
        if (room.getHumans().size() > 1)
            throw new Exception("Невозможно полистать свою записную книжку");
        return "Задумчиво мурлыкает что-то и листает свою записную книжку со стихами";
    }



}
