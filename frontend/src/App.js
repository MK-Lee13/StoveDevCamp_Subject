import './App.css';
import React from 'react'
import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";
import Main from './pages/main'

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/main">
          <Main />
        </Route>
        <Redirect path="*" to="/main" />
      </Switch>
    </BrowserRouter >
  );
}

export default App;
