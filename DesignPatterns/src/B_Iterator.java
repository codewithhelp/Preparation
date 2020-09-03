/*
 * get a way to access the elements of a collection object in a sequential manner, without exposing
 * underlying implementation
 * */
public class B_Iterator {
	public static void main(String[] args) {
		Topic[] topics = new Topic[5];
		topics[0] = new Topic("topic1");
		topics[1] = new Topic("topic2");
		topics[2] = new Topic("topic3");
		topics[3] = new Topic("topic4");
		topics[4] = new Topic("topic5");

		List<Topic> list = new TopicList(topics);
		Iterator<Topic> iterator = list.iterator();
		while (iterator.hasNext()) {
			Topic current = iterator.next();
			System.out.println(current.getName());
		}

	}

	static class Topic {
		String name;

		Topic(String name) {
			this.name = name;
		}

		String getName() {
			return this.name;
		}

		void setName(String name) {
			this.name = name;
		}
	}

	interface Iterator<E> {
		void reset();

		E next();

		E currentItem();

		boolean hasNext();
	}

	static class TopicIterator implements Iterator<Topic> {

		Topic[] topics;
		int position;

		TopicIterator(Topic[] topics) {
			this.topics = topics;
		}

		@Override
		public void reset() {
			position = 0;
		}

		@Override
		public Topic next() {
			return topics[position++];
		}

		@Override
		public Topic currentItem() {
			return topics[position];
		}

		@Override
		public boolean hasNext() {
			return position < topics.length;
		}

	}

	interface List<E> {
		Iterator<E> iterator();
	}

	static class TopicList implements List<Topic> {
		Topic[] topics;

		public TopicList(Topic[] topics) {
			this.topics = topics;
		}

		@Override
		public Iterator<Topic> iterator() {
			return new TopicIterator(topics);
		}

	}
}
