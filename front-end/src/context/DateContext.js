import React, { useReducer, createContext, useContext } from 'react';

const today = new Date();
const dateState = today.toJSON().substr(0,10);

function dateReducer(state, action) {
  switch (action.type) {
    case 'UPDATE_DATE':
      return action.data;
    default:
      throw new Error(`Unhanded action type: ${action.type}`);
  }
}

const DateStateContext = createContext();
const DateDispatchContext = createContext();

export function DateProvider({ children }) {
  const [state, dispatch] = useReducer(dateReducer, dateState);
  return (
    <DateStateContext.Provider value={state}>
      <DateDispatchContext.Provider value={dispatch}>
        {children}
      </DateDispatchContext.Provider>
    </DateStateContext.Provider>
  )
}

export function useDateState() {
  return useContext(DateStateContext);
}

export function useDateDispatch() {
  return useContext(DateDispatchContext);
}