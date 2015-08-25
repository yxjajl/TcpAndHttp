package com.disruptor;

//import java.util.UUID;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import com.lmax.disruptor.EventHandler;
//import com.lmax.disruptor.RingBuffer;
//import com.lmax.disruptor.dsl.Disruptor;

public class Simple {
//    public static void main(String[] args) {
//        ExecutorService exec = Executors.newCachedThreadPool();
//        // Preallocate RingBuffer with 1024 ValueEvents
//        Disruptor<ValueEvent> disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, 1024, exec);
//        final EventHandler<ValueEvent> handler = new EventHandler<ValueEvent>() {
//            // event will eventually be recycled by the Disruptor after it wraps
//            public void onEvent(final ValueEvent event, final long sequence, final boolean endOfBatch) throws Exception {
//                System.out.println("Sequence: " + sequence+"   ValueEvent: " + event.getValue());
//            }
//        };
//
//        disruptor.handleEventsWith(handler);
//        RingBuffer<ValueEvent> ringBuffer = disruptor.start();
//        for (long i = 10; i < 15; i++) {
//            String uuid =String.valueOf(i)    ;
//            long seq = ringBuffer.next();
//            ValueEvent valueEvent = ringBuffer.get(seq);
//            valueEvent.setValue(uuid);
//            ringBuffer.publish(seq);
//        }
//        disruptor.shutdown();
//        exec.shutdown();
//    }
}
