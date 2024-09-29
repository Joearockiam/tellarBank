import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import './form_control.css'

const UpdateCreditBalance = () => {
  const [employeeName, setEmployeeName] = useState(''); //store employee name
  const [employees, setEmployees] = useState([]);
  const [selectedEmployee, setSelectedEmployee] = useState(""); //store employee key
  const [selectedCompany, setSelectedCompany] = useState(""); //store company key
  const [trxnType, setTrxnType] = useState('');
  const [companyName, setCompanyName] = useState(''); //store company name
  const [amount, setAmount] = useState('');
  const [message, setMessage] = useState('');
  const [mapData, setMapData] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/company/all/name'); 
        const result = await response.json();
        console.log(result)
        setMapData(result); 
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);


  const fetchEmployees = async (company) => {
    try {
      const response = await fetch(`http://localhost:8080/api/employee/allEmployee?companyId=${company}`);
      const data = await response.json(); 
      setEmployees(data);
    } catch (error) {
      console.error("Error fetching employee data:", error);
    }
  };



  useEffect(() => { // fetch employees ffor the selected company
    if (selectedCompany) {
      fetchEmployees(selectedCompany);
      setSelectedEmployee(""); //reset
    } else {
      setEmployees([]); // Clear list
    }
  }, [selectedCompany]);

  const handleCompanyChange = (event) => {
    const selectedIndex = event.target.selectedIndex;
    setSelectedCompany(event.target.value);
    setCompanyName(event.target.options[selectedIndex].text)
  };
  const handleEmployeeChange = (event) => {
    setSelectedEmployee(event.target.value);
    const selectedIndex = event.target.selectedIndex;
    setEmployeeName(event.target.options[selectedIndex].text)
  };
  const handleChange = (e) => {
    setTrxnType(e.target.value);
  };

  const handleSubmit = async(e) => {
    e.preventDefault(); // Prevent page refresh
    // Create the data object to be sent to the API
    const employeeData = {
        companyName: companyName,
        employeeName: employeeName,
        trxnType:trxnType,
        amount:amount,
    };
    console.log(employeeData)
    try {
        // API call 
        const response = await axios.post('http://localhost:8080/api/employee/balance/update', employeeData);
  
        // Check if the API call was successful
        if (response.status === 200) {
          setMessage('Credit balance updated successfully!');
        } else {
          setMessage('Failed to update credit balance. Please try again.');
        }
  
      } catch (error) {
        setMessage('Error: ' + error.message);
      }
    setEmployeeName('');
    setCompanyName('');
    setAmount('');
    //setMessage('');
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Update Credit Balance</h2>
      <form onSubmit={handleSubmit} style={{ width: '500px' }}>

      {//data.length>0 && 
      <div className="form-group">
      <label>Company Name<span className="required">*</span></label>
      <select id="comboBox" className="form-control" required value={selectedCompany} onChange={handleCompanyChange}>
        <option value="">--Select--</option>
        {/* iterate the map*/}
        {Object.entries(mapData).map(([key, value]) => (
          <option value={key}>
          {value}
        </option>
        ))}
      </select>
    </div>
    }
        <div className="form-group">
          <label>Employee Name<span className="required">*</span></label>
        <select
        id="employeeComboBox"
        value={selectedEmployee}
        className="form-control"
        onChange={handleEmployeeChange}
        disabled={!selectedCompany || employees.length === 0}
      >
        <option value="">--Select--</option>
        {Object.entries(employees).map(([key, value]) => (
          <option value={key}>
            {value}
          </option>
        ))}
      </select>
        </div>
        <div className="form-group">
          <label>Transaction Type<span className="required">*</span></label>
          <select
                    id="transactionSelect"
                    value={trxnType}
                    onChange={handleChange}
                    className="form-control"
                    required
                >
                    <option value="">--Select--</option>
                    <option value="Credit">Credit</option>
                    <option value="Debit">Debit</option>
                </select>
          
        </div>
        <div className="form-group">
          <label>Amount<span className="required">*</span></label>
          <input
            type="text"
            className="form-control"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
            style={{ width: '500px' }}
          />
        </div>
        <div>
            &nbsp;
        </div>
        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </form>
      {message && <div className="mt-3 alert alert-info" style={{ width: '500px' }}>{message}</div>}
    </div>
  );
};

export default UpdateCreditBalance;
