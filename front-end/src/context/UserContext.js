import React, { useReducer, createContext, useContext } from 'react';
import axios from 'axios';

const initialState = {
  loading: false,
  data: null,
  error: null
};

const loading = {
  loading: true,
  data: null,
  error: null
};

const success = data => ({
  loading: false,
  data,
  error: null
});

const error = error => ({
  loading: false,
  data: null,
  error
});

function userReducer(state, action) {
  switch (action.type) {
    case 'GET_USER':
      return loading;
    case 'GET_USER_SUCCESS':
      return success(action.data);
    case 'GET_USER_ERROR':
      return error(action.data);
    case 'DELETE_USER':
      return {
        loading: false,
        data: null,
        error: null
      };
    default:
      throw new Error(`Unhanded action type: ${action.type}`);
  }
}

export async function getUser(userDispatch, todoDispatch, dateState) {
  userDispatch({ type: 'GET_USER' });
  try {
    const userResponse = await axios.get('/api/user');
    userDispatch({ type: 'GET_USER_SUCCESS', data: userResponse.data });
    const todoResponse = await axios.get(`/api/todos/${userResponse.data.email}/${dateState}`);
    todoDispatch({
      type: 'FETCH',
      data: todoResponse.data
    });
  } catch (e) {
    userDispatch({ type: 'GET_USER_ERROR', error: e });
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