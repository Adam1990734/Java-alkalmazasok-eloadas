import type { InventorResponse, InventorRequest, InventionMinimal } from '../../types/types';
import { useEffect, useRef, useState } from 'react';
import axios from 'axios';

type Props = {
    id: number;
};

export default function InventorUpdateView({ id }: Props) {
    const inventorRef = useRef<InventorRequest>({
        name: '',
        bornat: 0,
        diedat: null,
        inventions: []
    });
    const [inventor, setInventor] = useState<InventorResponse | null>(null);
    useEffect(() => {
        axios.get<InventorResponse>(`/api/inventors/${id}`).then((resp) => {
            const { inventions, ...other } = resp.data;
            inventorRef.current = {
                ...other,
                inventions: inventions.map((invention: InventionMinimal) : number => invention.id)
            };
            setInventor({
                ...resp.data
            });
        });
    }, []);

    const [result, setResult] = useState<number>(0);
    const onSubmit = async () => {
        console.log(inventorRef.current);
        const resp = await axios.put<InventorResponse>(`/api/inventors/update/${id}`, {
            ...inventorRef.current
        });
        setResult(resp.status);
    };

    return (
        <>
            {result === 204 && (
                <>
                    <span>Successfully update!</span>
                </>
            )}
            <form
                onSubmit={async (e) => {
                    e.preventDefault();
                    await onSubmit();
                }}
            >
                <input
                    type={'text'}
                    defaultValue={inventor?.name ?? ''}
                    onChange={(e) => (inventorRef.current.name = e.currentTarget.value)}
                    required={true}
                />
                <input
                    type={'number'}
                    defaultValue={inventor?.bornat ?? ''}
                    onChange={(e) => (inventorRef.current.bornat = Number(e.currentTarget.value))}
                    required={true}
                />
                <input
                    type={'number'}
                    defaultValue={inventor?.diedat ?? ''}
                    onChange={(e) => (inventorRef.current.diedat = isNaN(e.currentTarget.valueAsNumber) ? null : e.currentTarget.valueAsNumber)}
                    required={false}
                />
                <button type={'submit'}>Save</button>
            </form>
        </>
    );
}
