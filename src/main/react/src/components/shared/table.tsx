import type { Inventor, Invention } from '../../types/types';
import InventorRow from "../inventor/inventor-row.tsx";
import InventionRow from "../invention/invention-row.tsx";

type Props = {
    headerLabels: string[];
    objectToMap: Inventor[] | Invention[];
    hasOperations?: boolean;
};

type HeaderProps = {
    headerLabels: string[];
    hasOperations?: boolean;
};

function Header({ headerLabels, hasOperations = false }: HeaderProps) {
    return (
        <thead>
        <tr>
            {headerLabels.map((label, id) => (
                <th key={id}>{label}</th>
            ))}
            {hasOperations && <th>Operations</th>}
        </tr>
        </thead>
    );
}

type BodyProps = {
    elements: Inventor[] | Invention[];
    hasOperations?: boolean;
};

function Body({ elements, hasOperations = false } : BodyProps) {
    return (
        <tbody>
        {
            elements.map((obj, id) => {
                switch (obj.type) {
                    case 'Invention':
                        return <InventionRow key={id} invention={obj} isComplex={false} hasOperations={hasOperations}/>;
                    case 'Inventor':
                        return <InventorRow key={id} inventor={obj} isComplex={false} hasOperations={hasOperations}/>;
                }
            })
        }
        </tbody>
    );
}

export default function Table({ objectToMap, headerLabels, hasOperations = false }: Props) {
    return (
        <table className="table table-striped table-hover align-middle">
            <Header headerLabels={headerLabels} hasOperations={hasOperations} />
            <Body elements={objectToMap} hasOperations={hasOperations} />
        </table>
    );
}