import './App.css'
import {useParams} from "react-router-dom";

function App() {
    const { id } = useParams();
    return (
        <>
            <span>{id}</span>
        </>
    )
}

export default App
