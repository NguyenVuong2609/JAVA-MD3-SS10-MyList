package Rikkei.academy.BT1_MyList;

import java.util.ArrayList;
import java.util.Arrays;

public class MyList<E> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private Object elements[];

    public MyList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyList(int capacity) {
        elements = new Object[capacity];
    }

    private void ensureCapa() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    public void add(E e) {
        if (size == elements.length) {
            ensureCapa();
        }
        elements[size++] = e;
    }

    public E get(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        return (E) elements[i];
    }

    public int size() {
        return size;
    }

    public Object clone() {
        try {
            MyList<E> v = (MyList<E>) super.clone();
            v.elements = Arrays.copyOf(elements, size);
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();
    }

    public E remove(int index) {
        rangeCheck(index);

        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index,
                    numMoved);
        elements[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    E elementData(int index) {
        return (E) elements[index];
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    public void clear() {
        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elements[i] = null;

        size = 0;
    }

}
