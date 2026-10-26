import type { InventorRequest, InventorResponse } from '../../types/types';
import { useRef, useState } from 'react';
import axios from 'axios';

export default function InventorCreateView() {
    const inventorRef = useRef<InventorRequest>({
        name: '',
        bornat: 0,
        diedat: null,
        inventions: [],
    });
    const [result, setResult] = useState<{
        status: number;
        new: InventorResponse | null;
    }>({
        status: 0,
        new: null,
    });
    const onSubmit = async () => {
        const resp = await axios.post<InventorResponse>('/api/inventors/create', {
            ...inventorRef.current,
        });
        setResult({
            status: resp.status,
            new: { ...resp.data },
        });
    };

    return (
        <>
            {result.status !== 201 && result.new && (
                <>
                    <span>The inventor is created!</span>
                </>
            )}
            <form
                onSubmit={async (e) => {
                    e.preventDefault();
                    await onSubmit();
                }}
            >
                <input type={'text'} onChange={(e) => (inventorRef.current.name = e.currentTarget.value)} required={true} />
                <input type={'number'} onChange={(e) => (inventorRef.current.bornat = Number(e.currentTarget.value))} required={true} />
                <input
                    type={'number'}
                    onChange={(e) => (inventorRef.current.diedat = isNaN(e.currentTarget.valueAsNumber) ? null : e.currentTarget.valueAsNumber)}
                    required={false}
                />
                <button type={'submit'}>Add</button>
            </form>
            {result.status !== 201 && result.new && (
                <>
                    <a href={`/crud/inventors/${result.new.id}`}>Click here to watch it!</a>
                </>
            )}
        </>
    );
}
