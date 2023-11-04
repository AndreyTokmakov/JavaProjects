package Observer;

import java.util.ArrayList;
import java.util.List;

interface IObserver {
    void update(IObservable subject);
}

interface IObservable {
    IObserver addObserver(IObserver observer);

    IObserver removeObserver(IObserver observer);

    void notifyObservers();
}

abstract class ObservableBase implements IObservable
{
    private final List<IObserver> observersList = new ArrayList<IObserver>();

    @Override
    public IObserver addObserver(IObserver observer) {
        observersList.add(observer);
        return observer;
    }

    @Override
    public IObserver removeObserver(IObserver observer) {
        observersList.remove(observer);
        return observer;
    }

    @Override
    public void notifyObservers() {
        observersList.forEach(o -> o.update(this));
    }
}

class SubjectEx extends ObservableBase
{
    private String state;

    public SubjectEx(String state) {
        this.state = state;
    }

    void setState(String state) {
        this.state = state;
        this.notifyObservers();
    }

    String getState(String state) {
        return this.state;
    }

    @Override
    public String toString() {
        return String.format("SubjectEx(%s)", this.state );
    }
}

class ConcreteObserverOne implements IObserver {
    @Override
    public void update(IObservable subject) {
        System.out.println(this.getClass().getSimpleName() + "::update() called for " + subject);
    }
}

class ConcreteObserverTwo implements IObserver {
    @Override
    public void update(IObservable subject) {
        System.out.println(this.getClass().getSimpleName() + "::update() called for " + subject);
    }
}

public class ObserverBasic {
    public static void main(String[] args)
    {
        SubjectEx subj = new SubjectEx("None");
        subj.addObserver(new ConcreteObserverOne());
        final IObserver o =subj.addObserver(new ConcreteObserverTwo());

        subj.setState("NEW");
        subj.setState("RUNNING");

        subj.removeObserver(o);

        subj.setState("STOPPED");

    }
}
