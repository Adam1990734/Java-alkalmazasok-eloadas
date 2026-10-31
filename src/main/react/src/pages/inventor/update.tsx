import type {InventorResponse, InventorRequest, InventionMinimal } from '../../types/types';
import { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import {useNavigate, useParams} from "react-router-dom";
import InventionSelector from "../../components/invention/invention-selector.tsx";
import Layout from "../../layout.tsx";
import FormLayout from "../../formLayout.tsx";

export default function InventorUpdateView() {
    const { id } = useParams();
    const navigate = useNavigate();
    const inventorRef = useRef<InventorRequest>({
        name: '',
        bornat: 0,
        diedat: null,
        inventions: []
    });
    const [inventor, setInventor] = useState<InventorResponse | null>(null);
    const [selectedInventions, setSelectedInventions] = useState<InventionMinimal[]>([]);
    useEffect(() => {
        axios.get<InventorResponse>(`http://localhost:8080/api/inventors/${id}`).then((resp) => {
            const { inventions, ...other } = resp.data;
            inventorRef.current = {
                ...other,
                inventions: inventions.map((invention: InventionMinimal) : number => invention.id)
            };
            setSelectedInventions(inventions);
            setInventor({
                ...resp.data
            });
        });
    }, []);

    const [result, setResult] = useState<number>(0);
    const onSubmit = async () => {
        const resp = await axios.put<InventorResponse>(`http://localhost:8080/api/inventors/${id}`, {
            ...inventorRef.current
        });
        setResult(resp.status);
    };

    return (
        <Layout>
            {result === 204 && (
                <>
                    <span>Successfully update!</span>
                </>
            )}
            <FormLayout name={"Update"}>
                <form
                    onSubmit={async (e) => {
                        e.preventDefault();
                        await onSubmit();
                    }}
                >
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Name: </label> <br/>
                        <input
                            type={'text'}
                            className={"form-control"}
                            defaultValue={inventor?.name ?? ''}
                            onChange={(e) => (inventorRef.current.name = e.currentTarget.value)}
                            required={true}
                        />
                    </div>
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Born At: </label> <br/>
                        <input
                            type={'number'}
                            className={"form-control"}
                            defaultValue={inventor?.bornat ?? ''}
                            onChange={(e) => (inventorRef.current.bornat = Number(e.currentTarget.value))}
                            required={true}
                        />
                    </div>
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Died At: </label> <br/>
                        <input
                            type={'number'}
                            className={"form-control"}
                            defaultValue={inventor?.diedat ?? ''}
                            onChange={(e) => (inventorRef.current.diedat = isNaN(e.currentTarget.valueAsNumber) ? null : e.currentTarget.valueAsNumber)}
                            required={false}
                        />
                    </div>
                    <label className={"form-label"}>Inventions: </label> <br/>
                    <InventionSelector
                        value={selectedInventions}
                        onChange={(items) => {
                            setSelectedInventions(items);

                            inventorRef.current.inventions =
                                items.map(item => item.id);
                        }}
                    />
                    <button className={"btn btn-primary"} type={'submit'}>Save</button>
                </form>
                <button className={"btn btn-secondary"} onClick={() => navigate(`/crud/inventors`)}>Back</button>
            </FormLayout>
        </Layout>
    );
}
