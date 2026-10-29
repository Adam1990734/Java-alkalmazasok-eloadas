import type { Invention } from '../../types/types';
import {useNavigate} from "react-router-dom";

type Props = {
    invention: Invention;
    isComplex: boolean;
    hasOperations?: boolean;
}

export default function InventionRow({ invention, isComplex, hasOperations = false } : Props) {
    const navigate = useNavigate();
    return (
        <>
            {isComplex ? (
                <tr>
                    <td>{invention.name}</td>
                    <td>
                        <ul>
                            {invention.inventors.map((inventor, id) => (
                                <li key={id}>{inventor.name}</li>
                            ))}
                        </ul>
                    </td>
                    { hasOperations && (
                        <td>
                            <button className={"secondary-btn"} onClick={() => navigate(`/crud/inventions/${invention.id}`)}>Details</button>
                            <button className={"secondary-btn"} onClick={() => navigate(`/crud/inventions/update/${invention.id}`)}>Update</button>
                            <button className={"secondary-btn"} onClick={() => navigate(`/crud/inventions/delete/${invention.id}`)}>Delete</button>
                        </td>
                    )}
                </tr>
            ) : (
                <tr>
                    <td>{invention.name}</td>
                    { hasOperations &&
                        <td>
                            <button className={"secondary-btn"} onClick={() => navigate(`/crud/inventions/${invention.id}`)}>Details</button>
                            <button className={"secondary-btn"} onClick={() => navigate(`/crud/inventions/update/${invention.id}`)}>Update</button>
                            <button className={"secondary-btn"} onClick={() => navigate(`/crud/inventions/delete/${invention.id}`)}>Delete</button>
                        </td>
                    }
                </tr>
            )}
        </>
    );
}
