import { useEffect, useState } from 'react';
import type { InventorResponse } from '../../types/types';
import axios from 'axios';
import { useParams } from 'react-router-dom';

export default function InventorDeleteView() {
    const { id } = useParams();
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
        const resp = await axios.delete(`http://localhost:8080/inventors/delete/${id}`);
        setResult(resp.status);
    };

    return (
        <>
            {result === 204 && (
                <>
                    <span>Successfully deletion!</span>
                </>
            )}
            <form
                onSubmit={async (e) => {
                    e.preventDefault();
                    await onSubmit();
                }}
            >
                <input type={'text'} defaultValue={inventor?.name ?? ''} required={true} disabled={true} />
                <input type={'number'} defaultValue={inventor?.bornat ?? ''} required={true} disabled={true} />
                <input type={'number'} defaultValue={inventor?.diedat ?? ''} required={true} disabled={true} />
                <button type={'submit'}>Delete</button>
            </form>
        </>
    );
}
