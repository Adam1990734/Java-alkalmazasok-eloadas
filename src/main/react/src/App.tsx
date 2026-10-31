/*
import './App.css'
import InventorIndexView from "./pages/inventor";

function App() {
    return (
        <InventorIndexView/>
    )
}

export default App
*/

// import './App.css'
import InventorIndexView from "./pages/inventor";
import { useNavigate } from "react-router-dom";

function App() {
    const navigate = useNavigate();

    return (
        <div className="container my-4">
            <div className="card p-3 mb-4 shadow-sm bg-white">
                <div className="d-flex justify-content-between align-items-center">
                    <h1 className="h4 m-0 text-primary fw-bold">CRUD</h1>
                    <div className="btn-group">
                        <button
                            className="btn btn-primary"
                            onClick={() => navigate('/crud/inventors')}
                        >
                            Inventors
                        </button>
                        <button
                            className="btn btn-outline-secondary"
                            disabled={true}
                            onClick={() => navigate('/crud/inventions')}
                        >
                            Inventions - Not Available
                        </button>
                    </div>
                </div>
            </div>
            <InventorIndexView />
        </div>
    );
}

export default App