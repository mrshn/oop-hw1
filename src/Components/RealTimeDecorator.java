package Components;

public class RealTimeDecorator implements IRealTimeComponent {

    private IRealTimeComponent wrappee;

    public RealTimeDecorator(IRealTimeComponent source) {
        this.wrappee = source;
    }

    @Override
    public void update(float deltaT) {
        wrappee.update(deltaT);
    }
}

