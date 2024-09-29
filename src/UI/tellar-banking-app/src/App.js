import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import Header from './Header';
import Sidebar from './Sidebar';
import MainContent from './MainContent';
import './App.css'; 


const App = () => {
    return (
      <Router>
        <div className="app">
            <Header />
            <div className="content-container">
                <Sidebar />
                <MainContent />
            </div>
        </div>
        </Router>
    );
};

export default App;
