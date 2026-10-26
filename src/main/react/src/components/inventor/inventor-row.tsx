import type { Inventor } from '../../types/types';

type Props = {
    inventor: Inventor;
    isComplex: boolean;
    hasOperations?: boolean;
};

export default function InventorRow({ inventor, isComplex, hasOperations = false }: Props) {
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
                            <a href={`/crud/inventors/${inventor.id}`}>Details</a>
                            <a href={`/crud/inventors/update/${inventor.id}`}>Update</a>
                            <a href={`/crud/inventors/delete/${inventor.id}`}>Delete</a>
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
                            <a href={`/crud/inventors/${inventor.id}`}>Details</a>
                            <a href={`/crud/inventors/update/${inventor.id}`}>Update</a>
                            <a href={`/crud/inventors/delete/${inventor.id}`}>Delete</a>
                        </td>
                    }
                </tr>
            )}
        </>
    );
}
