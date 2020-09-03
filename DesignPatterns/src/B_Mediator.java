import java.util.HashMap;
import java.util.Map;

/*
 * reduce communication complexity between multiple object/classes
 * 
 * In a chat application we can have several participants. 
 * It’s not a good idea to connect each participant to all the others because 
 * the number of connections would be really high. The best solution is to have a 
 * hub where all participants will connect; this hub is just the mediator class.
 * 
 * Adv:
 * easy maintenance and loose coupling
 * 
 * Good at implementing one to many/ many to many solutions
 * */
public class B_Mediator {
	public static void main(String[] args) {
		IChatRoom chatroom = new ChatRoom();

		User user1 = new ChatUser(chatroom, "1", "Alex");
		User user2 = new ChatUser(chatroom, "2", "Brian");
		User user3 = new ChatUser(chatroom, "3", "Charles");
		User user4 = new ChatUser(chatroom, "4", "David");

		chatroom.addUser(user1);
		chatroom.addUser(user2);
		chatroom.addUser(user3);
		chatroom.addUser(user4);

		user1.send("Hello brian", "2");
		user2.send("Hey buddy", "1");
	}

	interface IChatRoom {
		void sendMessage(String msg, String userId);

		void addUser(User user);
	}

	static class ChatRoom implements IChatRoom {
		Map<String, User> userMap = new HashMap<>();

		@Override
		public void sendMessage(String msg, String userId) {
			User u = userMap.get(userId);
			u.receive(msg);
		}

		@Override
		public void addUser(User user) {
			userMap.put(user.getId(), user);
		}

	}

	static abstract class User {
		IChatRoom mediator;

		String id, name;

		User(IChatRoom room, String id, String name) {
			this.mediator = room;
			this.id = id;
			this.name = name;
		}

		abstract void send(String msg, String userId);

		abstract void receive(String msg);

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

	static class ChatUser extends User {

		ChatUser(IChatRoom room, String id, String name) {
			super(room, id, name);
		}

		@Override
		void send(String msg, String userId) {
			System.out.println(this.getName() + " :: Sending Message : " + msg);
			getMediator().sendMessage(msg, userId);
		}

		@Override
		void receive(String msg) {
			System.out.println(this.getName() + " :: Received Message : " + msg);
		}

	}

}
