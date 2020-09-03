/*
 * want a hierarchy of objects to modify their behaviour but without modifying their source code
 * 
 * open/closed principle
 * */
public class B_Visitor {
	private static MacConfigurator macConfigurator;
	private static LinuxConfigurator linuxConfigurator;
	private static DLinkRouter dlink;
	private static TPLinkRouter tplink;

	public static void main(String[] args) {
		setUp();
		testDlink();
		testTPLink();
	}

	public static void setUp() {
		macConfigurator = new MacConfigurator();
		linuxConfigurator = new LinuxConfigurator();

		dlink = new DLinkRouter();
		tplink = new TPLinkRouter();
	}

	public static void testDlink() {
		dlink.accept(macConfigurator);
		dlink.accept(linuxConfigurator);
	}

	public static void testTPLink() {
		tplink.accept(macConfigurator);
		tplink.accept(linuxConfigurator);
	}

	interface Router {
		public void sendData(char[] data);

		public void acceptData(char[] data);

		public void accept(RouterVisitor v);
	}

	static class DLinkRouter implements Router {

		@Override
		public void sendData(char[] data) {
		}

		@Override
		public void acceptData(char[] data) {
		}

		@Override
		public void accept(RouterVisitor v) {
			v.visit(this);
		}
	}

	static class TPLinkRouter implements Router {

		@Override
		public void sendData(char[] data) {
		}

		@Override
		public void acceptData(char[] data) {
		}

		@Override
		public void accept(RouterVisitor v) {
			v.visit(this);
		}
	}

	interface RouterVisitor {
		public void visit(DLinkRouter router);

		public void visit(TPLinkRouter router);
	}

	static class LinuxConfigurator implements RouterVisitor {

		@Override
		public void visit(DLinkRouter router) {
			System.out.println("DLinkRouter Configuration for Linux complete !!");
		}

		@Override
		public void visit(TPLinkRouter router) {
			System.out.println("TPLinkRouter Configuration for Linux complete !!");
		}
	}

	static class MacConfigurator implements RouterVisitor {

		@Override
		public void visit(DLinkRouter router) {
			System.out.println("DLinkRouter Configuration for Mac complete !!");
		}

		@Override
		public void visit(TPLinkRouter router) {
			System.out.println("TPLinkRouter Configuration for Mac complete !!");
		}
	}
}
