import "./App.css";
import { HashRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/Login/Login";
import Lead from "./components/Leads/Leads";

function App() {
  return (
    <Router>
      <MainApp />
    </Router>
  );
}

function MainApp() {

  return (
    <>
      <Routes>
        <Route path="" element={<Login/>}/>
        <Route path="/leads" element={<Lead/>}/>
      </Routes>
    </>
  );
}

export default App;
