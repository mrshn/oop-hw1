package Components;

public class RealTimeDecorator implements IRealTimeComponent {

    private IRealTimeComponent wrapper;

    public RealTimeDecorator(IRealTimeComponent source) {
        this.wrapper = source;
    }

    @Override
    public void update(float deltaT) {
        wrapper.update(deltaT);
    }
}

