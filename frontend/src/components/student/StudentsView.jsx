import React, { useEffect, useState } from 'react'
import axios from 'axios'
import {FaEdit, FaEye, FaTrashAlt} from 'react-icons/fa'
import { Link } from 'react-router-dom';
import Search from '../common/Search';

const StudentsView = () => {
const [students,setStudents] = useState([]);
const [search,setSearch] = useState("");

useEffect(()=>{
    loadStudents();
},[]);

const loadStudents = async () => {
        const results = await axios.get(
            "http://localhost:8080/api/student/getAll");
        setStudents(results.data);
}

const handleDelete = async(id) => {
    await axios.delete(`http://localhost:8080/api/student/delete/${id}`);
    loadStudents();
}

  return (
    <section>
        <Search search={search} setSearch={setSearch}/>
        <table className="table table-bordered table-hover shadow">
            <thead>
                <tr className="text-center">
                    <th>ID</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Email</th>
                    <th>Department</th>
                    <th colSpan="3">Action</th>
                </tr>
            </thead>
            <tbody className="text-center">
                {students.filter((st) => 
                    st.firstName.toLowerCase().includes(search)
                )
                .map((student,index) => (
                    <tr key={student.id}>
                    <th scope="row" key={index}>
                        {index+1}
                    </th>
                    <td>{student.firstName}</td>
                    <td>{student.lastName}</td>
                    <td>{student.email}</td>
                    <td>{student.department}</td>
                    <td className="mx-2">
                        <Link to={`/student-profile/${student.id}`} className="btn btn-info">
                            <FaEye/>
                        </Link>
                    </td>
                    <td className="mx-2">
                        <Link to={`/edit-students/${student.id}`} className="btn btn-warning">
                            <FaEdit/>
                        </Link>
                    </td>
                    <td className="mx-2">
                        <button onClick={()=>{handleDelete(student.id)}}  className="btn btn-danger">
                            <FaTrashAlt/>
                        </button>
                    </td>
                    </tr>
                ))}
            </tbody>
        </table>
    </section>
  )
}

export default StudentsView;
