package com.blueeagle.anydo.models;

/**
 * Created by nvtuan on 25/08/2017.
 */

public class DeleteAndToggleAction extends Action {

    private int mId;

    public DeleteAndToggleAction(int mId) {
        this.mType = ActionType.TOGGLE_TASK;
        this.mId = mId;
    }

    public int getId() {
        return mId;
    }
}
