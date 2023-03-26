package Stepik3;

import java.util.*;
import java.util.function.*;


public class Main
{
    private static class Pair<T> {
        public final String first;
        public final T second;

        private Pair(String first, T second) {
            this.first = first;
            this.second = second;
        }
    }

    public static interface Handlable<T> {
        public Pair<T> getData();
    }

    public static final class MailMessage implements Handlable<String>
    {
        private final String from;
        private final String to;
        private final String content;

        public MailMessage(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getContent() {
            return content;
        }

        @Override
        public Pair<String> getData() {
            return new Pair<String>(to, content);
        }
    }

    public static class Salary implements Handlable<Integer> {
        private final String from;
        private final String to;
        private final Integer salary;

        public Salary(String from, String to, Integer salary) {
            this.from = from;
            this.to = to;
            this.salary = salary;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public Integer getSalary() {
            return salary;
        }

        @Override
        public Pair<Integer> getData() {
            return new Pair<Integer>(to, salary);
        }
    }

    public static final class MyMap<K, V> extends HashMap<K, List<V>> {

        @Override
        public List<V> get(Object key) {
            final List<V> result = super.get(key);
            if (null == result)
                return Collections.<V>emptyList();
            else return result;
        }
    }

    public static class MailService<T> implements Consumer<Handlable> {
        private final MyMap<String, T> map = new MyMap<String, T>();

        @Override
        public void accept(Handlable t) {
            final Pair<T> data = t.getData();
            final List<T> list = map.get(data.first);
            if (Collections.<T>emptyList() != list)
                list.add(data.second);
            else
                map.put(data.first, new ArrayList<T>(Arrays.asList(data.second)));
        }

        public Map<String, List<T>> getMailBox() {
            return map;
        }
    }

    public static boolean ASSERT(boolean expr, String text) {
        if (!expr) {
            System.err.println(text);
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        // Random variables
        String randomFrom = "123456789";
        String randomTo = "wsasdasdasdasdsdsdsdsdsd";
        int randomSalary = 100;

        // Creating a list of three mail messages.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        ASSERT(firstMessage.getFrom().equals("Robert Howard"), "Wrong firstMessage from address");
        ASSERT(firstMessage.getTo().equals("H.P. Lovecraft"),"Wrong firstMessage to address");
        ASSERT(firstMessage.getContent().endsWith("Howard!"), "Wrong firstMessage content ending");

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Brother, why is everyone praising you so much when I wrote almost all the scripts. That's not fair!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "I still didn't understand Interstellar."
        );

        List<MailMessage> messages = Arrays.asList(firstMessage, secondMessage, thirdMessage);

        // Create mail service
        MailService<String> mailService = new MailService<>();

        // Processing of the list of emails by the mail service
        messages.stream().forEachOrdered(mailService);

        // Receiving and checking the "mailbox" dictionary,
        // where the recipient can get a list of messages that were sent to him
        Map<String, List<String>> mailBox = mailService.getMailBox();

        ASSERT(mailBox.get("H.P. Lovecraft").equals(Arrays.asList(
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!")),
                "wrong mailService mailbox content (1)");
        ASSERT(mailBox.get("Christopher Nolan").equals(Arrays.asList(
                "Brother, why is everyone praising you so much when I wrote almost all the scripts. That's not fair!",
                "I still didn't understand Interstellar.")),
                "wrong mailService mailbox content (2)");
        ASSERT(mailBox.get(randomTo).equals(Collections.<String>emptyList()),
                "wrong mailService mailbox content (3)");

        // Creating a list of three salaries.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        // Creating a mail service that processes salaries.
        MailService<Integer> salaryService = new MailService<>();

        // Processing of the salary list by the mail service
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        // Receiving and checking the "mailbox" dictionary,
        // where the recipient can get a list of salaries that were sent to him.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();

        // assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
        ASSERT(salaries.get(salary1.getTo()).equals(Arrays.asList(1)),"wrong salaries mailbox content (1)");
        // assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
        ASSERT(salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)),"wrong salaries mailbox content (2)");
        // assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";
        ASSERT(salaries.get(randomTo).equals(Arrays.asList(randomSalary)),"wrong salaries mailbox content (3)");

        System.out.println("Done");
    }
}
