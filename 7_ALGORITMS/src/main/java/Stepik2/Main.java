package Stepik2;

import java.util.logging.Logger;

public class Main {

    // Interface: An entity that can be sent by mail.
    // From such an entity, you can get a letter from whom and to whom.
    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    // An abstract class that allows you to abstract the storage logic
    // the source and recipient of the message in the corresponding fields of the class.
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from))
                return false;
            if (!to.equals(that.to))
                return false;
            return true;
        }
    }

    // A letter that has a text that can be obtained using the `GetMessage' method
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            if (!super.equals(o))
                return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null)
                return false;

            return true;
        }
    }

    // A package whose contents can be obtained using the 'get Content' method
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            if (!super.equals(o))
                return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content))
                return false;
            return true;
        }
    }

    // The class that defines the parcel.
    // The parcel has a text description of the contents and an integer value.
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price)
                return false;
            if (!content.equals(aPackage.content))
                return false;
            return true;
        }
    }

    // An interface that specifies a class that can handle a mail object in some way.
    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    // A class in which the logic of real mail is hidden
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // The code of the real mail sending system is described here.
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {
        private final MailService realMailService = new RealMailService();
        private MailService[] mailServices;

        public UntrustworthyMailWorker(MailService[] services){
            mailServices = services;
        }

        public MailService getRealMailService(){
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail)
        {
            Sendable processed = mail;
            for (int i = 0; i < mailServices.length; i++) {
                processed = mailServices[i].processMail(processed);
            }
            return realMailService.processMail(mail);
        }
    }

    public static class Spy implements MailService
    {
        public static final String AUSTIN_POWERS = "Austin Powers";
        private final Logger LOG;

        public Spy(Logger logger) {
            this.LOG = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (MailMessage.class == mail.getClass()) {
                final MailMessage mailMessage = (MailMessage) mail;
                final String from = mailMessage.getFrom();
                final String to = mailMessage.getTo();

                if (from.equals(AUSTIN_POWERS) || to.equals(AUSTIN_POWERS)) {
                    this.LOG.warning("Detected target mail correspondence: from " + from + " to " + to + " \"" + mailMessage.getMessage() + "\"");
                } else {
                    this.LOG.info("Usual correspondence: from " + from + " to " + to + "");
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {
        private int minPrice = 0;
        private int stolenPrice = 0;

        public Thief(int minPrice){
            this.minPrice = minPrice;
        }

        public int getStolenValue(){
            return stolenPrice;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if(mail.getClass() == MailPackage.class)
            {
                Package pac = ((MailPackage)mail).getContent();
                if(pac.getPrice() >= minPrice){
                    stolenPrice += pac.getPrice();
                    mail = new MailPackage(mail.getFrom(), mail.getTo(),new Package("stones instead of " + pac.getContent(), 0));
                }
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {}
    public static class StolenPackageException extends RuntimeException {}

    public static class Inspector implements MailService
    {
        public static final String WEAPONS = "weapons";
        public static final String BANNED_SUBSTANCE = "banned substance";

        @Override
        public Sendable processMail(Sendable mail) {

            if(mail.getClass() == MailPackage.class) {
                Package pac = ((MailPackage)mail).getContent();
                String content = pac.getContent();
                if(content.indexOf("stones instead of ") == 0) {
                    throw new StolenPackageException();
                } else if(content.equals(WEAPONS) || content.equals(BANNED_SUBSTANCE)){
                    throw new IllegalPackageException();
                }
            }
            return mail;
        }
    }

    public static void main(String[] args) {
        MailService spy = new Spy(Logger.getLogger(Class.class.getName()));
        MailService thief = new Thief(10);
        MailService inspector = new Inspector();
        MailService[] msArray = {spy, thief, inspector};
        MailMessage mail1 = new MailMessage("Romeo", "Juliet", "I love you!");
        MailMessage mail2 = new MailMessage("Austin Powers", "James Bond", "Big secret!");
        MailPackage mail3 = new MailPackage("Romeo", "Juliet", new Package("Flowers", 15));
        MailPackage mail4 = new MailPackage("Romeo", "Juliet", new Package("Flowers", 25));
        MailPackage mail5 = new MailPackage("Austin Powers", "James Bond", new Package("weapons", 5));

        UntrustworthyMailWorker umw = new UntrustworthyMailWorker(msArray);
        try {
            umw.processMail(mail1);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        try {
            umw.processMail(mail2);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        try {
            umw.processMail(mail3);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        try {
            umw.processMail(mail4);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        try {
            umw.processMail(mail5);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }

        System.out.println("Thief have stolen $" + ((Thief)thief).getStolenValue() + "!");
    }
}
