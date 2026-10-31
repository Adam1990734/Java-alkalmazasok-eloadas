import { useState, useEffect } from 'react';
import type { InventorResponse, Inventor } from '../../types/types';
import axios from 'axios';
import InventorDetails from "../../components/inventor/inventor-details.tsx";
import {useNavigate, useParams} from 'react-router-dom';
import Layout from "../../layouts/layout.tsx";

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
        <Layout>
            {inventor ? <InventorDetails inventor={inventor} isComplex={true} /> : <span>Hiba</span>}
            <button
                type="button"
                className="btn btn-outline-secondary"
                onClick={() => navigate("/crud/inventors")}
            >
                Back
            </button>
        </Layout>
    );
}
