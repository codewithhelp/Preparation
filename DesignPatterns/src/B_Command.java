/*
 * is a data driven design pattern
 * A request/action is wrapped under an object as a command and passed to invoker object
 * 
 * Adv:
 * 	-> scalable, as we can add new commands without changing the existing code
 *  -> loose coupling between invoker and receivers
 * Dis:
 * 	-> increases number of classes for each individual command
 * 
 * Kind of callback
 */
public class B_Command {
	public static void main(String[] args) {

		Light livingRoomLight = new Light();
		Fan livingRoomFan = new Fan();

		Light bedRoomLight = new Light();
		Fan bedRoomFan = new Fan();

		HomeAutomateRemote remote = new HomeAutomateRemote();

		remote.setCommand(new TurnOffLightCommand(livingRoomLight));
		remote.buttonPressed();

		remote.setCommand(new TurnOnLightCommand(livingRoomLight));
		remote.buttonPressed();

		remote.setCommand(new StartFanCommand(livingRoomFan));
		remote.buttonPressed();

		remote.setCommand(new StopFanCommand(livingRoomFan));
		remote.buttonPressed();

		remote.setCommand(new TurnOffLightCommand(bedRoomLight));
		remote.buttonPressed();

		remote.setCommand(new TurnOnLightCommand(bedRoomLight));
		remote.buttonPressed();

		remote.setCommand(new StartFanCommand(bedRoomFan));
		remote.buttonPressed();

		remote.setCommand(new StopFanCommand(bedRoomFan));
		remote.buttonPressed();

	}

	interface Command {
		void execute();
	}

	static class Light {
		void turnOn() {
			System.out.println("Light TurnOn");
		}

		void turnOff() {
			System.out.println("Light TurnOff");
		}
	}

	static class Fan {
		void start() {
			System.out.println("Fan Start");
		}

		void stop() {
			System.out.println("Fan Stop");
		}
	}

	static class TurnOffLightCommand implements Command {
		Light light;

		TurnOffLightCommand(Light light) {
			this.light = light;
		}

		@Override
		public void execute() {
			System.out.println("Turning off light");
			light.turnOff();
		}

	}

	static class TurnOnLightCommand implements Command {
		Light light;

		TurnOnLightCommand(Light light) {
			this.light = light;
		}

		@Override
		public void execute() {
			System.out.println("Turning on light");
			light.turnOn();
		}

	}

	static class StartFanCommand implements Command {
		Fan fan;

		StartFanCommand(Fan fan) {
			this.fan = fan;
		}

		@Override
		public void execute() {
			System.out.println("Start fan");
			fan.start();
		}

	}

	static class StopFanCommand implements Command {
		Fan fan;

		StopFanCommand(Fan fan) {
			this.fan = fan;
		}

		@Override
		public void execute() {
			System.out.println("Stop fan");
			fan.stop();
		}

	}

	static class HomeAutomateRemote {
		Command command;

		void setCommand(Command command) {
			this.command = command;
		}

		void buttonPressed() {
			command.execute();
		}
	}

}
