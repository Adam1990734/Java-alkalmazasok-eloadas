import { useState, useEffect } from 'react';
import type { InventorResponse, Inventor } from '../../types/types';
import axios from 'axios';
import InventorDetails from "../../components/inventor/inventor-details.tsx";
import {useNavigate, useParams} from 'react-router-dom';

export default function InventorDetailsView() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [inventor, setInventor] = useState<Inventor>();

    useEffect(() => {
        axios
            .get(`http://localhost:8080/api/inventors/${id}`)
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
            <button onClick={() => navigate(`/crud/inventors`)}>Back</button>
        </>
    );
}
