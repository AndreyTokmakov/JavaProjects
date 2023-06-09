package Mediator;


import java.util.HashMap;
import java.util.Map;

abstract class User  {
    private final IChatRoom mediator;
    private final String id;
    private final String name;

    public User(IChatRoom room, String id, String name){
        this.mediator = room;
        this.name = name;
        this.id = id;
    }

    public abstract void send(String msg, String userId);
    public abstract void receive(String msg);

    public IChatRoom getMediator() {
        return mediator;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

interface IChatRoom {
    void sendMessage(String msg, String userId);

    void addUser(User user);
}


class ChatRoom implements IChatRoom {
    private final Map<String, User> usersMap = new HashMap<String, User>();

    @Override
    public void sendMessage(String msg, String userId) {
        this.usersMap.get(userId).receive(msg);
    }

    @Override
    public void addUser(User user) {
        this.usersMap.put(user.getId(), user);
    }
}

class ChatUser extends User {
    public ChatUser(IChatRoom room, String id, String name) {
        super(room, id, name);
    }

    @Override
    public void send(String msg, String userId) {
        System.out.println(this.getName() + " :: Sending Message : " + msg);
        getMediator().sendMessage(msg, userId);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.getName() + " :: Received Message : " + msg);
    }
}

public class ChatMediator
{
    public static void main(String[] args)
    {
        IChatRoom chatroom = new ChatRoom();

        User user1 = new ChatUser(chatroom,"1", "Alex");
        User user2 = new ChatUser(chatroom,"2", "Brian");
        User user3 = new ChatUser(chatroom,"3", "Charles");
        User user4 = new ChatUser(chatroom,"4", "David");

        chatroom.addUser(user1);
        chatroom.addUser(user2);
        chatroom.addUser(user3);
        chatroom.addUser(user4);

        user1.send("Hello brian", "2");
        user2.send("Hey buddy", "1");
    }
}
