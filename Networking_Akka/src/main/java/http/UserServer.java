package http;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.model.StatusCodes;
import akka.pattern.PatternsCS;
import akka.stream.StreamRefMessages;
import akka.util.Timeout;
import scala.concurrent.duration.Duration;
import static akka.http.javadsl.server.PathMatchers.*;

class UserServer extends HttpApp
{

    private final StreamRefMessages.ActorRef userActor;

    Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));

    UserServer(ActorRef userActor) {
      this.userActor = userActor;
    }

    @Override
    public Route routes() {
        return path("users", this::postUser)
                .orElse(path(segment("users").slash(longSegment()), id ->
                        route(getUser(id))));
    }

    private Route getUser(Long id) {
        return get(() -> {
            CompletionStage<Optional<User>> user = PatternsCS.ask(userActor, new GetUserMessage(id), timeout)
                    .thenApply(obj -> (Optional<User>) obj);

            return onSuccess(() -> user, performed -> {
                if (performed.isPresent())
                  return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                else
                  return complete(StatusCodes.NOT_FOUND);
            });
        });
    }

    private Route postUser()
    {
        return route(post(() -> entity(Jackson.unmarshaller(User.class), user -> {
            CompletionStage<UserMessages.ActionPerformed> userCreated = PatternsCS.ask(userActor, new CreateUserMessage(user), timeout)
                    .thenApply(obj -> (ActionPerformed) obj);

            return onSuccess(() -> userCreated, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }

    public static void main(String[] args) throws Exception
    {
        ActorSystem system = ActorSystem.create("userServer");
        ActorRef userActor = system.actorOf(UserActor.props(), "userActor");
        UserServer server = new UserServer(userActor);
        server.startServer("localhost", 8080, system);
    }
}
