import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './table_style.css'
import axios from 'axios';

const AllEmployeeBalance = () => {
  const [companyName, setCompanyName] = useState('');
  const [employeesData, setEmployeesData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [mapData, setMapData] = useState({});
  const [selectedCompany, setSelectedCompany] = useState(""); //store company key

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

  const handleCompanyChange = (event) => {
    const selectedIndex = event.target.selectedIndex;
    setSelectedCompany(event.target.value);
    setCompanyName(event.target.options[selectedIndex].text)
  };

  const handleSubmit = async(e) => {
    e.preventDefault(); // Prevent page refresh
    try {
        setLoading(true);
        // API call 
        const response = await axios.get('http://localhost:8080/api/employee/all/balance',
          {
            params: {
            companyName: companyName,
            }
      },);
        console.log(response)
        // Check if the API call was successful
        if (response.status === 200) {
           console.log(response)
           setEmployeesData(response.data); 
           setLoading(false);
          } else {
            setError('Error fetching employee data');
        }
  
      } catch (error) {
        setError('Error fetching employee data '+error.meessage);
        setLoading(false);
      }
    // Clear the form fields
    setCompanyName('');
  };

  return (
    <div className="container mt-3">
      <h2 className="mb-4">All Employee Balance</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group" style={{ width: '500px'}}>
          <label>Company Name:</label>

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
        <div>
            &nbsp;
        </div>
        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </form>
      <div>
        &nbsp;
      </div>
      {loading && <p>Loading...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {employeesData.length>0 && !error && (
                <div className="table-container" style={{ width: '600px'}}> 
                    <h2>Employee List</h2>
                    <table className="employee-table" style={{ width: '500px'}}>
                        <thead >
                            <tr >
                                <th colSpan="2" className="header-cell">Name</th>
                                <th colSpan="2" className="header-cell">Balance</th>
                            </tr>
                        </thead>
                        <tbody>
                        {employeesData.map(employee => (
                            <tr>
                                <td colSpan="2" >{employee.name}</td>
                                <td colSpan="2" >{employee.balance}</td>
                            </tr>
                             ))}
                        </tbody>
                    </table>
                </div>
            )}
    </div>
  );
};

export default AllEmployeeBalance;
