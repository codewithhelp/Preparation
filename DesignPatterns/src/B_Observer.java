import java.util.ArrayList;
import java.util.List;

/*
 * also known as pub/sub pattern
 * 
 * used when there is one to many relationship between objects, if one object is modified it's dependent
 * objects to be notified automatically
 * 
 * Adv: loose couple 
 */
public class B_Observer {
	public static void main(String[] args) {

		MessageSubOne sub1 = new MessageSubOne();
		MessageSubTwo sub2 = new MessageSubTwo();
		MessageSubThree sub3 = new MessageSubThree();

		MessagePublisher pub = new MessagePublisher();

		pub.attach(sub1);
		pub.attach(sub2);

		pub.notifyUpdate(new Message("Message 1"));

		pub.detach(sub2);
		pub.attach(sub3);

		pub.notifyUpdate(new Message("Message 2"));
	}

	interface Subject {
		void attach(Observer o);

		void detach(Observer o);

		void notifyUpdate(Message m);
	}

	interface Observer {
		public void update(Message m);
	}

	static class Message {
		String message;

		public Message(String message) {
			this.message = message;
		}

		public String getMessage() {
			return this.message;
		}
	}

	static class MessagePublisher implements Subject {

		private List<Observer> observers = new ArrayList<Observer>();

		@Override
		public void attach(Observer o) {
			observers.add(o);
		}

		@Override
		public void detach(Observer o) {
			observers.remove(o);
		}

		@Override
		public void notifyUpdate(Message m) {
			for (Observer o : observers)
				o.update(m);
		}

	}

	static class MessageSubOne implements Observer {

		@Override
		public void update(Message m) {
			System.out.println("Message subscriber one : " + m.getMessage());
		}

	}

	static class MessageSubTwo implements Observer {

		@Override
		public void update(Message m) {
			System.out.println("Message subscriber two : " + m.getMessage());
		}

	}

	static class MessageSubThree implements Observer {

		@Override
		public void update(Message m) {
			System.out.println("Message subscriber three : " + m.getMessage());
		}

	}

}
