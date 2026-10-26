import { useEffect, useRef, useState } from 'react';
import type { Inventor } from '../../types/types';
import axios from 'axios';
import Pager from "../../components/shared/pager.tsx";
import Table from "../../components/shared/table.tsx";

type Props = {
    hasOperations?: boolean;
};

export default function InventorIndexView({ hasOperations = true} : Props) {
    const [inventors, setInventors] = useState<Inventor[]>([]);
    const [page, setPage] = useState<number>(1);
    const lastPage = useRef<number>(0);
    useEffect(() => {
        axios.get(`/api/inventors?page=${page}`)
            .then((data) => data.data)
            .then((data) => {
                lastPage.current = data.last_page;
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
        <>
            <Table headerLabels={['name', 'born in', 'died in']} objectToMap={inventors} hasOperations={hasOperations} />
            <Pager currentPage={page} pageSetter={setPage} lastPage={lastPage.current} />
        </>
    );
}
