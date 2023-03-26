package Command;


import java.util.ArrayList;
import java.util.List;

interface ICommand {
    void execute();
}

class DomesticEngineer implements ICommand {
    public void execute() {
        System.out.println("take out the trash");
    }
}

class Politician implements ICommand {
    public void execute() {
        System.out.println("take money from the rich, take votes from the poor");
    }
}

class Programmer implements ICommand {
    public void execute() {
        System.out.println("sell the bugs, charge extra for the fixes");
    }
}

public class Command_ToSimple
{
    public static List<ICommand> produceRequests() {
        List<ICommand> queue = new ArrayList<>();
        queue.add(new DomesticEngineer());
        queue.add(new Politician());
        queue.add(new Programmer());
        return queue;
    }

    public static void workOffRequests(List<ICommand> queue) {
        for (final ICommand command : queue) {
            command.execute();
        }
    }

    public static void main( String[] args ) {
        List<ICommand> queue = produceRequests();
        workOffRequests(queue);
    }
}
