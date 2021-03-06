package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {
    
    private final List<Integer> elements;
    private int index;
    private final SelectStrategy eachSelectStrategy=new SelectStrategyFactoryImpl().createEachStrategy();

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
        return next(eachSelectStrategy);
    }

    @Override
    public Optional<Integer> previous() {
        if (isEmpty())
            return Optional.empty();

        this.index--;

        if (this.index <0)
            this.index = size()-1;

        return Optional.of(elements.get(this.index));
    }

    @Override
    public void reset() {
        this.index=-1;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {

        Optional<Integer> element=Optional.empty();

        if (isEmpty())
            return element;

        for (int i=0;i<size();i++)
        {
            this.index++;

            if (this.index == size())
                this.index = 0;

            element = Optional.of(elements.get(this.index));
            if (strategy.apply(element.get()))
                break;
        }

        return element;
    }
}
