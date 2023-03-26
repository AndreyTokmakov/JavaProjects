package Iterator;

class Topic
{
    private String name;

    public Topic(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface Iterator<T>  {
    void reset(); // reset to the first element

    T next(); // To get the next element

    T current();  // To retrieve the current element

    boolean hasNext();  // To check whether there is any next element or not.
}

interface List<T> {
    Iterator<T> iterator();
}

class TopicIterator implements Iterator<Topic> {
    private final Topic[] topics;
    private int position = 0;

    public TopicIterator(Topic[] topics) {
        this.topics = topics;
    }

    @Override
    public void reset() {
        position = 0;
    }

    @Override
    public Topic next() {
        return topics[position++];
    }

    @Override
    public Topic current() {
        return topics[position];
    }

    @Override
    public boolean hasNext() {
        return position < topics.length;
    }
}


class TopicList implements List<Topic>
{
    private final Topic[] topics;

    public TopicList(Topic[] topics)  {
        this.topics = topics;
    }

    @Override
    public Iterator<Topic> iterator() {
        return new TopicIterator(topics);
    }
}

public class IteratorBasic {
    public static void main(String[] args) {
        Topic[] topics = new Topic[5];
        topics[0] = new Topic("topic1");
        topics[1] = new Topic("topic2");
        topics[2] = new Topic("topic3");
        topics[3] = new Topic("topic4");
        topics[4] = new Topic("topic5");

        List<Topic> list = new TopicList(topics);

        Iterator<Topic> iterator = list.iterator();
        while (iterator.hasNext()) {
            Topic currentTopic = iterator.next();
            System.out.println(currentTopic.getName());
        }
    }
}
