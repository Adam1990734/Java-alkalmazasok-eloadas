import { useEffect, useRef, useState } from 'react';
import type { Inventor } from '../../types/types';
import axios from 'axios';
import Pager from "../../components/shared/pager.tsx";
import Table from "../../components/shared/table.tsx";
import { useNavigate } from "react-router-dom";

type Props = {
    hasOperations?: boolean;
};

export default function InventorIndexView({ hasOperations = true }: Props) {
    const navigate = useNavigate();
    const [inventors, setInventors] = useState<Inventor[]>([]);
    const [page, setPage] = useState<number>(1);
    const lastPage = useRef<number>(0);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/inventors?page=${page}`)
            .then((data) => data.data)
            .then((data) => {
                lastPage.current = data.pageMaxCount;
                setInventors(
                    data.data.map(
                        (obj: Inventor): Inventor => ({
                            ...obj,
                            type: 'Inventor',
                        }),
                    ),
                );
            });
    }, [page]);

    return (
        <div className="container my-4">
            <div className="d-flex justify-content-between align-items-center mb-3">
                <h2 className="m-0">Feltalálók listája</h2>
                <button
                    className="btn btn-success"
                    onClick={() => navigate('/crud/inventors/create')}
                >
                    + Create a new inventor
                </button>
            </div>

            <Table headerLabels={['name', 'born in', 'died in']} objectToMap={inventors} hasOperations={hasOperations} />

            <div className="d-flex justify-content-center mt-3">
                <Pager currentPage={page} pageSetter={setPage} lastPage={lastPage.current} />
            </div>
        </div>
    );
}
