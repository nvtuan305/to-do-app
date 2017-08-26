package com.blueeagle.anydo.models;

/**
 * Created by nvtuan on 26/08/2017.
 */

public class AddAction extends Action {

    private String mContent;

    public AddAction(String mContent) {
        this.mType = ActionType.ADD_NEW_TASK;
        this.mContent = mContent;
    }

    public String getContent() {
        return mContent;
    }
}
