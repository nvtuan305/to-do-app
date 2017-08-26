package com.blueeagle.anydo.utils;

import com.blueeagle.anydo.models.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by nvtuan on 26/08/2017.
 */

public class SortUtils {

    /**
     * Sort task list by date created and completion state
     *
     * @param tasks Task list
     * @return Sorted task list
     */
    public static ArrayList<Task> sortByState(ArrayList<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                if (task1.id() < task2.id()) {
                    if (!task1.isCompleted() && task2.isCompleted()) // N - Y
                        return -1;
                    else if (task1.isCompleted() && !task2.isCompleted()) // Y - N
                        return 1;

                    return -1;

                } else if (task1.id() > task2.id()) { // 5 - 2
                    if (!task1.isCompleted() && task2.isCompleted()) // N - Y
                        return -1;
                    return 1;
                }

                return 0;
            }
        });

        return tasks;
    }

}
