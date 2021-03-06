package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {
    
    private final List<Integer> elements;
    private int index;

    public CircularListImpl() {
        this.elements = new ArrayList<>();
        this.index = -1;
    }

    @Override
    public void add(int element) {
        this.elements.add(element);
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if (isEmpty())
            return Optional.empty();

        this.index++;

        if (this.index == size())
            this.index = 0;

        return Optional.of(elements.get(this.index));
    }

    @Override
    public Optional<Integer> previous() {
        return Optional.empty();
    }

    @Override
    public void reset() {
        this.index=0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }
}
