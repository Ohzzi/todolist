import React, { useReducer, createContext, useContext } from 'react';
import axios from 'axios';

const todos = [];

export async function createTodo(dispatch, data) {
  try {
    await axios.post('/api/todo', data);
    await fetchTodos(dispatch, data.user, data.date);
  } catch (error) {
    console.log(error);
  }
}

export async function fetchTodos(dispatch, user, date) {
  try {
    const response = await axios(`/api/todos/${user.email}/${date}`);
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

export async function completeTodo(state, dispatch, id) {
  dispatch({ type: 'TOGGLE', id: id });
  const todo = getTodoById(state, id);
  try {
    await axios.put(`/api/todo/${id}`, {
      content: todo.content,
      isImportant: todo.isImportant,
      isDone: !todo.isDone
    });
  } catch(error) {
    console.log(error);
  }
}

function getTodoById(state, id) {
  let result;
  state.forEach(item => {
    if (item.id === id) {
      result = item;
    }
  });
  return result;
}

export async function deleteTodo(dispatch, id) {
  dispatch({ type: 'REMOVE', id });
  try {
    await axios.delete(`/api/todo/${id}`);
    console.log(response);
  } catch(error) {
    console.log(error);
  }
}

function todoReducer(state, action) {
  switch(action.type) {
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