import type { Inventor } from '../../types/types';

type Props = {
    inventor: Inventor;
    isComplex: boolean;
}
export default function InventorDetails({ inventor, isComplex } : Props) {
    return (
        <div className="card shadow-sm border-0">
            <div className="card-header bg-primary text-white">
                <h4 className="mb-0">{inventor.name}</h4>
            </div>

            <div className="card-body">
                <div className="row g-3">
                    <div className="col-md-6">
                        <div className="text-secondary small fw-semibold">
                            Born In
                        </div>
                        <div>{inventor.bornat}</div>
                    </div>

                    <div className="col-md-6">
                        <div className="text-secondary small fw-semibold">
                            Died In
                        </div>
                        <div>{inventor.diedat ?? "-"}</div>
                    </div>
                </div>

                {isComplex && (
                    <>
                        <hr />

                        <h5 className="mb-3">Inventions</h5>

                        <ul className="list-group list-group-flush">
                            {inventor.inventions.map((invention, id) => (
                                <li
                                    key={id}
                                    className="list-group-item d-flex justify-content-between align-items-center"
                                >
                                    <span>{invention.name}</span>

                                    <span className="badge text-bg-primary rounded-pill">
                                {id + 1}
                            </span>
                                </li>
                            ))}
                        </ul>
                    </>
                )}
            </div>
        </div>
    );
}
