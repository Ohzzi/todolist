import React, { useReducer, createContext, useContext } from 'react';
import axios from 'axios';

const initialState = {};

function userReducer(state, action) {
  switch (action.type) {
    case 'GET_USER_SUCCESS':
      return action.data;
    case 'DELETE_USER':
      return {};
    default:
      throw new Error(`Unhanded action type: ${action.type}`);
  }
}

export async function getUser(userDispatch, todoDispatch, dateState) {
  try {
    const userResponse = await axios.get('/api/user');
    userDispatch({ type: 'GET_USER_SUCCESS', data: userResponse.data });
    const todoResponse = await axios.get(`/api/todos/${userResponse.data.email}/${dateState}`);
    todoDispatch({
      type: 'FETCH',
      data: todoResponse.data
    });
  } catch (error) {
    userDispatch({ type: 'DELETE_USER' });
    console.error(error);
    document.location.href = '/';
  }
}

const UserStateContext = createContext();
const UserDispatchContext = createContext();

export function UserProvider({ children }) {
  const [state, dispatch] = useReducer(userReducer, initialState);
  return (
    <UserStateContext.Provider value={state}>
      <UserDispatchContext.Provider value={dispatch}>
        {children}
      </UserDispatchContext.Provider>
    </UserStateContext.Provider>
  );
}

export function useUserState() {
  return useContext(UserStateContext);
}

export function useUserDispatch() {
  return useContext(UserDispatchContext);
}