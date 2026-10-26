import { useState, useEffect } from 'react';
import type { InventorResponse, Inventor } from '../../types/types';
import axios from 'axios';
import InventorDetails from "../../components/inventor/inventor-details.tsx";

type Props = {
    id: number;
};

export default function InventorDetailsViewView({ id }: Props) {

    const [inventor, setInventor] = useState<Inventor>();

    useEffect(() => {
        axios
            .get(`/api/inventors/${id}`)
            .then((data) => data.data)
            .then((data: InventorResponse) => {
                setInventor({
                    ...data,
                    type: 'Inventor',
                });
            });
    }, []);

    return (
        <>
            {inventor ? <InventorDetails inventor={inventor} isComplex={true} /> : <span>Hiba</span>}
        </>
    );
}
