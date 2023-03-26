package Annotations;

import java.util.Arrays;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME) // Indicates that our Annotation can be used at run time through Reflection (we just need this).
@Target(ElementType.METHOD)         // Indicates that the purpose of our Annotation is a method (not a class, not a variable, not a field, but a method).
@interface Command   {              // Description. Note that interface is @;
	// The command for which the function will be responsible (for example, "hello");
    String name();	

	// Command arguments, will be used to display a list of commands
    String args(); 

	// The minimum number of arguments, immediately assigned 0 (logical)
    int minArgs() default 0; 

	// Description, also for the list
    String desc(); 

	// The maximum number of arguments. Altogether optional, but can also be used
    int maxArgs() default Integer.MAX_VALUE; 

	// Whether to show the command in the list (optional line at all, but you never know, come in handy!)
    boolean showInHelp() default true; 

    // Which commands will be considered equivalent to ours (For example, for "hello", 
    // it can be "Zdarov", "Priv", etc., for each case, to start a function is not rational
    String[] aliases(); 
}


class MessageReceivedEvent {
	public String getMessage() {
		return "SomeMessage";
	}
}

class MessageListener {
    public void onMessageReceived(MessageReceivedEvent event)  {
    	final String message = event.getMessage().toLowerCase(); 
        if (true == message.startsWith("Bot"))
        {
            try {
                String[] args = message.split(" "); 
                String command = args[1].toLowerCase();
                String[] nArgs = Arrays.copyOfRange(args, 2, args.length);
            } catch (ArrayIndexOutOfBoundsException e)  {

            }
        }
    }
}


class CommandListener
{
    @Command(name = "Hello",
             args = "",
             desc = "Be cultural, say hello",
             showInHelp = false,
             aliases = {"здаров"})
    public void hello(String[] args) {
       // Some kind of functionality, at your discretion.
    }

    @Command(name = "Bie",
             args = "",
             desc = "",
             aliases = {"Good luck"})
    public void bie(String[] args) {
       // Some kind of functionality, at your discretion.
    }

    @Command(name = "Help",
             args = "",
             desc = "Displays a list of commands",
             aliases = {"help", "commands"})
    public void help(String[] args)
    {
        StringBuilder sb = new StringBuilder("List of commands: \n");
        for (Method m : this.getClass().getDeclaredMethods())
        {
            if (m.isAnnotationPresent(Command.class))
            {
                Command com = m.getAnnotation(Command.class);
                if (com.showInHelp()) // If you want to show the command in the list.
                {
                    sb.append("Бот, ").append(com.name()).append(" ").append(com.args()).append(" - ").append(com.desc()).append("\n");
                }
            }
        }
    }
}

public class MessageListenerTests {

}
