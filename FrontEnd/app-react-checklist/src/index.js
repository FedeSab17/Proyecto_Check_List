import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import ThemeProvider from 'react-bootstrap/ThemeProvider'

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(

    <BrowserRouter>
      <ThemeProvider
        breakpoints={['xxl', 'xl', 'lg', 'md', 'sm', 'xs']}
        minBreakpoint="xs"
      > 
      <App />
      </ThemeProvider>
    </BrowserRouter>

);

reportWebVitals();



