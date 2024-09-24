package viewer;

import org.apache.pulsar.client.api.Message;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

abstract public class ConsumerBase extends Thread
{
    final protected BlockingDeque<Message<byte[]>> queue;
    final protected AtomicBoolean stopFlag;

    public ConsumerBase(BlockingDeque<Message<byte[]>> queue,
                        AtomicBoolean stopFlag)
    {
        this.queue = queue;
        this.stopFlag = stopFlag;
    }

    public boolean isStopRequested()
    {
        return !stopFlag.getOpaque();
    }

    public void Stop()
    {
        stopFlag.setOpaque(true);
    }
}