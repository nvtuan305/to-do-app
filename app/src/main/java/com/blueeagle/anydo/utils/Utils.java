package com.blueeagle.anydo.utils;

/**
 * Created by nvtuan on 26/08/2017.
 */

public class Utils {

    private static int mCurrentIdx = 0;

    /**
     * Get the next id for new task
     *
     * @return new id
     */
    public static int getNextTaskId() {
        return mCurrentIdx++;
    }
}
