package com.blueeagle.anydo.models;

/**
 * Action class
 * Created by nvtuan on 26/08/2017.
 */

public abstract class Action {

    protected ActionType mType;

    public enum ActionType {
        ADD_NEW_TASK,
        TOGGLE_TASK,
        DELETE_TASK
    }

    public void setType(ActionType mType) {
        this.mType = mType;
    }

    public ActionType getType() {
        return mType;
    }
}
