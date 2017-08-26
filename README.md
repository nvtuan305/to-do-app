## Architechture
This app is built base on [Redux](http://redux.js.org/) architechture with three principles:
- **Single source of truth**: The state of your whole application is stored in an object tree within a single store.

- **State is read-only**: The only way to change the state is to emit an action, an object describing what happened.

- **Changes are made with pure functions**: To specify how the state tree is transformed by actions, you write pure reducers.

## Some important classes
- **Action**: An object describing what happened in the app. Actions contain some minimal data to indentify a modify the state. An action will be emmited by UI components or other part of the app. Some subclasses are:
  + **AddAction**: Add new task action.
  
  + **DeleteAndToggleAction**: Delete or toggle state task action.
  
- **State**: An object storing all the app state include some data, UI state. In this app, the state object contains a list of tasks.

- **Reducer**: Take the previous state and an action, then change the state according to the specific action and return the next application state.

- **Store**: 
  + Hold the application state and allow access to state via *getState()*.
  + A reducer to reduce the next app state. The store object allows emit an action to update the app state via *dispatch(action)*.
  + List of change listener. It will be call any time an action dispatched. The store object also allows unregister the listener from list of change listener.
  

