import type { Inventor } from '../../types/types';
import { useNavigate } from 'react-router-dom';

type Props = {
    inventor: Inventor;
    isComplex: boolean;
    hasOperations?: boolean;
};

export default function InventorRow({ inventor, isComplex, hasOperations = false }: Props) {
    const navigate = useNavigate();

    return (
        <>
            {isComplex ? (
                <tr>
                    <td>{inventor.name}</td>
                    <td>{inventor.bornat}</td>
                    <td>{inventor.diedat ? inventor.diedat : '-'}</td>
                    <td>
                        <ul>
                            {inventor.inventions.map((invention, id) => (
                                <li key={id}>{invention.name}</li>
                            ))}
                        </ul>
                    </td>
                    {hasOperations && (
                        <td>
                            <button onClick={() => navigate(`/crud/inventors/${inventor.id}`)}>Details</button>
                            <button onClick={() => navigate(`/crud/inventors/update/${inventor.id}`)}>Update</button>
                            <button onClick={() => navigate(`/crud/inventors/delete/${inventor.id}`)}>Delete</button>
                        </td>
                    )}
                </tr>
            ) : (
                <tr>
                    <td>{inventor.name}</td>
                    <td>{inventor.bornat}</td>
                    <td>{inventor.diedat ? inventor.diedat : '-'}</td>
                    { hasOperations &&
                        <td>
                            <button onClick={() => navigate(`/crud/inventors/${inventor.id}`)}>Details</button>
                            <button onClick={() => navigate(`/crud/inventors/update/${inventor.id}`)}>Update</button>
                            <button onClick={() => navigate(`/crud/inventors/delete/${inventor.id}`)}>Delete</button>
                        </td>
                    }
                </tr>
            )}
        </>
    );
}
