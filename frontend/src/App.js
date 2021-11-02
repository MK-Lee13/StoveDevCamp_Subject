import './App.css';
import React from 'react'
import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";
import Main from './pages/main'
import Post from './pages/post'

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/main">
          <Main />
        </Route>
        <Route exact path="/post">
          <Post />
        </Route>
        <Redirect path="*" to="/main" />
      </Switch>
    </BrowserRouter >
  );
}

export default App;
