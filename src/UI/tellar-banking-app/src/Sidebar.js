import React from 'react';
import { Link } from 'react-router-dom';
import './Sidebar.css'; 


const Sidebar = () => {
    return (
        <div className="sidebar">
            <h2>Menu</h2>
                <div>
                    <ul>
                        <li><Link to="/RegisterEmployee">Register Employee</Link></li>
                        <li><Link to="/EmployeeBalance">Credit Balance</Link></li>
                        <li><Link to="/AllEmployeeBalance">All Employee Balance</Link></li>
                        <li><Link to="/UpdateCreditBalance">Update Credit Balance</Link></li>
                    </ul>
                </div>
        </div>
    );
};

export default Sidebar;
