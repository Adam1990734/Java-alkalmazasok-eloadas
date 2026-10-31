import { useEffect, useState } from 'react';
import type { InventorResponse } from '../../types/types';
import axios from 'axios';
import {useNavigate, useParams} from 'react-router-dom';
import Layout from "../../layout.tsx";
import FormLayout from "../../formLayout.tsx";

export default function InventorDeleteView() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [inventor, setInventor] = useState<InventorResponse | null>(null);
    useEffect(() => {
        axios.get<InventorResponse>(`http://localhost:8080/api/inventors/${id}`).then((resp) => {
            setInventor({
                ...resp.data,
            });
        });
    }, []);

    const [result, setResult] = useState<number>(0);
    const onSubmit = async () => {
        const resp = await axios.delete(`http://localhost:8080/api/inventors/${id}`);
        setResult(resp.status);
    };

    return (
        <Layout>
            {result === 204 && (
                <>
                    <span>Successfully deletion!</span>
                </>
            )}
            <FormLayout name={"Delete"}>
                <form
                    onSubmit={async (e) => {
                        e.preventDefault();
                        await onSubmit();
                    }}
                >
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Name: </label> <br/>
                        <input className={"form-control"} type={'text'} defaultValue={inventor?.name ?? ''} required={true} disabled={true} />
                    </div>
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Born At: </label> <br/>
                        <input className={"form-control"} type={'number'} defaultValue={inventor?.bornat ?? ''} required={true} disabled={true} />
                    </div>
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Died At: </label> <br/>
                        <input className={"form-control"} type={'number'} defaultValue={inventor?.diedat ?? ''} required={true} disabled={true} />
                    </div>
                    <button className={"btn btn-primary"} type={'submit'}>Delete</button>
                </form>
                <button className={"btn btn-secondary"} onClick={() => navigate(`/crud/inventors`)}>Back</button>
            </FormLayout>
        </Layout>
    );
}
