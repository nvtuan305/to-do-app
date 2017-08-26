package com.blueeagle.anydo;

import com.blueeagle.anydo.models.Action;
import com.blueeagle.anydo.models.AddAction;
import com.blueeagle.anydo.models.DeleteAndToggleAction;
import com.blueeagle.anydo.models.ImmutableTask;
import com.blueeagle.anydo.models.Task;
import com.blueeagle.anydo.utils.SortUtils;
import com.blueeagle.anydo.utils.Utils;

import java.util.ArrayList;

/**
 * Created by nvtuan on 26/08/2017.
 */

public class Reducer {

    public State reduce(Action action, State oldState) {
        Action.ActionType actionType = action.getType();
        switch (actionType) {
            case ADD_NEW_TASK:
                return addNewTask(action, oldState);

            case TOGGLE_TASK:
                return toggleTask(action, oldState);

            case DELETE_TASK:
                return deleteTask(action, oldState);

            default:
                return oldState;
        }
    }

    private State addNewTask(Action action, State oldState) {
        String content = ((AddAction) action).getContent();
        Task task = ImmutableTask.builder()
                .id(Utils.getNextTaskId())
                .content(content)
                .isCompleted(false)
                .build();

        ArrayList<Task> newTasks = new ArrayList<>();
        newTasks.addAll(oldState.tasks());
        newTasks.add(task);

        SortUtils.sortByState(newTasks);
        return ImmutableState.builder().tasks(newTasks).build();
    }

    private State toggleTask(Action action, State oldState) {
        int id = ((DeleteAndToggleAction) action).getId();
        ArrayList<Task> newTasks = new ArrayList<>();

        for (Task task : oldState.tasks()) {
            if (task.id() == id) {
                newTasks.add(ImmutableTask.builder()
                        .id(task.id())
                        .content(task.content())
                        .isCompleted(!task.isCompleted())
                        .build());
            } else {
                newTasks.add(task);
            }
        }

        SortUtils.sortByState(newTasks);
        return ImmutableState.builder().tasks(newTasks).build();
    }

    private State deleteTask(Action action, State oldState) {
        int id = ((DeleteAndToggleAction) action).getId();
        ArrayList<Task> newTasks = new ArrayList<>();

        for (Task task : oldState.tasks()) {
            if (task.id() != id) {
                newTasks.add(task);
            }
        }

        SortUtils.sortByState(newTasks);
        return ImmutableState.builder().tasks(newTasks).build();
    }
}
