import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

const EmployeeBalance = () => {
  const [employeeName, setEmployeeName] = useState('');
  const [email, setEmail] = useState('');
  const [companyName, setCompanyName] = useState('');
  const [message, setMessage] = useState('');
  
  const handleSubmit = async(e) => {
    e.preventDefault(); // Prevent page refresh
    console.log('Form Submitted:', { employeeName, email, companyName });
    // Create the data object to be sent to the API
    const employeeData = {
        email: email,
        company: companyName,
        employeeName: employeeName,
    };
    try {
        // API call 
        const response = await axios.post('http://localhost:8080/api/employee/credit/balance', employeeData);
  
        // Check if the API call was successful
        if (response.status === 200) {
           console.log(response)
           setMessage("Credit:"+response.data['credit']);
        } else {
          setMessage('Failed to check employee balance. Please try again.');
        }
  
      } catch (error) {
        setMessage('Error: ' + error.message);
      }
    // Clear the form fields
    setEmployeeName('');
    setEmail('');
    setCompanyName('');
    //setMessage('')
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Credit Balance Check</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Employee Name</label>
          <input
            type="text"
            className="form-control"
            value={employeeName}
            onChange={(e) => setEmployeeName(e.target.value)}
            required
            style={{ width: '500px' }}
          />
        </div>
        <div className="form-group">
          <label>Email</label>
          <input
            type="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            style={{ width: '500px' }}
          />
        </div>
        <div className="form-group">
          <label>Company Name</label>
          <input
            type="text"
            className="form-control"
            value={companyName}
            onChange={(e) => setCompanyName(e.target.value)}
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

export default EmployeeBalance;
