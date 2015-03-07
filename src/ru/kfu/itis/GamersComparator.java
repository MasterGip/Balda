package ru.kfu.itis;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Created by mg on 15.02.15.
 */
public class GamersComparator implements Comparator<Gamer> {
    @Override
    public int compare(Gamer o1, Gamer o2) {
        int points1 = o1.getPoints();
        int points2 = o2.getPoints();
        int returning = 0;
        if(points1 > points2){
            returning = 1;
        }else{
            if(points1 < points2){
                returning = -1;
            }
        }
        return returning;
    }

    @Override
    public Comparator<Gamer> reversed() {
        return null;
    }

    @Override
    public Comparator<Gamer> thenComparing(Comparator<? super Gamer> other) {
        return null;
    }

    @Override
    public <U> Comparator<Gamer> thenComparing(Function<? super Gamer, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Gamer> thenComparing(Function<? super Gamer, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Gamer> thenComparingInt(ToIntFunction<? super Gamer> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Gamer> thenComparingLong(ToLongFunction<? super Gamer> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Gamer> thenComparingDouble(ToDoubleFunction<? super Gamer> keyExtractor) {
        return null;
    }
}
