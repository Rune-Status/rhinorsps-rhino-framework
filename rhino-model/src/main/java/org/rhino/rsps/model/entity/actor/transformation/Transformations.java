package org.rhino.rsps.model.entity.actor.transformation;

import java.util.Arrays;
import java.util.Queue;

public interface Transformations {

    /**
     * Adds a new transformation
     *
     * @param transformation
     */
    void add(Transformation transformation);

    /**
     * @param transformation
     * @return
     */
    boolean accepts(Transformation transformation);

    /**
     * @return
     */
    Queue<Transformation> get();

    /**
     * @param transformations
     */
    default void add(Transformation... transformations) {
        Arrays.stream(transformations).forEach(this::add);
    }

}
