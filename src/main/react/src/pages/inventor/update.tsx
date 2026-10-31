import type {InventorResponse, InventorRequest, InventionMinimal } from '../../types/types';
import { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import {useNavigate, useParams} from "react-router-dom";
import InventionSelector from "../../components/invention/invention-selector.tsx";
import Layout from "../../layouts/layout.tsx";
import FormLayout from "../../layouts/formLayout.tsx";

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
            <FormLayout name="Update Inventor">
                {result === 204 && (
                    <div className="alert alert-success">
                        The inventor was updated successfully.
                    </div>
                )}
                <form
                    onSubmit={async (e) => {
                        e.preventDefault();
                        await onSubmit();
                    }}
                >
                    <div className="mb-3">
                        <label className="form-label fw-semibold">
                            Name
                        </label>

                        <input
                            type="text"
                            className="form-control"
                            defaultValue={inventor?.name ?? ""}
                            required
                            onChange={(e) =>
                                (inventorRef.current.name =
                                    e.currentTarget.value)
                            }
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label fw-semibold">
                            Born At
                        </label>

                        <input
                            type="number"
                            className="form-control"
                            defaultValue={inventor?.bornat ?? ""}
                            required
                            onChange={(e) =>
                                (inventorRef.current.bornat =
                                    Number(e.currentTarget.value))
                            }
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label fw-semibold">
                            Died At
                        </label>

                        <input
                            type="number"
                            className="form-control"
                            defaultValue={inventor?.diedat ?? ""}
                            onChange={(e) =>
                                (inventorRef.current.diedat =
                                    isNaN(e.currentTarget.valueAsNumber)
                                        ? null
                                        : e.currentTarget.valueAsNumber)
                            }
                        />
                    </div>

                    <div className="mb-4">
                        <label className="form-label fw-semibold">
                            Inventions
                        </label>

                        <InventionSelector
                            value={selectedInventions}
                            onChange={(items) => {
                                setSelectedInventions(items);

                                inventorRef.current.inventions =
                                    items.map(item => item.id);
                            }}
                        />
                    </div>

                    <div className="d-flex justify-content-between">
                        <button
                            type="button"
                            className="btn btn-outline-secondary"
                            onClick={() => navigate("/crud/inventors")}
                        >
                            Back
                        </button>

                        <button
                            className="btn btn-primary"
                            type="submit"
                        >
                            Save Changes
                        </button>
                    </div>
                </form>
            </FormLayout>
        </Layout>
    );
}
