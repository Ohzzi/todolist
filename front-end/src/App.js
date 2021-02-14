import React from 'react';
import { Route } from 'react-router-dom';
import Home from './components/Home';
import Todo from './components/Todo';

import './App.css';

function App() {

  return (
    <>
      <Route path="/" exact={true} component={Home} />
      <Route path="/todolist" exact={true} component={Todo} />
    </>
  );
}

export default App;