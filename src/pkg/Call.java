package pkg;

public class Call {

    private Transponder flightState;
    private String telNumber;

    public Call(Transponder flightState, String telNumber) {
        this.flightState = flightState;
        this.telNumber = telNumber;
    }

    public void makeCall() {
        System.out.println(String.format("Emergency call: %s with state %s", telNumber, flightState));
    }
}
