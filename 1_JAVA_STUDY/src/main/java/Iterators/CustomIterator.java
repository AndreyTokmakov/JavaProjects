package Iterators;

import java.util.Iterator;

public class CustomIterator
{
    static class Demo implements Iterable<String> {
        // Simple demo: use array instead of inventing new data structure
        String[] data = {"One", "Two", "Three"};

        /** This is the Iterator that makes it all happen */
        class DemoIterator implements Iterator<String> {
            int i = 0;


            /**
             * Tell if there are any more elements.
             * @return true if next() will succeed, false otherwise
             */
            @Override
            public boolean hasNext() {
                return i < data.length;
            }


            /** @return the next element from the data */
            @Override
            public String next() {
                return data[i++];
            }

            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        }

        /** Method by which the Demo class makes its iterator available */
        @Override
        public Iterator<String> iterator() {
            return new DemoIterator();
        }
    }


    public static void main(String[] args) {
        Demo demo = new Demo();
        for (String s : demo) {
            System.out.println(s);
        }
    }
}
