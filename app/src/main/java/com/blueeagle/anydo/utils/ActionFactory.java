package com.blueeagle.anydo.utils;

import com.blueeagle.anydo.models.Action;
import com.blueeagle.anydo.models.AddAction;
import com.blueeagle.anydo.models.DeleteAndToggleAction;

/**
 * Created by nvtuan on 26/08/2017.
 */

public class ActionFactory {

    public static Action createAddAction(String text) {
        return new AddAction(text);
    }

    public static Action createDeleteAction(int idx) {
        Action action = new DeleteAndToggleAction(idx);
        action.setType(Action.ActionType.DELETE_TASK);
        return action;
    }

    public static Action createToggleAction(int idx) {
        return new DeleteAndToggleAction(idx);
    }
}
