/*
 * used to restore state of an object to a previous state
 * 
 * Memento contains state of an object to be restored.
 * Originator creates and stores states in Memento objects and
 * Caretaker object is responsible to restore object state from Memento.
 * 
 * Adv:  an easy recovery technique(by create immutable object).
 * Dis: more storage
 * */
public class B_Memento {
	public static void main(String[] args) {
		Article article = new Article(1, "My Article");
		article.setContent("ABC"); // original content
		System.out.println(article);

		ArticleMemento memento = article.createMemento(); // created immutable memento

		article.setContent("123"); // changed content
		System.out.println(article);

		article.restore(memento); // UNDO change
		System.out.println(article);
	}

	static class Article {
		long id;
		String title, content;

		public Article(long id, String title) {
			this.id = id;
			this.title = title;
		}

		public void setContent(String con) {
			this.content = con;
		}

		public ArticleMemento createMemento() {
			ArticleMemento m = new ArticleMemento(id, title, content);
			return m;
		}

		public void restore(ArticleMemento m) {
			this.id = m.getId();
			this.title = m.getTitle();
			this.content = m.getContent();
		}

		@Override
		public String toString() {
			return "ID: " + id + ", TITLE: " + title + ", CONTENT: " + content;
		}
	}

	static class ArticleMemento {
		long id;
		String title, content;

		public ArticleMemento(long id, String title, String content) {
			this.id = id;
			this.title = title;
			this.content = content;
		}

		long getId() {
			return id;
		}

		String getTitle() {
			return title;
		}

		String getContent() {
			return content;
		}
	}
}
