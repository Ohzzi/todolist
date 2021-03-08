import React, { useReducer, createContext, useContext } from 'react';
import axios from 'axios';

const todos = [];

export async function fetchTodos(dispatch, user, date) {
  try {
    const response = await axios(`/api/todos/${user.email}/${date}`);
    console.log(response);
    dispatch({
      type: 'FETCH',
      data: response.data,
    });
  } catch (error) {
    console.log(error);
    dispatch({
      type: 'FETCH',
      data: [],
    });
  }
}

function todoReducer(state, action) {
  switch(action.type) {
    case 'CREATE':
      return state.concat(action.todo);
    case 'TOGGLE': 
      return state.map(todo =>
        todo.id === action.id ? { ...todo, isDone: !todo.isDone } : todo
      );
    case 'REMOVE':
      return state.filter(todo => todo.id !== action.id);
    case 'FETCH':
      return action.data;
    default:
      throw new Error(`Unhandled action type: ${action.type}`);
  }
}

const TodoStateContext = createContext();
const TodoDispatchContext = createContext();

export function TodoProvider({ children }) {
  const [state, dispatch] = useReducer(todoReducer, todos);
  return (
    <TodoStateContext.Provider value={state}>
      <TodoDispatchContext.Provider value={dispatch}>
        {children}
      </TodoDispatchContext.Provider>
    </TodoStateContext.Provider>
  );
}

export function useTodoState() {
  return useContext(TodoStateContext);
}

export function useTodoDispatch() {
  return useContext(TodoDispatchContext);
}