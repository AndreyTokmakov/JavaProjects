package ObjectsPools;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;


class ObjectFactory extends BasePooledObjectFactory<ObjectFactory.Computer> {

    @Override
    public Computer create() throws Exception {
        return new Computer("mac");
    }

    @Override
    public PooledObject<Computer> wrap(Computer obj) {
        return new DefaultPooledObject<>(obj);
    }

    public static class Computer{
        String name;

        public Computer(String name){
            this.name = name;
        }
    }
}
public class ObjectsPoolsTests
{
    public static void main(String[] args) throws InterruptedException {
        ObjectFactory objectFactory = new ObjectFactory();
        ObjectPool<ObjectFactory.Computer> objectPool = new GenericObjectPool<ObjectFactory.Computer>(objectFactory);


        Thread t1 = new Thread(() -> {
            try {
                ObjectFactory.Computer computer = objectPool.borrowObject();
                System.out.println(Thread.currentThread().getId() + ":" + computer);
                objectPool.returnObject(computer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t1.join();

        new Thread(() -> {
            try {
                ObjectFactory.Computer obj = objectPool.borrowObject();
                System.out.println(Thread.currentThread().getId() + ":" + obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
