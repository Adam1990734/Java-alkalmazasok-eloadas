import type { Invention } from '../../types/types';

type Props = {
    invention: Invention;
    isComplex: boolean;
}
export default function InventionDetails({ invention, isComplex } : Props) {
    return (
        <>
            {isComplex ? (
                <>
                    <div>
                        <span>Name: </span>
                        <span>{invention.name}</span>
                    </div>
                    <div>
                        <span>Inventors: </span>
                        <ul>
                            {invention.inventors.map((inventor, id) => (
                                <li key={id}>{inventor.name}</li>
                            ))}
                        </ul>
                    </div>
                </>
            ) : (
                <div>
                    <span>Name: </span>
                    <span>{invention.name}</span>
                </div>
            )}
        </>
    );
}
