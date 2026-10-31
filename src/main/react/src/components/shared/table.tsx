import type { Inventor, Invention } from '../../types/types';
import InventorRow from "../inventor/inventor-row.tsx";

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
        <thead className="table-dark">
        <tr>
            {headerLabels.map((label, id) => (
                <th
                    key={id}
                    scope="col"
                    className="text-uppercase small fw-semibold"
                >
                    {label}
                </th>
            ))}

            {hasOperations && (
                <th
                    scope="col"
                    className="text-uppercase small fw-semibold text-center"
                >
                    Operations
                </th>
            )}
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
        <tbody className="table-group-divider">
        {elements.map((obj, id) => {
            switch (obj.type) {
                //Ha kéne:
                case "Invention":
                    return (<></>);

                case "Inventor":
                    return (
                        <InventorRow
                            key={id}
                            inventor={obj}
                            isComplex={false}
                            hasOperations={hasOperations}
                        />
                    );

                default:
                    return (<></>);
            }
        })}
        </tbody>
    );
}

export default function Table({ objectToMap, headerLabels, hasOperations = false }: Props) {
    return (
        <table className="table table-hover align-middle shadow-sm">
            <Header headerLabels={headerLabels} hasOperations={hasOperations} />
            <Body elements={objectToMap} hasOperations={hasOperations} />
        </table>
    );
}