package com.project.jvm.concurrent.chaptor14;

public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V>{
    GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (isFull()) {
            throw new BufferFullException();
        }
        doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        return doTake();
    }


}
class BufferFullException extends Throwable{

}
class BufferEmptyException extends Throwable{

}