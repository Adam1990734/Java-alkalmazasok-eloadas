import { useEffect, useState } from 'react';
import type { InventorResponse } from '../../types/types';
import axios from 'axios';

type Props = {
    id: number
}

export default function InventorDeleteView({ id }: Props) {
    const [inventor, setInventor] = useState<InventorResponse | null>(null);
    useEffect(() => {
        axios.get<InventorResponse>(`/api/inventors/${id}`).then((resp) => {
            setInventor({
                ...resp.data,
            });
        });
    }, []);

    const [result, setResult] = useState<number>(0);
    const onSubmit = async () => {
        const resp = await axios.delete(`/api/inventors/delete/${id}`);
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
