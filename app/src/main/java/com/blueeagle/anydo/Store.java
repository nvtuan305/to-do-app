package com.blueeagle.anydo;

import com.blueeagle.anydo.models.Action;

import java.util.ArrayList;

/**
 * Created by nvtuan on 26/08/2017.
 */

public class Store {

    private Reducer mReducer;
    private State mCurrentState;
    private ArrayList<StateListener> mSubscribers;

    private static class StoreLoader {
        private static Store store = new Store();
    }

    public static Store getInstance() {
        return StoreLoader.store;
    }

    private Store() {
        mReducer = new Reducer();
        mCurrentState = State.getDefaultState();
        mSubscribers = new ArrayList<>();
    }

    public State getState() {
        return mCurrentState;
    }

    public void subscribe(StateListener subscriber) {
        mSubscribers.add(subscriber);
    }

    public void unsubscribe(StateListener subscriber) {
        if (mSubscribers.contains(subscriber))
            mSubscribers.remove(subscriber);
    }

    public void dispatch(Action action) {
        mCurrentState = mReducer.reduce(action, mCurrentState);
        applyNewState();
    }

    private void applyNewState() {
        if (mSubscribers == null) return;

        for (StateListener subscriber : mSubscribers) {
            subscriber.onStateChange();
        }
    }
}
