package apache;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
class ApachePoolTestOne
{
    private static class PersonPoolFactory extends BasePooledObjectFactory<Person>
    {
        @Override
        public Person create() throws Exception {
            return new Person();
        }

        @Override
        public PooledObject<Person> wrap(Person obj) {
            return new DefaultPooledObject<>(obj);
        }
    }

    private static class Person {
        // Some impl
    }

    public static void test() throws Exception
    {
        try (ObjectPool<Person> pool = new GenericObjectPool<>(new PersonPoolFactory()))
        {
            System.out.println("_____________?____" + pool.toString());

            Person person = pool.borrowObject();
            if(person !=null) {
                System.out.println(person.toString());
            }

            System.out.println("getNumActive: " + pool.getNumActive());
            System.out.println("getNumIdle  : " + pool.getNumIdle());

            Person person1 = pool.borrowObject();
            if (person1 !=null) {
                System.out.println(person1.toString());
            }

            System.out.println("\ngetNumActive: " + pool.getNumActive());
            System.out.println("getNumIdle  : " + pool.getNumIdle());
        }
    }
}

/*
class ApachePoolTestTwo {

    public static void main(String[] args) throws Exception {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);//  w   w   w.  d    e  m  o  2s  .   c  o   m
        poolConfig.setMinIdle(1);
        poolConfig.setTestOnBorrow(true);

        ObjectPool<Connect> objectPool = new ConnectPool(new ConnectFactory("172.161.1.1", 9091), poolConfig);

        List<Connect> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Connect connect = objectPool.borrowObject();
            System.out.println(connect.toString());
            if (i % 2 == 0) {

                objectPool.returnObject(connect);
            } else {
                list.add(connect);
            }
        }

        Random rnd = new Random();
        while (true) {
            System.out.println(
                    String.format("active:%d, idel:%d", objectPool.getNumActive(), objectPool.getNumIdle()));
            Thread.sleep(5000);

            if (list.size() > 0) {
                int i = rnd.nextInt(list.size());
                objectPool.returnObject(list.get(i));
                list.remove(i);
            }

            if (objectPool.getNumActive() <= 0) {
                break;
            }
        }

    }
}
*/

public class ApachePool
{
    public static void main(String[] args) throws Exception
    {
        new ApachePoolTestOne().test();
    }
}