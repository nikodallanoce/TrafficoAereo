package pkg;

public class EmergencyCallObserver extends Observer<AFlight> {

    private String numToCall;

    public EmergencyCallObserver(String numToCall) {
        this.numToCall = numToCall;
    }

    @Override
    void update(AFlight event) {
        if (isMayDay(event.getState())) {
            new Call(event.getState(), numToCall).makeCall();
        }
    }

    private boolean isMayDay(Transponder state) {
        return state.equals(Transponder.TX_7700);
    }
}
