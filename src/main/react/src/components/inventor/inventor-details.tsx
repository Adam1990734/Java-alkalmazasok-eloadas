import type { Inventor } from '../../types/types';

type Props = {
    inventor: Inventor;
    isComplex: boolean;
}
export default function InventorDetails({ inventor, isComplex } : Props) {
    return (
        <>
            {isComplex ? (
                <>
                    <div>
                        <span>Name: </span>
                        <span>{inventor.name}</span>
                    </div>
                    <div>
                        <span>Born In: </span>
                        <span>{inventor.bornat}</span>
                    </div>
                    <div>
                        <span>Died In: </span>
                        <span>{inventor.diedat ?? '-'}</span>
                    </div>
                    <div>
                        <span>Inventions: </span>
                        <ul>
                            {inventor.inventions.map((invention, id) => (
                                <li key={id}>{invention.name}</li>
                            ))}
                        </ul>
                    </div>
                </>
            ) : (
                <>
                    <div>
                        <span>Name: </span>
                        <span>{inventor.name}</span>
                    </div>
                    <div>
                        <span>Born In: </span>
                        <span>{inventor.bornat}</span>
                    </div>
                    <div>
                        <span>Died In: </span>
                        <span>{inventor.diedat ?? '-'}</span>
                    </div>
                </>
            )}
        </>
    );
}
