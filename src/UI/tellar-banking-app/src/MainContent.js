import React from 'react';
import { Routes, Route } from 'react-router-dom';
import './MainContent.css'; 
import RegisterEmployee from './employee/RegisterEmployee';
import EmployeeBalance from './employee/EmployeeBalance';
import AllEmployeeBalance from './employee/AllEmployeeBalance';
import UpdateCreditBalance from './employee/UpdateCreditBalance';

const MainContent = () => {
    return (
        <div className="main-content">
            <Routes>
                <Route path="/RegisterEmployee" element={<RegisterEmployee />} />
                <Route path="/EmployeeBalance" element={<EmployeeBalance />} />
                <Route path="/AllEmployeeBalance" element={<AllEmployeeBalance />} />
                <Route path="/UpdateCreditBalance" element={<UpdateCreditBalance />} />
            </Routes>
        </div>
    );
};

export default MainContent;
