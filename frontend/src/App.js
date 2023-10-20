import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '/node_modules/bootstrap/dist/js/bootstrap.min.js'
import './App.css';
import StudentsView from './components/student/StudentsView.jsx'
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom';
import NavBar from './components/common/NavBar';
import StudentsAdd from './components/student/StudentsAdd';
import Home from './Home';
import EditStudent from './components/student/EditStudent';
import StudentProfile from './components/student/StudentProfile';

function App() {
  return (
    <main className="container mt-5">
        <Router>
          <NavBar />
          <Routes>
                <Route path="/" element={<Home />}></Route>
                <Route path="/view-students" element={<StudentsView />}></Route>
                <Route path="/add-students" element={<StudentsAdd />}></Route>
                <Route path= "/student-profile/:id" element={<StudentProfile />}></Route>
                <Route path= "/edit-students/:id" element={<EditStudent />}></Route>
          </Routes>
        </Router>
    </main>
  );
}

export default App;
