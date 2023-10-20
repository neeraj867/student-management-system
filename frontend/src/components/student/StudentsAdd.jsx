import React from 'react'
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const StudentsAdd = () => {
  let navigate = useNavigate();
  const [student,setStudent] = useState({
    firstName: "",
    lastName: "",
    email: "",
    department: ""
  });

  const {firstName,lastName,email,department} = student;

  const handleInputChange = (e) => {
      setStudent({...student,[e.target.name]:e.target.value});
  }

  const saveStudent = async(e) => {
      e.preventDefault();
      await axios.post("http://localhost:8080/api/student/add",student);
      navigate("/view-students");
  }

  return (
    <div className="container">
      <div className="row justify-content-center mt-5">
        <div className="col-lg-6 col-md-8 col-sm-10">
          <h4>Add Student</h4>
          <form onSubmit={(e)=>{saveStudent(e)}}>
            <div className="mb-3">
              <label className="form-label" htmlFor="firstName">
                First Name
              </label>
              <input
                className="form-control"
                type="text"
                name="firstName"
                id="firstName"
                required
                value={firstName}
                onChange={(e) => handleInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label className="form-label" htmlFor="lastName">
                Last Name
              </label>
              <input
                className="form-control"
                type="text"
                name="lastName"
                id="lastName"
                required
                value={lastName}
                onChange={(e) => handleInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label className="form-label" htmlFor="email">
                Email
              </label>
              <input
                className="form-control"
                type="text"
                name="email"
                id="email"
                required
                value={email}
                onChange={(e) => handleInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label className="form-label" htmlFor="department">
                Department
              </label>
              <input
                className="form-control"
                type="text"
                name="department"
                id="department"
                required
                value={department}
                onChange={(e) => handleInputChange(e)}
              />
            </div>
            <div className="mb-3 text-center">
              <button type="submit" className="btn btn-success mx-2">
                Save
              </button>
              <Link to={"/view-students"} type="submit" className="btn btn-warning mx-2">
                Cancel
              </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default StudentsAdd
