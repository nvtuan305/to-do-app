package com.blueeagle.anydo;

import com.blueeagle.anydo.models.ImmutableTask;
import com.blueeagle.anydo.models.Task;
import com.blueeagle.anydo.utils.Utils;

import org.immutables.value.Value;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by nvtuan on 26/08/2017.
 */

@Value.Immutable
public abstract class State {

    public abstract ArrayList<Task> tasks();

    public static State getDefaultState() {
        // Init two default tasks
        Task mDefTask = ImmutableTask.builder()
                .id(Utils.getNextTaskId())
                .content("This is a task")
                .isCompleted(false)
                .build();

        return ImmutableState.builder()
                .tasks(new ArrayList<>(Collections.singletonList(mDefTask)))
                .build();
    }
}
