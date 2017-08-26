package com.blueeagle.anydo.models;

import org.immutables.value.Value;

/**
 * Created by nvtuan on 26/08/2017.
 */

@Value.Immutable
public abstract class Task {

    public abstract int id() ;

    public abstract String content();

    public abstract boolean isCompleted();
}
