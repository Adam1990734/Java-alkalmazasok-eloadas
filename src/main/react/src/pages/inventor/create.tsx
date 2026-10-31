import type {InventorRequest, InventorResponse, InventionMinimal} from '../../types/types';
import { useRef, useState } from 'react';
import axios from 'axios';
import InventionSelector from "../../components/invention/invention-selector.tsx";
import {useNavigate} from "react-router-dom";
import Layout from "../../layout.tsx";
import FormLayout from "../../formLayout.tsx";

export default function InventorCreateView() {
    const navigate = useNavigate();
    const inventorRef = useRef<InventorRequest>({
        name: '',
        bornat: 0,
        diedat: null,
        inventions: [],
    });
    const [inventions, setInventions] = useState<InventionMinimal[]>([]);
    const [result, setResult] = useState<{
        status: number;
        new: InventorResponse | null;
    }>({
        status: 0,
        new: null,
    });
    const onSubmit = async () => {
        const resp = await axios.post<InventorResponse>('http://localhost:8080/api/inventors', {
            ...inventorRef.current,
            inventions: inventions.map(i => i.id)
        });
        setResult({
            status: resp.status,
            new: { ...resp.data },
        });
    };

    return (
        <Layout>
            {result.status === 201 && result.new && (
                <>
                    <span>The inventor is created!</span>
                </>
            )}
            <FormLayout name={"Create"}>
                <form
                    onSubmit={async (e) => {
                        e.preventDefault();
                        await onSubmit();
                    }}
                >
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Name: </label> <br/>
                        <input className={"form-control"} type={'text'} onChange={(e) => (inventorRef.current.name = e.currentTarget.value)} required={true} />
                    </div>
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Born At: </label> <br/>
                        <input className={"form-control"} type={'number'} onChange={(e) => (inventorRef.current.bornat = Number(e.currentTarget.value))} required={true} />
                    </div>
                    <div className={"mb-3"}>
                        <label className={"form-label"}>Died At: </label> <br/>
                        <input
                            type={'number'}
                            className={"form-control"}
                            onChange={(e) => (inventorRef.current.diedat = isNaN(e.currentTarget.valueAsNumber) ? null : e.currentTarget.valueAsNumber)}
                            required={false}
                        />
                    </div>
                    <label className={"form-label"}>Inventions: </label> <br/>
                    <InventionSelector
                        value={inventions}
                        onChange={setInventions}
                    />
                    <button className={"btn btn-primary"} type={'submit'}>Add</button>
                </form>
                {result.status === 201 && result.new && (
                    <>
                        <span>The new inventor is can be found <button onClick={() => navigate(`/crud/inventors/${result.new!.id}`)}>here</button></span>
                    </>
                )}
                <button className={"btn btn-secondary"} onClick={() => navigate(`/crud/inventors`)}>Back</button>
            </FormLayout>
        </Layout>
    );
}
