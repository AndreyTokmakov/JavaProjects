package ObjectOrientedProgramming.SealedClasses.State;

/**
 * Sealed classes can be used to implement a state machine, a computational
 * model that defines the behaviour of a system in response to a series of inputs.
 * In a state machine, each state is represented by a sealed class, and the
 * transitions between states are modelled by methods that return a new state.
 */

public sealed class State permits IdleState, ActiveState, ErrorState {
    public static State transition(String input) {
        // Transition logic
        return new IdleState();
    }

    public static void main(String[] args) {
        final State state = transition("");
        System.out.println(state);
    }
}

final class IdleState extends State {
    // Class definition
}

final class ActiveState extends State {
    // Class definition
}

final class ErrorState extends State {
    // Class definition
}